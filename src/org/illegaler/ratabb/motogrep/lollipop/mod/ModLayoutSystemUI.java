package org.illegaler.ratabb.motogrep.lollipop.mod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated.LayoutInflatedParam;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.XResources;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Process;

import org.illegaler.ratabb.motogrep.lollipop.MotoGrepModule;
import org.illegaler.ratabb.motogrep.lollipop.mod.TrafficMeterAbstract.TrafficMeterMode;

import static org.illegaler.ratabb.motogrep.lollipop.Constant.*;

public class ModLayoutSystemUI {
	private static final String TAG = "ModLayout ";
	private static final String STATUSBAR = "status_bar";
	private static final String SIGNAL_CLUSTER_VIEW = "signal_cluster_view";

	private static Context mContext;

	private static RelativeLayout LEOT_MAIN;
	private static LinearLayout LEOT_ROOT;
	private static LinearLayout LEOT_NOTIF;
	private static LinearLayout LEOT_STAT;
	private static LinearLayout LEOT_SIGNAL;
	private static LinearLayout LEOT_BATTERY;
	private static LinearLayout LEOT_CLOCK;
	private static LinearLayout LEOT_SIMPLE;
	private static LinearLayout LEOT_CARRIER;
	private static LinearLayout LEOT_TRAFFIC;

	private static LinearLayout LEOT_TENGAH;

	private static LinearLayout.LayoutParams llparam_full = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.MATCH_PARENT);
	private static int wrapcontent = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
	private static StatusBarBattery mStatusBarBattery;
	private static StatusBarClock mStatusBarClock;
	private static List<BroadcastSubReceiver> mBroadcastSubReceivers = new ArrayList<BroadcastSubReceiver>();

	private static TrafficMeterAbstract mTraffic;
	private static TrafficMeterMode mTrafficMeterMode = TrafficMeterMode.OFF;

	private static XSharedPreferences pref;
	private static List<String> mStyleList;

	private static final String KEY_LEFT = "left";
	private static final String KEY_CENTER = "center";
	private static final String KEY_RIGHT = "right";
	private static final String KEY_NOTIF = "notif";
	private static final String KEY_STAT = "stat";
	private static final String KEY_SIGNAL = "signal";
	private static final String KEY_BATTERY = "battery";
	private static final String KEY_CLOCK = "clock";

	private static final String KEY_CARRIER = "carrier";
	private static final String KEY_TRAFFIC = "traffic";

	private static LayoutLocation mLayoutLocation = LayoutLocation.LL_LEFT;

	private static enum LayoutLocation {
		LL_LEFT, LL_CENTER, LL_RIGHT
	}

	private static void setRegisterReciver(Context context) {
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION_UPDATE);
		filter.addAction(ACTION_RESTART_SYSTEMUI);
		filter.addAction(ACTION_TRAFIC_UPDATE);
		filter.addAction(ACTION_CLOCK_TEXT);
		filter.addAction(ACTION_SIMPLE_SIGNAL);
		filter.addAction(ACTION_UPDATE_CARRIER);
		filter.addAction(ACTION_BATTERY_TExT);
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		filter.addAction(Intent.ACTION_TIME_TICK);
		filter.addAction(Intent.ACTION_TIME_CHANGED);
		filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
		context.registerReceiver(receiver, filter);
	}

	private static BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			for (BroadcastSubReceiver bsr : mBroadcastSubReceivers) {
				bsr.onBroadcastReceived(context, intent);
			}

			if (action.equals(ACTION_UPDATE)) {
				try {
					setStyleStatusbar();
				} catch (Throwable t) {
					MotoGrepModule
							.xLog(TAG, "ACTION_UPDATE: " + t.getMessage());
				}

			} else if (action.equals(ACTION_RESTART_SYSTEMUI)) {
				Process.sendSignal(Process.myPid(), Process.SIGNAL_KILL);
			} else if (action.equals(ACTION_TRAFIC_UPDATE)) {
				if (intent.hasExtra(EXTRA_DATA_MODE)) {
					try {
						TrafficMeterMode mode = TrafficMeterMode.valueOf(intent
								.getStringExtra(EXTRA_DATA_MODE));
						setTrafficMeterMode(mode);
					} catch (Throwable t) {
						MotoGrepModule.xLog(TAG,
								"ACTION_TRAFIC_UPDATE: " + t.getMessage());
					}
				}
			} else if (action.equals(ACTION_SIMPLE_SIGNAL)) {
				try {
					setSignalSimple();
				} catch (Throwable t) {
					MotoGrepModule.xLog(TAG,
							"ACTION_SIMPLE_SIGNAL: " + t.getMessage());
				}
			} else if (action.equals(ACTION_UPDATE_CARRIER)) {
				try {
					setCarrierLogo();
				} catch (Throwable t) {
					MotoGrepModule.xLog(TAG,
							"ACTION_UPDATE_CARRIER: " + t.getMessage());
				}
			}
		}

	};

	private static void setSignalSimple() {
		if (LEOT_SIMPLE == null) {
			return;
		}
		try {
			pref.reload();
			boolean simple = pref.getBoolean(PREF_KEY_ICON_SIGNAL, false);
			final LinearLayout.LayoutParams separo = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f);
			final LinearLayout.LayoutParams pul = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.MATCH_PARENT);

			if (simple) {
				LEOT_SIMPLE.setGravity(Gravity.END);
				LEOT_SIMPLE.setOrientation(LinearLayout.VERTICAL);
				LEOT_SIMPLE.setWeightSum(1);
				LEOT_SIMPLE.getChildAt(0).setLayoutParams(separo);
				LEOT_SIMPLE.getChildAt(1).setLayoutParams(separo);

			} else {
				LEOT_SIMPLE.setGravity(Gravity.CENTER_VERTICAL);
				LEOT_SIMPLE.setOrientation(LinearLayout.HORIZONTAL);
				LEOT_SIMPLE.getChildAt(0).setLayoutParams(pul);
				LEOT_SIMPLE.getChildAt(1).setLayoutParams(pul);
			}
			LEOT_SIMPLE.postInvalidate();
		} catch (Throwable t) {
			MotoGrepModule.xLog(TAG, "setSignalSimple: " + t.getMessage());
		}

	}

	private static void setCarrierLogo() {
		if (LEOT_CARRIER == null) {
			return;
		}
		try {
			ImageView iv = (ImageView) LEOT_CARRIER.getChildAt(0);
			pref.reload();
			boolean show = pref.getBoolean(PREF_KEY_ICON_CARRIER, false);
			if (show) {
				iv.setImageDrawable(DrawUtils.getDrawable(pref, PATH_ICON,
						"ic_carrier_logo"));
				iv.setVisibility(View.VISIBLE);
			} else {
				iv.setImageDrawable(null);
				iv.setVisibility(View.GONE);
			}
			iv.postInvalidate();
		} catch (Throwable t) {
			MotoGrepModule.xLog(TAG, "setCarrierLogo: " + t.getMessage());
		}
	}

	private static void setStyleStatusbar() {

		try {
			LEOT_TENGAH.removeAllViews();
			LEOT_MAIN.removeAllViews();
			String[] styles = getStyleFromPref();
			int position = -1;
			for (int index = 0; index < styles.length; index++) {
				String str = styles[index];
				if (str.equals(KEY_LEFT) || str.equals(KEY_CENTER)
						|| str.equals(KEY_RIGHT)) {
					position = 0;// HEADER
				} else {
					position += 1;// STYLE
				}
				changeStyleStatusbar(str, index, position);
			}

			LEOT_MAIN.addView(LEOT_TENGAH);
			LEOT_TENGAH.postInvalidate();
			LEOT_MAIN.postInvalidate();

		} catch (Throwable t) {
			MotoGrepModule.xLog(TAG, "setStyleStatusbar: " + t.getMessage());
		}
	}

	private static void setTrafficMeterMode(TrafficMeterMode mode) {
		if (mTrafficMeterMode == mode)
			return;
		mTrafficMeterMode = mode;
		if (mTraffic != null) {
			if (mBroadcastSubReceivers.contains(mTraffic)) {
				mBroadcastSubReceivers.remove(mTraffic);
			}
		}
		LEOT_TRAFFIC.removeView(mTraffic);
		if (mTrafficMeterMode != TrafficMeterMode.OFF) {
			mTraffic = TrafficMeterAbstract.create(mContext, mTrafficMeterMode);
			mTraffic.initialize(pref);
			mBroadcastSubReceivers.add(mTraffic);
			LEOT_TRAFFIC.addView(mTraffic);
		}
	}

	private static String[] getStyleFromPref() {
		pref.reload();
		mStyleList = new ArrayList<String>(Arrays.asList(pref.getString(
				PREF_KEY_STYLE_ORDER, DEFAULT_STYLE).split(",")));

		return getStyleFromList(mStyleList);
	}

	private static String[] getStyleFromList(List<String> list) {
		String[] styles = new String[list.size()];
		styles = list.toArray(styles);
		return styles;
	}

	private static void changeStyleStatusbar(String name, int index,
			int position) {

		LinearLayout leot = getLinearLayout(name);

		if (leot != null) {

			if (position == 1) {

				if (mLayoutLocation == LayoutLocation.LL_LEFT) {
					leot.setLayoutParams(RLParam.KIRI(wrapcontent));
				} else if (mLayoutLocation == LayoutLocation.LL_CENTER) {
					LEOT_TENGAH.addView(leot);
				} else if (mLayoutLocation == LayoutLocation.LL_RIGHT) {
					leot.setLayoutParams(RLParam.KANAN(wrapcontent));

				}
			} else if (position > 1) {

				if (mLayoutLocation == LayoutLocation.LL_LEFT) {
					leot.setLayoutParams(RLParam.RIGHT_OF(getIdLayout(index),
							wrapcontent));
				} else if (mLayoutLocation == LayoutLocation.LL_CENTER) {
					LEOT_TENGAH.addView(leot);

				} else if (mLayoutLocation == LayoutLocation.LL_RIGHT) {
					leot.setLayoutParams(RLParam.LEFT_OF(getIdLayout(index),
							wrapcontent));
				}

			}
			if (mLayoutLocation != LayoutLocation.LL_CENTER) {
				LEOT_MAIN.addView(leot);
			}

		}

	}

	private static LinearLayout getLinearLayout(String name) {
		if (name.equals(KEY_NOTIF)) {
			return LEOT_NOTIF;
		} else if (name.equals(KEY_STAT)) {
			return LEOT_STAT;
		} else if (name.equals(KEY_SIGNAL)) {
			return LEOT_SIGNAL;
		} else if (name.equals(KEY_BATTERY)) {
			return LEOT_BATTERY;
		} else if (name.equals(KEY_CLOCK)) {
			return LEOT_CLOCK;
		} else if (name.equals(KEY_CARRIER)) {
			return LEOT_CARRIER;
		} else if (name.equals(KEY_TRAFFIC)) {
			return LEOT_TRAFFIC;
		} else {
			if (name.equals(KEY_LEFT)) {
				mLayoutLocation = LayoutLocation.LL_LEFT;
			} else if (name.equals(KEY_CENTER)) {
				mLayoutLocation = LayoutLocation.LL_CENTER;
			} else if (name.equals(KEY_RIGHT)) {
				mLayoutLocation = LayoutLocation.LL_RIGHT;
			}
			return null;
		}

	}

	private static int getIdLayout(int index) {
		int ret = -1;
		int indexmin = index - 1;
		String[] styles = getStyleFromList(mStyleList);

		String compare = styles[indexmin];

		if (compare.equals(KEY_NOTIF)) {
			ret = LEOT_NOTIF.getId();
		} else if (compare.equals(KEY_STAT)) {
			ret = LEOT_STAT.getId();
		} else if (compare.equals(KEY_SIGNAL)) {
			ret = LEOT_SIGNAL.getId();
		} else if (compare.equals(KEY_BATTERY)) {
			ret = LEOT_BATTERY.getId();
		} else if (compare.equals(KEY_CLOCK)) {
			ret = LEOT_CLOCK.getId();
		} else if (compare.equals(KEY_CARRIER)) {
			ret = LEOT_CARRIER.getId();
		} else if (compare.equals(KEY_TRAFFIC)) {
			ret = LEOT_TRAFFIC.getId();
		}
		return ret;

	}

	private static int getIdViewByName(LayoutInflatedParam inflatedParam,
			String name) {
		return inflatedParam.res.getIdentifier(name, "id", SYSTEMUI_PKG);
	}

	/** public Xposed metode **/

	public static void initZygote(XSharedPreferences xpref) throws Throwable {
		pref = xpref;
	}

	public static void handleInitPackageResources(
			InitPackageResourcesParam resparam) throws Throwable {

		if (!resparam.packageName.equals(SYSTEMUI_PKG)) {
			return;
		}

		XResources xresource = resparam.res;
		XC_LayoutInflated statusBarHook = new XC_LayoutInflated() {
			@Override
			public void handleLayoutInflated(LayoutInflatedParam liparam)
					throws Throwable {
				try {
					View mView = liparam.view;
					mContext = mView.getContext();

					// FIXME notif layout overlap
					LEOT_ROOT = (LinearLayout) mView
							.findViewById(getIdViewByName(liparam,
									"status_bar_contents"));
					LEOT_MAIN = new RelativeLayout(mContext);

					LEOT_NOTIF = new LinearLayout(mContext);
					LEOT_STAT = new LinearLayout(mContext);
					LEOT_SIGNAL = new LinearLayout(mContext);
					LEOT_BATTERY = new LinearLayout(mContext);
					LEOT_CLOCK = new LinearLayout(mContext);
					LEOT_CARRIER = new LinearLayout(mContext);
					LEOT_TRAFFIC = new LinearLayout(mContext);
					LEOT_TENGAH = new LinearLayout(mContext);

					LEOT_MAIN.setId(0x1001);
					LEOT_NOTIF.setId(0x1002);
					LEOT_STAT.setId(0x1003);
					LEOT_SIGNAL.setId(0x1004);
					LEOT_BATTERY.setId(0x1005);
					LEOT_CLOCK.setId(0x1006);
					LEOT_CARRIER.setId(0x1007);
					LEOT_TRAFFIC.setId(0x1008);

					LEOT_NOTIF.setGravity(Gravity.CENTER_VERTICAL);
					LEOT_STAT.setGravity(Gravity.CENTER_VERTICAL);
					LEOT_SIGNAL.setGravity(Gravity.CENTER_VERTICAL);
					LEOT_BATTERY.setGravity(Gravity.CENTER_VERTICAL);
					LEOT_CLOCK.setGravity(Gravity.CENTER_VERTICAL);
					LEOT_CARRIER.setGravity(Gravity.CENTER_VERTICAL);
					LEOT_TRAFFIC.setGravity(Gravity.CENTER_VERTICAL);

					LEOT_TENGAH.setGravity(Gravity.CENTER_VERTICAL);
					LEOT_TENGAH.setPaddingRelative(1, 0, 1, 0);

					/** carrier logo **/
					ImageView ivCarrier = new ImageView(mContext);
					ivCarrier.setAdjustViewBounds(true);
					ivCarrier.setImageDrawable(null);

					LEOT_CARRIER.addView(ivCarrier);

					/** notification **/
					FrameLayout notif = (FrameLayout) LEOT_ROOT
							.findViewById(getIdViewByName(liparam,
									"notification_icon_area"));
					// XXX

					LEOT_ROOT.removeView(notif);
					LEOT_NOTIF.addView(notif);

					LinearLayout systemIcon = (LinearLayout) LEOT_ROOT
							.findViewById(getIdViewByName(liparam,
									"system_icons"));
					LinearLayout stat = (LinearLayout) systemIcon
							.findViewById(getIdViewByName(liparam,
									"statusIcons"));
					systemIcon.removeView(stat);
					LEOT_STAT.addView(stat);

					/** signal **/
					LinearLayout signal = (LinearLayout) systemIcon
							.findViewById(getIdViewByName(liparam,
									"signal_cluster"));

					LinearLayout.LayoutParams llParams0 = new LinearLayout.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT);
					llParams0.setMarginStart(0);
					signal.setLayoutParams(llParams0);

					LEOT_SIMPLE = new LinearLayout(mContext);
					View signal1 = (View) signal.findViewById(getIdViewByName(
							liparam, "mobile_combo"));
					View signal2 = (View) signal.findViewById(getIdViewByName(
							liparam, "mobile_combo_2"));
					signal.removeView(signal1);
					signal.removeView(signal2);
					signal.addView(LEOT_SIMPLE);
					LEOT_SIMPLE.addView(signal1);
					LEOT_SIMPLE.addView(signal2);

					systemIcon.removeView(signal);
					LEOT_SIGNAL.addView(signal);

					/** traffic **/

					try {
						TrafficMeterMode mode = TrafficMeterMode.valueOf(pref
								.getString(PREF_KEY_DATA_TRAFFIC, "OFF"));
						setTrafficMeterMode(mode);
					} catch (Throwable t) {
						MotoGrepModule.xLog(TAG,
								"setTrafficMeterMode: " + t.getMessage());
					}

					/** battery **/
					View battery = (View) systemIcon
							.findViewById(getIdViewByName(liparam, "battery"));

					mStatusBarBattery = new StatusBarBattery(pref);
					mStatusBarBattery.initialize(battery);
					mBroadcastSubReceivers.add(mStatusBarBattery);

					LEOT_BATTERY.addView(mStatusBarBattery.getBatteryText());
					LEOT_BATTERY.addView(mStatusBarBattery.getBatteryImage());

					/** clock **/
					LinearLayout systemIconArea = (LinearLayout) LEOT_ROOT
							.findViewById(getIdViewByName(liparam,
									"system_icon_area"));
					TextView clock = (TextView) systemIconArea
							.findViewById(getIdViewByName(liparam, "clock"));

					systemIconArea.removeView(clock);

					mStatusBarClock = new StatusBarClock(pref);
					mStatusBarClock.initialize(clock);
					mBroadcastSubReceivers.add(mStatusBarClock);
					LEOT_CLOCK.addView(mStatusBarClock.getClock());

					/**  **/

					LEOT_TENGAH.setLayoutParams(RLParam.TENGAH(wrapcontent));
					LEOT_MAIN.addView(LEOT_TENGAH);
					LEOT_MAIN.addView(LEOT_NOTIF);
					LEOT_MAIN.addView(LEOT_STAT);
					LEOT_MAIN.addView(LEOT_SIGNAL);
					LEOT_MAIN.addView(LEOT_BATTERY);
					LEOT_MAIN.addView(LEOT_CLOCK);
					LEOT_MAIN.addView(LEOT_CARRIER);
					LEOT_MAIN.addView(LEOT_TRAFFIC);
					systemIconArea.setLayoutParams(llparam_full);
					systemIconArea.setPadding(0, 0, 0, 0);
					systemIconArea.addView(LEOT_MAIN,
							LinearLayout.LayoutParams.MATCH_PARENT,
							LinearLayout.LayoutParams.MATCH_PARENT);

					setStyleStatusbar();
					setSignalSimple();
					setCarrierLogo();
					setRegisterReciver(mContext);

				} catch (Throwable t) {
					MotoGrepModule.xLog(TAG, "statusBarHook " + t.getMessage());
				}

			}
		};
		XC_LayoutInflated signalClusterViewHook = new XC_LayoutInflated() {
			@Override
			public void handleLayoutInflated(LayoutInflatedParam liparam)
					throws Throwable {
				try {
					View mView = liparam.view;

					/** remove sim label **/
					TextView[] arrSimLabel = {
							(TextView) mView.findViewById(getIdViewByName(
									liparam, "mobile_phone_label")),
							(TextView) mView.findViewById(getIdViewByName(
									liparam, "mobile_phone_label_2")) };

					for (TextView textView : arrSimLabel) {
						textView.setVisibility(View.GONE);
						textView.setTextSize(0.0f);
					}

					/** remove view space **/
					View[] arrSpace = {
							(View) mView.findViewById(getIdViewByName(liparam,
									"spacer_1a")),
							(View) mView.findViewById(getIdViewByName(liparam,
									"spacer_1b")),
							(View) mView.findViewById(getIdViewByName(liparam,
									"spacer_2a")),
							(View) mView.findViewById(getIdViewByName(liparam,
									"spacer_2b")) };
					for (View view : arrSpace) {
						view.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
					}

				} catch (Throwable t) {
					MotoGrepModule.xLog(TAG,
							"signalClusterViewHook " + t.getMessage());
				}

			}
		};

		/** hook **/
		xresource.hookLayout(SYSTEMUI_PKG, "layout", STATUSBAR, statusBarHook);

		xresource.hookLayout(SYSTEMUI_PKG, "layout", SIGNAL_CLUSTER_VIEW,
				signalClusterViewHook);

	}

}
