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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Process;
import org.illegaler.ratabb.motogrep.lollipop.MotoGrepModule;
import org.illegaler.ratabb.motogrep.lollipop.RLParam;

import static org.illegaler.ratabb.motogrep.lollipop.Constant.*;

public class ModLayoutSystemUI {
	private static final String TAG = "ModLayout ";
	private static final String STATUSBAR = "status_bar";
	private static final String SIGNAL_CLUSTER_VIEW = "signal_cluster_view";
	private static final String KEYGUARD_STATUS_BAR = "keyguard_status_bar";

	private static Context mContext;

	private static RelativeLayout LEOT_MAIN;
	private static LinearLayout LEOT_ROOT;
	private static LinearLayout LEOT_NOTIF;
	private static LinearLayout LEOT_STAT;
	private static LinearLayout LEOT_SIGNAL;
	private static LinearLayout LEOT_BATTERY;
	private static LinearLayout LEOT_CLOCK;

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

	private static final int LOC_LEFT = 0x001;
	private static final int LOC_CENTER = 0x002;
	private static final int LOC_RIGHT = 0x003;
	
	private static int LOC;

	private static int getLOC() {
		return LOC;
	}

	private static void setLOC(int lOC) {
		LOC = lOC;
	}

	private static BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(ACTION_UPDATE)) {
				try {
					updateStyleStatusbar();
				} catch (Throwable t) {
					MotoGrepModule.xLog("onReceive: " + t.getMessage());
				}

			} else if (action.equals(ACTION_RESTART_SYSTEMUI)) {
				Process.sendSignal(Process.myPid(), Process.SIGNAL_KILL);
			}
		}

	};

	private static String[] getStyleFromPref(XSharedPreferences xpref) {
		xpref.reload();

		List<String> listStyle = new ArrayList<String>(Arrays.asList(xpref
				.getString(PREF_KEY_STYLE_ORDER, DEFAULT_STYLE).split(",")));

		String[] styles = new String[listStyle.size()];
		styles = listStyle.toArray(styles);
		mStyleList = listStyle;
		return styles;
	}

	private static String[] getStyleFromList() {
		String[] styles = new String[mStyleList.size()];
		styles = mStyleList.toArray(styles);
		return styles;
	}

	private static void updateStyleStatusbar() {

		try {

			LEOT_MAIN.removeAllViews();
			String[] styles = getStyleFromPref(pref);

			int position = -1;
			for (int index = 0; index < styles.length; index++) {
				String str = styles[index];
				if (str.equals(KEY_LEFT) || str.equals(KEY_CENTER)
						|| str.equals(KEY_RIGHT)) {
					// HEADER
					position = 0;
				} else {
					// STYLE
					position += 1;
				}
				changeStyleStatusbar(str, index, position);
			}
		} catch (Throwable t) {
			MotoGrepModule
					.xLog(TAG + "updateStyleStatusbar: " + t.getMessage());
		}
	}

	private static void changeStyleStatusbar(String name, int index,
			int position) {

		LinearLayout leot = getLinearLayout(name);
		int w = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

		if (leot != null) {
			LEOT_MAIN.removeView(leot);
			if (position == 1) {
				if (getLocation(index) == LOC_LEFT) {
					leot.setLayoutParams(RLParam.KIRI(w));

				} else if (getLocation(index) == LOC_CENTER) {
					leot.setLayoutParams(RLParam.TENGAH(w));

				} else if (getLocation(index) == LOC_RIGHT) {
					leot.setLayoutParams(RLParam.KANAN(w));

				}
			} else if (position > 1) {
				int l = getLOC();
				if (l == LOC_LEFT) {
					leot.setLayoutParams(RLParam
							.RIGHT_OF(getIdLayout(index), w));
				} else if (l == LOC_CENTER) {
					if (position == 2) {
						leot.setLayoutParams(RLParam.RIGHT_OF(
								getIdLayout(index), w));
					} else if (position == 3) {
						leot.setLayoutParams(RLParam.LEFT_OF(
								getIdLayout(index - 1), w));
					} else if (position == 4) {
						leot.setLayoutParams(RLParam.RIGHT_OF(
								getIdLayout(index - 1), w));
					} else if (position == 5) {
						leot.setLayoutParams(RLParam.LEFT_OF(
								getIdLayout(index - 1), w));
					} else {
						leot.setLayoutParams(RLParam.LEFT_OF(
								getIdLayout(index - 1), w));
					}

				} else if (l == LOC_RIGHT) {
					leot.setLayoutParams(RLParam.LEFT_OF(getIdLayout(index), w));
				}

			}
			LEOT_MAIN.addView(leot);
			LEOT_MAIN.invalidate();

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
		} else {
			if (name.equals(KEY_LEFT)) {
				setLOC(LOC_LEFT);
			} else if (name.equals(KEY_CENTER)) {
				setLOC(LOC_CENTER);
			} else if (name.equals(KEY_RIGHT)) {
				setLOC(LOC_RIGHT);
			}
			return null;
		}

	}

	private static int getIdLayout(int index) {
		int ret = -1;
		int indexmin = index - 1;
		String[] styles = getStyleFromList();

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
		}
		return ret;

	}

	private static int getLocation(int index) {
		int ret = -1;
		int indexmin = index - 1;
		String[] styles = getStyleFromList();

		String compare = styles[indexmin];

		if (compare.equals(KEY_LEFT)) {
			ret = LOC_LEFT;
		} else if (compare.equals(KEY_CENTER)) {
			ret = LOC_CENTER;
		} else if (compare.equals(KEY_RIGHT)) {
			ret = LOC_RIGHT;
		}

		return ret;
	}

	private static int getIdViewByName(LayoutInflatedParam inflatedParam,
			String name) {
		return inflatedParam.res.getIdentifier(name, "id", SYSTEMUI_PKG);
	}

	/** public Xposed metode */

	public static void initZygote(XSharedPreferences xpref) throws Throwable {
		pref = xpref;
	}

	public static void handleInitPackageResources(
			InitPackageResourcesParam resparam) throws Throwable {

		if (resparam.packageName.equals(SYSTEMUI_PKG)) {
			XResources xresource = resparam.res;

			XC_LayoutInflated statusBarHook = new XC_LayoutInflated() {

				@Override
				public void handleLayoutInflated(LayoutInflatedParam liparam)
						throws Throwable {

					try {

						View mView = liparam.view;

						mContext = mView.getContext();

						//FIXME layout overlap 
						LEOT_MAIN = new RelativeLayout(mContext);
						
						LEOT_NOTIF = new LinearLayout(mContext);
						LEOT_STAT = new LinearLayout(mContext);
						LEOT_SIGNAL = new LinearLayout(mContext);
						LEOT_BATTERY = new LinearLayout(mContext);
						LEOT_CLOCK = new LinearLayout(mContext);

						LEOT_ROOT = (LinearLayout) mView
								.findViewById(getIdViewByName(liparam,
										"status_bar_contents"));

						LEOT_MAIN.setId(0x1001);
						LEOT_NOTIF.setId(0x1002);
						LEOT_STAT.setId(0x1003);
						LEOT_SIGNAL.setId(0x1004);
						LEOT_BATTERY.setId(0x1005);
						LEOT_CLOCK.setId(0x1006);

						LEOT_NOTIF.setGravity(Gravity.CENTER_VERTICAL);
						LEOT_STAT.setGravity(Gravity.CENTER_VERTICAL);
						LEOT_SIGNAL.setGravity(Gravity.CENTER_VERTICAL);
						LEOT_BATTERY.setGravity(Gravity.CENTER_VERTICAL);
						LEOT_CLOCK.setGravity(Gravity.CENTER_VERTICAL);

						FrameLayout notif = (FrameLayout) LEOT_ROOT
								.findViewById(getIdViewByName(liparam,
										"notification_icon_area"));
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

						LinearLayout signal = (LinearLayout) systemIcon
								.findViewById(getIdViewByName(liparam,
										"signal_cluster"));
						systemIcon.removeView(signal);
						LEOT_SIGNAL.addView(signal);

						View battery = (View) systemIcon
								.findViewById(getIdViewByName(liparam,
										"battery"));
						systemIcon.removeView(battery);
						LEOT_BATTERY.addView(battery);

						LinearLayout systemIconArea = (LinearLayout) LEOT_ROOT
								.findViewById(getIdViewByName(liparam,
										"system_icon_area"));
						TextView clock = (TextView) systemIconArea
								.findViewById(getIdViewByName(liparam, "clock"));
						systemIconArea.removeView(clock);
						LEOT_CLOCK.addView(clock);

						LEOT_MAIN.addView(LEOT_NOTIF);
						LEOT_MAIN.addView(LEOT_STAT);
						LEOT_MAIN.addView(LEOT_SIGNAL);
						LEOT_MAIN.addView(LEOT_BATTERY);
						LEOT_MAIN.addView(LEOT_CLOCK);

						LEOT_ROOT.addView(LEOT_MAIN,
								LinearLayout.LayoutParams.MATCH_PARENT,
								LinearLayout.LayoutParams.MATCH_PARENT);

						updateStyleStatusbar();

						IntentFilter filter = new IntentFilter();
						filter.addAction(ACTION_UPDATE);
						filter.addAction(ACTION_RESTART_SYSTEMUI);
						mContext.registerReceiver(receiver, filter);

					} catch (Throwable t) {
						MotoGrepModule.xLog("statusBarHook " + t.getMessage());
					}

				}
			};
			XC_LayoutInflated signalClusterViewHook = new XC_LayoutInflated() {
				@Override
				public void handleLayoutInflated(LayoutInflatedParam liparam)
						throws Throwable {
					try {
						View mView = liparam.view;

						LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
								0, LinearLayout.LayoutParams.MATCH_PARENT);

						// remove sim label
						TextView[] arrSimLabel = {
								(TextView) mView.findViewById(getIdViewByName(
										liparam, "mobile_phone_label")),
								(TextView) mView.findViewById(getIdViewByName(
										liparam, "mobile_phone_label_2")) };

						for (TextView textView : arrSimLabel) {
							textView.setVisibility(View.GONE);
							textView.setTextSize(0.0f);
						}

						// remove view space
						View[] arrSpace = {
								(View) mView.findViewById(getIdViewByName(
										liparam, "spacer_1a")),
								(View) mView.findViewById(getIdViewByName(
										liparam, "spacer_1b")),
								(View) mView.findViewById(getIdViewByName(
										liparam, "spacer_2a")),
								(View) mView.findViewById(getIdViewByName(
										liparam, "spacer_2b")) };
						for (View view : arrSpace) {
							view.setLayoutParams(linearLayoutParams);
						}

					} catch (Throwable t) {
						MotoGrepModule.xLog("signalClusterViewHook "
								+ t.getMessage());
					}

				}
			};

			XC_LayoutInflated keyguardStatusbarHook = new XC_LayoutInflated() {

				@Override
				public void handleLayoutInflated(LayoutInflatedParam liparam)
						throws Throwable {

					try {

						View mView = liparam.view;

						FrameLayout multiUserView = (FrameLayout) mView
								.findViewById(getIdViewByName(liparam,
										"multi_user_switch"));

						// FIXME: remove statusbar overlaping at lockscreen
						// multiUserView
						// .setLayoutParams(RLParam
						// .TENGAH(android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

						RelativeLayout rootLayout = (RelativeLayout) multiUserView
								.getParent();

						int childCount = rootLayout.getChildCount();

						for (int i = 0; i < childCount; i++) {
							View childView = rootLayout.getChildAt(i);
							childView.setVisibility(View.GONE);

						}

					} catch (Throwable t) {
						MotoGrepModule.xLog("keyguardStatusbarHook "
								+ t.getMessage());
					}

				}
			};

			try {

				xresource.hookLayout(SYSTEMUI_PKG, "layout", STATUSBAR,
						statusBarHook);// mContext 

				xresource.hookLayout(SYSTEMUI_PKG, "layout", SIGNAL_CLUSTER_VIEW,
						signalClusterViewHook);

				xresource.hookLayout(SYSTEMUI_PKG, "layout", KEYGUARD_STATUS_BAR,
						keyguardStatusbarHook);

			} catch (Throwable t) {
				MotoGrepModule.xLog("handleInitPackageResources "
						+ t.getMessage());
			}

		}
	}

}
