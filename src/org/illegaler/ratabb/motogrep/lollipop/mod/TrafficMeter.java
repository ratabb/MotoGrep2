/*
 * Copyright (C) 2013 CyanKang Project
 * Copyright (C) 2014 Peter Gregus for GravityBox Project (C3C076@xda)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.illegaler.ratabb.motogrep.lollipop.mod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import de.robv.android.xposed.XSharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;

public class TrafficMeter extends TrafficMeterAbstract {
	public static final int INACTIVITY_MODE_DEFAULT = 0;
	public static final int INACTIVITY_MODE_HIDDEN = 1;
	public static final int INACTIVITY_MODE_SUMMARY = 2;

	boolean mTrafficMeterHide;
	boolean mCanReadFromFile;
	int mTrafficMeterSummaryTime;
	long mTotalRxBytes;
	long mLastUpdateTime;
	long mTrafficBurstStartTime;
	long mTrafficBurstStartBytes;
	long mKeepOnUntil = Long.MIN_VALUE;
	String mB = "B";
	String mKB = "KB";
	String mMB = "MB";
	String mS = "s";

	NumberFormat mDecimalFormat = new DecimalFormat("##0.0");
	NumberFormat mIntegerFormat = NumberFormat.getIntegerInstance();

	public TrafficMeter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onInitialize(XSharedPreferences prefs) {
		mCanReadFromFile = canReadFromFile();
		try {
			int inactivityMode = INACTIVITY_MODE_DEFAULT;// Integer.valueOf("0");
			setInactivityMode(inactivityMode);
		} catch (NumberFormatException nfe) {
			// log("Invalid preference value for PREF_KEY_DATA_TRAFFIC_INACTIVITY_MODE");
		}

	}

	@Override
	protected void onPreferenceChanged(Intent intent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void startTrafficUpdates() {
		mTotalRxBytes = getTotalReceivedBytes();
		mLastUpdateTime = SystemClock.elapsedRealtime();
		mTrafficBurstStartTime = Long.MIN_VALUE;

		getHandler().removeCallbacks(mRunnable);
		getHandler().post(mRunnable);

	}

	@Override
	protected void stopTrafficUpdates() {
		final Handler h = getHandler();
		if (h != null && mRunnable != null) {
			h.removeCallbacks(mRunnable);
		}

	}

	private String formatTraffic(long bytes, boolean speed) {
		if (bytes > 10485760) { // 1024 * 1024 * 10
			return (speed ? "" : "(") + mIntegerFormat.format(bytes / 1048576)
					+ (speed ? mMB + "/" + mS : mMB + ")");
		} else if (bytes > 1048576) { // 1024 * 1024
			return (speed ? "" : "(")
					+ mDecimalFormat.format(((float) bytes) / 1048576f)
					+ (speed ? mMB + "/" + mS : mMB + ")");
		} else if (bytes > 10240) { // 1024 * 10
			return (speed ? "" : "(") + mIntegerFormat.format(bytes / 1024)
					+ (speed ? mKB + "/" + mS : mKB + ")");
		} else if (bytes > 1024) { // 1024
			return (speed ? "" : "(")
					+ mDecimalFormat.format(((float) bytes) / 1024f)
					+ (speed ? mKB + "/" + mS : mKB + ")");
		} else {
			return (speed ? "" : "(") + mIntegerFormat.format(bytes)
					+ (speed ? mB + "/" + mS : mB + ")");
		}
	}

	Runnable mRunnable = new Runnable() {
		@Override
		public void run() {
			long td = SystemClock.elapsedRealtime() - mLastUpdateTime;

			if (!mAttached) {
				return;
			}

			long currentRxBytes = mCanReadFromFile ? getTotalReceivedBytes()
					: TrafficStats.getTotalRxBytes();
			long newBytes = currentRxBytes - mTotalRxBytes;

			boolean disconnected = false;
			if (mCanReadFromFile && newBytes < 0) {
				// It's impossible to get a speed under 0
				currentRxBytes = 0;
				newBytes = 0;
				disconnected = true;
			}

			if (mTrafficMeterHide && newBytes == 0) {
				long trafficBurstBytes = (mCanReadFromFile && disconnected) ? mTotalRxBytes
						- mTrafficBurstStartBytes
						: currentRxBytes - mTrafficBurstStartBytes;

				if (trafficBurstBytes != 0 && mTrafficMeterSummaryTime != 0) {
					setText(formatTraffic(trafficBurstBytes, false));

					// if (DEBUG)
					// log("Traffic burst ended: "
					// + trafficBurstBytes
					// + "B in "
					// + (SystemClock.elapsedRealtime() -
					// mTrafficBurstStartTime)
					// / 1000 + "s");
					mKeepOnUntil = SystemClock.elapsedRealtime()
							+ mTrafficMeterSummaryTime;
					mTrafficBurstStartTime = Long.MIN_VALUE;
					mTrafficBurstStartBytes = currentRxBytes;
				}
			} else {
				if (mTrafficMeterHide
						&& mTrafficBurstStartTime == Long.MIN_VALUE) {
					mTrafficBurstStartTime = mLastUpdateTime;
					mTrafficBurstStartBytes = mTotalRxBytes;
				}
				if (td > 0) {
					setText(formatTraffic(newBytes * 1000 / td, true));
				}
			}

			// Hide if there is no traffic
			if (mTrafficMeterHide && newBytes == 0) {
				if (getVisibility() != GONE
						&& mKeepOnUntil < SystemClock.elapsedRealtime()) {
					setText("");
					setVisibility(View.GONE);
				}
			} else {
				if (getVisibility() != VISIBLE) {
					setVisibility(View.VISIBLE);
				}
			}

			mTotalRxBytes = (mCanReadFromFile && disconnected) ? mTotalRxBytes
					: currentRxBytes;
			mLastUpdateTime = SystemClock.elapsedRealtime();
			getHandler().postDelayed(mRunnable, mInterval);
		}
	};

	private boolean canReadFromFile() {
		return new File("/proc/net/dev").exists();
	}

	private long getTotalReceivedBytes() {
		String line;
		String[] segs;
		long received = 0;
		int i;
		long tmp = 0;
		boolean isNum;
		try {
			FileReader fr = new FileReader("/proc/net/dev");
			BufferedReader in = new BufferedReader(fr);
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.contains(":") && !line.startsWith("lo")) {
					segs = line.split(":")[1].split(" ");
					for (i = 0; i < segs.length; i++) {
						isNum = true;
						try {
							tmp = Long.parseLong(segs[i]);
						} catch (Exception e) {
							isNum = false;
						}
						if (isNum == true) {
							received = received + tmp;
							break;
						}
					}
				}
			}
			in.close();
		} catch (IOException e) {
			return -1;
		}
		return received;
	}

	private void setInactivityMode(int mode) {
		switch (mode) {
		case INACTIVITY_MODE_HIDDEN:
			mTrafficMeterHide = true;
			mTrafficMeterSummaryTime = 0;
			break;
		case INACTIVITY_MODE_SUMMARY:
			mTrafficMeterHide = true;
			mTrafficMeterSummaryTime = 3000;
			break;
		case INACTIVITY_MODE_DEFAULT:
		default:
			mTrafficMeterHide = false;
			mTrafficMeterSummaryTime = 0;
			break;
		}
	}
}
