/*
 * Copyright (C) 2013 Peter Gregus for GravityBox Project (C3C076@xda)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.illegaler.ratabb.motogrep.lollipop.mod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {

	// XXX
	public static Boolean isCmFalcon() {
		return isStringPropEquals("ro.cm.device", "falcon");
	}

	public static Boolean isStringPropEquals(String prop, String campare) {
		return getProp(prop).equalsIgnoreCase(campare);
	}

	public static String getProp(String prop) {
		Process p = null;
		String ret = "";

		try {
			p = new ProcessBuilder(new String[] { "/system/bin/getprop", prop })
					.redirectErrorStream(true).start();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				ret = line;
			}
			p.destroy();
		} catch (IOException ioe) {
			ret = ("null");
		} catch (NullPointerException npe) {
			ret = ("null");
		} catch (IndexOutOfBoundsException ioobe) {
			ret = ("null");
		}

		return ret;
	}

	public static String join(String[] stringArray, String separator) {
		String buf = "";
		for (String s : stringArray) {
			if (!buf.isEmpty())
				buf += separator;
			buf += s;
		}
		return buf;
	}

	private static Boolean mIsWifiOnly = null;

	public static boolean isWifiOnly(Context con) {
		// returns true if device doesn't support mobile data (is wifi only)
		if (mIsWifiOnly != null)
			return mIsWifiOnly;

		try {
			ConnectivityManager cm = (ConnectivityManager) con
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			mIsWifiOnly = (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) == null);
			return mIsWifiOnly;
		} catch (Throwable t) {
			mIsWifiOnly = null;
			return false;
		}
	}
}
