package org.illegaler.ratabb.motogrep.lollipop;

import android.os.Environment;

public class Constant {

	public static final String PREF_KEY_STYLE_ORDER = "styleorder";
	public static final String PREF_KEY_CLOCK_TEXT = "clocktext";
	public static final String PREF_KEY_DENSITY = "density";
	public final static String PREF_KEY_RESTART_UI = "restart_sysui";

	public static final String DEFAULT_STYLE = "left,signal,notif,center,clock,right,battery,stat";
	public static final String DEFAULT_CLOCK = "hh:mm a";

	public static final String ACTION_SAVE_PREF = "org.illegaler.ratabb.motogrep.lollipop.ACTION_SAVE_PREF";
	public static final String ACTION_UPDATE = "org.illegaler.ratabb.motogrep.lollipop.ACTION_UPDATE";
	public static final String ACTION_RESTART_SYSTEMUI = "org.illegaler.ratabb.motogrep.lollipop.ACTION_RESTART_SYSTEMUI";
	public static final String ACTION_TRAFIC_UPDATE = "org.illegaler.ratabb.motogrep.lollipop.ACTION_TRAFIC_UPDATE";
	public static final String ACTION_CLOCK_TEXT = "org.illegaler.ratabb.motogrep.lollipop.ACTION_CLOCK_TEXT";

	public static final String EXTRA_SAVE_PREF = "extra_save_pref";

	public static final String SYSTEMUI_PKG = "com.android.systemui";
	public static final String CLASS_PHONE_STATUSBAR = "com.android.systemui.statusbar.phone.PhoneStatusBar";

	public static final String PATH_ICON = Environment
			.getExternalStorageDirectory() + "/MotoGrep/icon";
	public static final String PATH_ICON_OTHER = Environment
			.getExternalStorageDirectory() + "/MotoGrep/icon/other";
}
