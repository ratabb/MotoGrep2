package org.illegaler.ratabb.motogrep.lollipop;

import org.illegaler.ratabb.motogrep.lollipop.mod.ModIconStatusBar;
import org.illegaler.ratabb.motogrep.lollipop.mod.ModLayoutSystemUI;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MotoGrepModule implements IXposedHookZygoteInit,
		IXposedHookInitPackageResources, IXposedHookLoadPackage {

	public static final String PACKAGE_NAME = MotoGrepModule.class.getPackage()
			.getName();
	public static String MODULE_PATH = null;
	private static XSharedPreferences pref;
	private static final String TAG = "MG2:";
	private static final Boolean DEBUG = true;


	public static void xLog(String where, String what) {
		if (DEBUG) {
			String format = TAG + " %s=%s";
			XposedBridge.log(String.format(format, where, what));
		}
	}

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		MODULE_PATH = startupParam.modulePath;
		pref = new XSharedPreferences(PACKAGE_NAME);
		pref.makeWorldReadable();
		ModLayoutSystemUI.initZygote(pref);
		ModIconStatusBar.initZygote(pref);

	}

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		ModIconStatusBar.handleLoadPackage(lpparam);

	}

	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam)
			throws Throwable {
		ModLayoutSystemUI.handleInitPackageResources(resparam);
		ModIconStatusBar.handleInitPackageResources(resparam);

	}

}
