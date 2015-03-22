package org.illegaler.ratabb.motogrep.lollipop;

import android.os.Environment;

public class Constant {

	public static final String PREF_KEY_STYLE_ORDER = "styleorder";
	public static final String PREF_KEY_CLOCK_TEXT = "clocktext";
	public static final String PREF_KEY_DENSITY = "density";
	public final static String PREF_KEY_RESTART_UI = "restart_sysui";
	public final static String PREF_KEY_DATA_TRAFFIC = "trafic";
	public final static String PREF_KEY_ICON_CARRIER = "iconcarrier";
	public final static String PREF_KEY_ICON_SIGNAL = "iconsignalstyle";
	public final static String PREF_KEY_ICON_DATA = "icondatakitkat";
	public final static String PREF_KEY_BATTERY_TEXT = "batterytext";

	public static final String DEFAULT_STYLE = "left,battery,notif,stat,center,clock,traffic,right,carrier,signal";
	public static final String DEFAULT_CLOCK = "hh:mm a";

	public static final String ACTION_SAVE_PREF = "ratabb.motogrep.ACTION_SAVE_PREF";

	public static final String ACTION_UPDATE = "ratabb.motogrep.ACTION_UPDATE";
	/** style order,icon signal **/
	public static final String ACTION_RESTART_SYSTEMUI = "ratabb.motogrep.ACTION_RESTART_SYSTEMUI";
	public static final String ACTION_TRAFIC_UPDATE = "ratabb.motogrep.ACTION_TRAFIC_UPDATE";
	public static final String ACTION_CLOCK_TEXT = "ratabb.motogrep.ACTION_CLOCK_TEXT";
	public static final String ACTION_SIMPLE_SIGNAL = "ratabb.motogrep.ACTION_SIMPLE_SIGNAL";
	public static final String ACTION_UPDATE_CARRIER = "ratabb.motogrep.ACTION_UPDATE_CARRIER";
	public static final String ACTION_BATTERY_TExT = "ratabb.motogrep.ACTION_BATTERY_TExT";

	public static final String EXTRA_SAVE_PREF = "extra_save_pref";
	public static final String EXTRA_DATA_MODE = "dtMode";
	public static final String EXTRA_BATTERY_MODE = "btMode";

	public static final String SYSTEMUI_PKG = "com.android.systemui";
	public static final String CLASS_PHONE_STATUSBAR = "com.android.systemui.statusbar.phone.PhoneStatusBar";

	public static final String PATH_ICON = Environment
			.getExternalStorageDirectory() + "/MotoGrep/icon";
	public static final String PATH_ICON_OTHER = Environment
			.getExternalStorageDirectory() + "/MotoGrep/icon/other";
	public static final String PATH_ROOT = Environment
			.getExternalStorageDirectory() + "/MotoGrep";
}
