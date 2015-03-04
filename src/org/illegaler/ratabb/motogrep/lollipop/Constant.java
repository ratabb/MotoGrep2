package org.illegaler.ratabb.motogrep.lollipop;

import android.os.Environment;

public class Constant {
	public static final String PREF_KEY_STYLE_ORDER = "pref_style_order";
	public static final String PREF_KEY_DENSITY = "pref_density";

	public static final String DEFAULT_STYLE = "left,signal,notif,center,clock,right,battery,stat";

	public static final String ACTION_SAVE_PREF = "org.illegaler.ratabb.motogrep.lollipop.ACTION_SAVE_PREF";
	public static final String ACTION_UPDATE = "org.illegaler.ratabb.motogrep.lollipop.ACTION_UPDATE";
	public static final String ACTION_RESTART_SYSTEMUI = "org.illegaler.ratabb.motogrep.lollipop.ACTION_RESTART_SYSTEMUI";

	public static final String EXTRA_SAVE_PREF = "extra_save_pref";

	public static final String SYSTEMUI_PKG = "com.android.systemui";
	public static final String CLASS_PHONE_STATUSBAR = "com.android.systemui.statusbar.phone.PhoneStatusBar";

	public static final String PATH_ICON = Environment
			.getExternalStorageDirectory() + "/MotoGrep/icon";
	public static final String PATH_ICON_OTHER = Environment
			.getExternalStorageDirectory() + "/MotoGrep/icon/other";
}
