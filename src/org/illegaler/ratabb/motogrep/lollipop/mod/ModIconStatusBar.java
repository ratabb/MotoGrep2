package org.illegaler.ratabb.motogrep.lollipop.mod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.illegaler.ratabb.motogrep.lollipop.MotoGrepModule;

import android.content.res.Resources;
import android.content.res.XResources;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import static org.illegaler.ratabb.motogrep.lollipop.Constant.*;

public class ModIconStatusBar {
	private static final String TAG = "ModIcon ";
	private static XSharedPreferences pref;
//	private static Object mPhoneStatusbar;
	// private static Context mContext;

	private static List<String> otherPkg = new ArrayList<String>();
	private static List<String> otherIcon = new ArrayList<String>();

	private static List<String> IC_SYSBAR;
	private static List<String> OTHER;

	private static void setResListSystemUI() {
		IC_SYSBAR = new ArrayList<String>();
		OTHER = new ArrayList<String>();

		IC_SYSBAR.add("ic_sysbar_back");
		IC_SYSBAR.add("ic_sysbar_back_ime");
		IC_SYSBAR.add("ic_sysbar_back_land");
		IC_SYSBAR.add("ic_sysbar_home");
		IC_SYSBAR.add("ic_sysbar_home_land");
		IC_SYSBAR.add("ic_sysbar_menu");
		IC_SYSBAR.add("ic_sysbar_menu_land");
		IC_SYSBAR.add("ic_sysbar_recent");
		IC_SYSBAR.add("ic_sysbar_recent_land");

		OTHER.add("star");
		OTHER.add("stat_notify_more");
		OTHER.add("stat_sys_airplane_mode");
		OTHER.add("stat_sys_alarm");
		OTHER.add("stat_sys_data_bluetooth");
		OTHER.add("stat_sys_data_bluetooth_connected");
		OTHER.add("stat_notify_image");
		OTHER.add("stat_notify_image_error");
	}

	private static void replaceOtherPackage(InitPackageResourcesParam resparam)
			throws Throwable {
		getListOtherPackageIcons();
		for (int i = 0; i < otherPkg.size(); i++) {
			String pkg = otherPkg.get(i);
			String res = otherIcon.get(i);
			String png = pkg + "#" + res;
			if (pkg.equals(resparam.packageName)) {
				try {
					resparam.res.setReplacement(pkg, "drawable", res,
							DrawUtils.getIcon(pref, PATH_ICON_OTHER, png));
				} catch (Throwable t) {
					MotoGrepModule.xLog(TAG + "replaceOther " + t.getMessage());
				}
			}
		}

	}

	private static void getListOtherPackageIcons() {
		File iconOtherDir = new File(PATH_ICON_OTHER);
		if (iconOtherDir.isDirectory()) {
			File[] listFile = iconOtherDir.listFiles();

			for (File file : listFile) {
				String fullFileName = file.getName();
				if (fullFileName.endsWith("png")) {
					String[] fileName = fullFileName.split("#");
					String key = fileName[0];
					String value = fileName[1].substring(0,
							fileName[1].length() - 4);
					otherPkg.add(key);
					otherIcon.add(value);

				}
			}
		}
	}

	private static void replaceResourceSytemUI(XResources xresource) {
		// FIXME
		try {
			for (String string : IC_SYSBAR) {
				if (getResExisted(SYSTEMUI_PKG, xresource, string)
						&& getIconReplacementExisted(PATH_ICON, string)) {

					xresource.setReplacement(SYSTEMUI_PKG, "drawable", string,
							DrawUtils.getIcon(pref, PATH_ICON, string));

				}
			}

			for (String string : OTHER) {
				if (getResExisted(SYSTEMUI_PKG, xresource, string)
						&& getIconReplacementExisted(PATH_ICON, string)) {
					xresource.setReplacement(SYSTEMUI_PKG, "drawable", string,
							DrawUtils.getIcon(pref, PATH_ICON, string));
				}
			}
		} catch (Throwable t) {
			MotoGrepModule.xLog(TAG + "replaceResourceSytemUI "
					+ t.getMessage());
		}
	}

	private static Boolean getResExisted(String pkgName, Resources resources,
			String string) {
		return (resources.getIdentifier(string, "drawable", pkgName) != 0);
	}

	private static Boolean getIconReplacementExisted(String stringPath,
			String name) {
		return ((File) new File(stringPath + "/" + name + ".png")).exists();
	}

	/** public Xposed metode */

	public static void initZygote(XSharedPreferences xpref) throws Throwable {
		pref = xpref;
		setResListSystemUI();
	}

	public static void handleLoadPackage(LoadPackageParam lpparam)
			throws Throwable {
		//
		// if (lpparam.packageName.equals(SYSTEMUI_PKG)) {
		// try {
		// final Class<?> phoneStatusBar = XposedHelpers.findClass(
		// CLASS_PHONE_STATUSBAR, lpparam.classLoader);
		//
		// XposedHelpers.findAndHookMethod(phoneStatusBar,
		// "makeStatusBarView", new XC_MethodHook() {
		// @Override
		// protected void beforeHookedMethod(
		// MethodHookParam param) throws Throwable {
		// mPhoneStatusbar = param.thisObject;
		// mContext = (Context) XposedHelpers
		// .getObjectField(mPhoneStatusbar,
		// "mContext");
		//
		// super.beforeHookedMethod(param);
		// }
		// });
		//
		// } catch (Throwable t) {
		// MotoGrepModule
		// .xLog(TAG + "handleLoadPackage " + t.getMessage());
		// }
		// }
	}

	public static void handleInitPackageResources(
			InitPackageResourcesParam resparam) throws Throwable {

		XResources xresource = resparam.res;

		// StatusBar SystemUI
		if (resparam.packageName.equals(SYSTEMUI_PKG)) {
			try {
				replaceResourceSytemUI(xresource);
			} catch (Throwable t) {
				MotoGrepModule.xLog(TAG + "handleInitPackageResources "
						+ t.getMessage());
			}
		}

		// icon statusbar other
		replaceOtherPackage(resparam);

	}
}
