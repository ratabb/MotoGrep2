package org.illegaler.ratabb.motogrep.lollipop.mod;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private static final String DRAWABLE = "drawable";

	private static List<String> IC_SYSBAR;
	private static List<String> STATUS;

	private static Map<String, String> OTHER_NOTIF;
	private static Map<String, String> DATA;
	private static Map<String, String> BOOL;
	private static Map<String, String> SIGNAL;
	private static Map<String, String> WIFI;

	/**
	 * private static String preficSignal = "zz_moto_"; private static String[]
	 * suficSignal = { "_of_4", "_of_4_fully", "_of_4_fully_wide",
	 * "_of_4_separated", "_of_4_separated_fully", "_of_4_separated_fully_wide",
	 * "_of_4_separated_wide", "_of_4_wide" };
	 * 
	 * private static String[] pngSignal = { "stat_sys_signal_0",
	 * "stat_sys_signal_1", "stat_sys_signal_2", "stat_sys_signal_3",
	 * "stat_sys_signal_4", "stat_sys_signal_no_signal",
	 * "stat_sys_airplane_mode" };
	 **/

	private static void setResListSystemUI() {
		IC_SYSBAR = new ArrayList<String>();
		STATUS = new ArrayList<String>();
		DATA = new HashMap<String, String>();
		BOOL = new HashMap<String, String>();
		SIGNAL = new HashMap<String, String>();
		WIFI = new HashMap<String, String>();
		// XXX res,png

		/**
		 * for (String png : pngSignal) { for (String akhiran : suficSignal) {
		 * String key = preficSignal + png + akhiran; SIGNAL.put(key, png); } }
		 **/

		// FIXME aghhhhhhhhh
		SIGNAL.put("ic_qs_signal_0", "stat_sys_signal_0");
		SIGNAL.put("ic_qs_signal_1", "stat_sys_signal_1");
		SIGNAL.put("ic_qs_signal_2", "stat_sys_signal_2");
		SIGNAL.put("ic_qs_signal_3", "stat_sys_signal_3");
		SIGNAL.put("ic_qs_signal_4", "stat_sys_signal_4");
		SIGNAL.put("ic_qs_signal_full_0", "stat_sys_signal_0");
		SIGNAL.put("ic_qs_signal_full_1", "stat_sys_signal_1");
		SIGNAL.put("ic_qs_signal_full_2", "stat_sys_signal_2");
		SIGNAL.put("ic_qs_signal_full_3", "stat_sys_signal_3");
		SIGNAL.put("ic_qs_signal_full_4", "stat_sys_signal_4");
		SIGNAL.put("stat_sys_signal_0", "stat_sys_signal_0");
		SIGNAL.put("stat_sys_signal_0_fully", "stat_sys_signal_0");
		SIGNAL.put("stat_sys_signal_1", "stat_sys_signal_1");
		SIGNAL.put("stat_sys_signal_1_fully", "stat_sys_signal_1");
		SIGNAL.put("stat_sys_signal_2", "stat_sys_signal_2");
		SIGNAL.put("stat_sys_signal_2_fully", "stat_sys_signal_2");
		SIGNAL.put("stat_sys_signal_3", "stat_sys_signal_3");
		SIGNAL.put("stat_sys_signal_3_fully", "stat_sys_signal_3");
		SIGNAL.put("stat_sys_signal_4", "stat_sys_signal_4");
		SIGNAL.put("stat_sys_signal_4_fully", "stat_sys_signal_4");
		SIGNAL.put("stat_sys_signal_null", "stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_4", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_4_separated", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_4_separated_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_4_wide", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_5", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_5_separated", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_5_separated_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_5_wide", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_6", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_6_separated", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_6_separated_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_0_of_6_wide", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_4", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_4_separated", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_4_separated_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_4_wide", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_5", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_5_separated", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_5_separated_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_5_wide", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_6", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_6_separated", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_6_separated_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_1_of_6_wide", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_4", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_4_separated", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_4_separated_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_4_wide", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_5", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_5_separated", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_5_separated_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_5_wide", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_6", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_6_separated", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_6_separated_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_2_of_6_wide", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_4", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_4_separated", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_4_separated_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_4_wide", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_5", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_5_separated", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_5_separated_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_5_wide", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_6", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_6_separated", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_6_separated_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_3_of_6_wide", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_4", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_4_separated", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_4_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_4_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_5", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_5_separated", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_5_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_5_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_6", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_6_separated", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_6_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_4_of_6_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_5_of_5", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_5_of_5_separated", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_5_of_5_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_5_of_5_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_5_of_6", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_5_of_6_separated", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_5_of_6_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_5_of_6_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_6_of_6", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_6_of_6_separated", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_6_of_6_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_6_of_6_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_4", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_4_separated",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_4_separated_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_4_wide", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_5", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_5_separated",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_5_separated_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_5_wide", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_6", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_6_separated",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_6_separated_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_0_of_6_wide", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_4", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_4_separated",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_4_separated_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_4_wide", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_5", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_5_separated",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_5_separated_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_5_wide", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_6", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_6_separated",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_6_separated_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_1_of_6_wide", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_4", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_4_separated",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_4_separated_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_4_wide", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_5", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_5_separated",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_5_separated_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_5_wide", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_6", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_6_separated",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_6_separated_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_2_of_6_wide", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_4", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_4_separated",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_4_separated_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_4_wide", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_5", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_5_separated",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_5_separated_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_5_wide", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_6", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_6_separated",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_6_separated_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_3_of_6_wide", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_4", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_4_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_4_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_4_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_5", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_5_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_5_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_5_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_6", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_6_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_6_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_4_of_6_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_5_of_5", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_5_of_5_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_5_of_5_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_5_of_5_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_5_of_6", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_5_of_6_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_5_of_6_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_5_of_6_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_6_of_6", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_6_of_6_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_6_of_6_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_full_6_of_6_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_ic_qs_signal_no_signal_of_4",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_ic_qs_signal_no_signal_of_4_wide",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_ic_qs_signal_no_signal_of_5",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_ic_qs_signal_no_signal_of_5_wide",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_ic_qs_signal_no_signal_of_6",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_ic_qs_signal_no_signal_of_6_wide",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_4", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_4_fully", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_4_fully_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_4_separated",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_4_separated_fully",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_4_separated_fully_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_4_separated_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_4_wide", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_5", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_5_fully", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_5_fully_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_5_separated",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_5_separated_fully",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_5_separated_fully_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_5_separated_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_5_wide", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_6", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_6_fully", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_6_fully_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_6_separated",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_6_separated_fully",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_6_separated_fully_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_6_separated_wide",
				"stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_0_of_6_wide", "stat_sys_signal_0");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_4", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_4_fully", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_4_fully_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_4_separated",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_4_separated_fully",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_4_separated_fully_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_4_separated_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_4_wide", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_5", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_5_fully", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_5_fully_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_5_separated",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_5_separated_fully",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_5_separated_fully_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_5_separated_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_5_wide", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_6", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_6_fully", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_6_fully_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_6_separated",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_6_separated_fully",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_6_separated_fully_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_6_separated_wide",
				"stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_1_of_6_wide", "stat_sys_signal_1");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_4", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_4_fully", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_4_fully_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_4_separated",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_4_separated_fully",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_4_separated_fully_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_4_separated_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_4_wide", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_5", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_5_fully", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_5_fully_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_5_separated",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_5_separated_fully",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_5_separated_fully_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_5_separated_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_5_wide", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_6", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_6_fully", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_6_fully_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_6_separated",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_6_separated_fully",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_6_separated_fully_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_6_separated_wide",
				"stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_2_of_6_wide", "stat_sys_signal_2");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_4", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_4_fully", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_4_fully_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_4_separated",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_4_separated_fully",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_4_separated_fully_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_4_separated_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_4_wide", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_5", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_5_fully", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_5_fully_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_5_separated",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_5_separated_fully",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_5_separated_fully_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_5_separated_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_5_wide", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_6", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_6_fully", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_6_fully_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_6_separated",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_6_separated_fully",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_6_separated_fully_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_6_separated_wide",
				"stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_3_of_6_wide", "stat_sys_signal_3");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_4", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_4_fully", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_4_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_4_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_4_separated_fully",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_4_separated_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_4_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_4_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_5", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_5_fully", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_5_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_5_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_5_separated_fully",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_5_separated_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_5_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_5_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_6", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_6_fully", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_6_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_6_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_6_separated_fully",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_6_separated_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_6_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_4_of_6_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_5", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_5_fully", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_5_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_5_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_5_separated_fully",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_5_separated_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_5_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_5_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_6", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_6_fully", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_6_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_6_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_6_separated_fully",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_6_separated_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_6_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_5_of_6_wide", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_6_of_6", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_6_of_6_fully", "stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_6_of_6_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_6_of_6_separated",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_6_of_6_separated_fully",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_6_of_6_separated_fully_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_6_of_6_separated_wide",
				"stat_sys_signal_4");
		SIGNAL.put("zz_moto_stat_sys_signal_6_of_6_wide", "stat_sys_signal_4");
		SIGNAL.put("stat_sys_airplane_mode", "stat_sys_airplane_mode");
		SIGNAL.put("zz_moto_stat_sys_signal_airplane_mode_of_4",
				"stat_sys_airplane_mode");
		SIGNAL.put("zz_moto_stat_sys_signal_airplane_mode_of_4_wide",
				"stat_sys_airplane_mode");
		SIGNAL.put("zz_moto_stat_sys_signal_airplane_mode_of_5",
				"stat_sys_airplane_mode");
		SIGNAL.put("zz_moto_stat_sys_signal_airplane_mode_of_5_wide",
				"stat_sys_airplane_mode");
		SIGNAL.put("zz_moto_stat_sys_signal_airplane_mode_of_6",
				"stat_sys_airplane_mode");
		SIGNAL.put("zz_moto_stat_sys_signal_airplane_mode_of_6_wide",
				"stat_sys_airplane_mode");
		SIGNAL.put("zz_moto_stat_sys_signal_no_signal_of_4",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_stat_sys_signal_no_signal_of_4_wide",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_stat_sys_signal_no_signal_of_5",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_stat_sys_signal_no_signal_of_5_wide",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_stat_sys_signal_no_signal_of_6",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_stat_sys_signal_no_signal_of_6_wide",
				"stat_sys_signal_no_signal");
		SIGNAL.put("zz_moto_ic_qs_no_sim", "stat_sys_data_no_sim");
		SIGNAL.put("zz_moto_ic_qs_no_sim_wide", "stat_sys_data_no_sim");
		SIGNAL.put("zz_moto_stat_sys_corrupt_sim", "stat_sys_data_corrupt_sim");
		SIGNAL.put("zz_moto_stat_sys_corrupt_sim_wide",
				"stat_sys_data_corrupt_sim");
		SIGNAL.put("zz_moto_stat_sys_no_sim", "stat_sys_data_no_sim");
		SIGNAL.put("zz_moto_stat_sys_no_sim_wide", "stat_sys_data_no_sim");
		SIGNAL.put("zz_moto_stat_sys_signal_emergency_only",
				"stat_sys_data_no_sim");
		SIGNAL.put("zz_moto_stat_sys_signal_emergency_only_wide",
				"stat_sys_data_no_sim");
		SIGNAL.put("zz_moto_ic_qs_signal_emergency_only",
				"stat_sys_data_no_sim");
		SIGNAL.put("zz_moto_ic_qs_signal_emergency_only_wide",
				"stat_sys_data_no_sim");

		// XXX
		DATA.put("ic_qs_signal_1x", "stat_sys_data_connected_1x");
		DATA.put("ic_qs_signal_3g", "stat_sys_data_connected_3g");
		DATA.put("ic_qs_signal_4g", "stat_sys_data_connected_4g");
		DATA.put("ic_qs_signal_e", "stat_sys_data_connected_e");
		DATA.put("ic_qs_signal_g", "stat_sys_data_connected_g");
		DATA.put("ic_qs_signal_h", "stat_sys_data_connected_h");
		DATA.put("ic_qs_signal_lte", "stat_sys_data_connected_lte");
		DATA.put("ic_qs_signal_r", "stat_sys_data_connected_roam");
		DATA.put("stat_sys_data_fully_connected_1x",
				"stat_sys_data_connected_1x");
		DATA.put("stat_sys_data_fully_connected_3g",
				"stat_sys_data_connected_3g");
		DATA.put("stat_sys_data_fully_connected_4g",
				"stat_sys_data_connected_4g");
		DATA.put("stat_sys_data_fully_connected_e", "stat_sys_data_connected_e");
		DATA.put("stat_sys_data_fully_connected_g", "stat_sys_data_connected_g");
		DATA.put("stat_sys_data_fully_connected_h", "stat_sys_data_connected_h");
		DATA.put("stat_sys_data_fully_connected_lte",
				"stat_sys_data_connected_lte");
		DATA.put("stat_sys_data_fully_connected_roam",
				"stat_sys_data_connected_roam");
		DATA.put("zz_moto_ic_qs_signal_1x", "stat_sys_data_connected_1x");
		DATA.put("zz_moto_ic_qs_signal_1x_wide", "stat_sys_data_connected_1x");
		DATA.put("zz_moto_ic_qs_signal_2g", "stat_sys_data_connected_2g");
		DATA.put("zz_moto_ic_qs_signal_2g_wide", "stat_sys_data_connected_2g");
		DATA.put("zz_moto_ic_qs_signal_3g", "stat_sys_data_connected_3g");
		DATA.put("zz_moto_ic_qs_signal_3g_plus",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_ic_qs_signal_3g_plus_wide",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_ic_qs_signal_3g_wide", "stat_sys_data_connected_3g");
		DATA.put("zz_moto_ic_qs_signal_4g", "stat_sys_data_connected_4g");
		DATA.put("zz_moto_ic_qs_signal_4g_lte", "stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_4g_lte_waves_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_4g_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_4g_waves_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_ic_qs_signal_4g_wide", "stat_sys_data_connected_4g");
		DATA.put("zz_moto_ic_qs_signal_attached_1x",
				"stat_sys_data_connected_1x");
		DATA.put("zz_moto_ic_qs_signal_attached_1x_wide",
				"stat_sys_data_connected_1x");
		DATA.put("zz_moto_ic_qs_signal_attached_2g",
				"stat_sys_data_connected_2g");
		DATA.put("zz_moto_ic_qs_signal_attached_2g_wide",
				"stat_sys_data_connected_2g");
		DATA.put("zz_moto_ic_qs_signal_attached_3g",
				"stat_sys_data_connected_3g");
		DATA.put("zz_moto_ic_qs_signal_attached_3g_plus",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_ic_qs_signal_attached_3g_plus_wide",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_ic_qs_signal_attached_3g_wide",
				"stat_sys_data_connected_3g");
		DATA.put("zz_moto_ic_qs_signal_attached_4g",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_ic_qs_signal_attached_4g_lte",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_attached_4g_lte_waves_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_attached_4g_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_attached_4g_waves_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_ic_qs_signal_attached_4g_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_ic_qs_signal_attached_e", "stat_sys_data_connected_e");
		DATA.put("zz_moto_ic_qs_signal_attached_e_waves_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_ic_qs_signal_attached_e_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_ic_qs_signal_attached_g", "stat_sys_data_connected_g");
		DATA.put("zz_moto_ic_qs_signal_attached_g_waves_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_ic_qs_signal_attached_g_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_ic_qs_signal_attached_h", "stat_sys_data_connected_h");
		DATA.put("zz_moto_ic_qs_signal_attached_h_wide",
				"stat_sys_data_connected_h");
		DATA.put("zz_moto_ic_qs_signal_attached_hplus",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_ic_qs_signal_attached_hplus_wide",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_ic_qs_signal_attached_lte",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_attached_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_disabled_1x",
				"stat_sys_data_disabled_1x");
		DATA.put("zz_moto_ic_qs_signal_disabled_1x_wide",
				"stat_sys_data_disabled_1x");
		DATA.put("zz_moto_ic_qs_signal_disabled_2g",
				"stat_sys_data_disabled_2g");
		DATA.put("zz_moto_ic_qs_signal_disabled_2g_wide",
				"stat_sys_data_disabled_2g");
		DATA.put("zz_moto_ic_qs_signal_disabled_3g",
				"stat_sys_data_disabled_3g");
		DATA.put("zz_moto_ic_qs_signal_disabled_3g_plus",
				"stat_sys_data_disabled_3gplus");
		DATA.put("zz_moto_ic_qs_signal_disabled_3g_plus_wide",
				"stat_sys_data_disabled_3gplus");
		DATA.put("zz_moto_ic_qs_signal_disabled_3g_wide",
				"stat_sys_data_disabled_3g");
		DATA.put("zz_moto_ic_qs_signal_disabled_4g",
				"stat_sys_data_disabled_4g");
		DATA.put("zz_moto_ic_qs_signal_disabled_4g_lte",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_ic_qs_signal_disabled_4g_lte_wide",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_ic_qs_signal_disabled_4g_wide",
				"stat_sys_data_disabled_4g");
		DATA.put("zz_moto_ic_qs_signal_disabled_e", "stat_sys_data_disabled_e");
		DATA.put("zz_moto_ic_qs_signal_disabled_e_wide",
				"stat_sys_data_disabled_e");
		DATA.put("zz_moto_ic_qs_signal_disabled_g", "stat_sys_data_disabled_g");
		DATA.put("zz_moto_ic_qs_signal_disabled_g_wide",
				"stat_sys_data_disabled_g");
		DATA.put("zz_moto_ic_qs_signal_disabled_h", "stat_sys_data_disabled_h");
		DATA.put("zz_moto_ic_qs_signal_disabled_h_wide",
				"stat_sys_data_disabled_h");
		DATA.put("zz_moto_ic_qs_signal_disabled_hplus",
				"stat_sys_data_disabled_hplus");
		DATA.put("zz_moto_ic_qs_signal_disabled_hplus_wide",
				"stat_sys_data_disabled_hplus");
		DATA.put("zz_moto_ic_qs_signal_disabled_lte",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_ic_qs_signal_disabled_lte_wide",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_ic_qs_signal_e", "stat_sys_data_connected_e");
		DATA.put("zz_moto_ic_qs_signal_e_waves_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_ic_qs_signal_e_wide", "stat_sys_data_connected_e");
		DATA.put("zz_moto_ic_qs_signal_full_1x", "stat_sys_data_connected_1x");
		DATA.put("zz_moto_ic_qs_signal_full_1x_wide",
				"stat_sys_data_connected_1x");
		DATA.put("zz_moto_ic_qs_signal_full_2g", "stat_sys_data_connected_2g");
		DATA.put("zz_moto_ic_qs_signal_full_2g_wide",
				"stat_sys_data_connected_2g");
		DATA.put("zz_moto_ic_qs_signal_full_3g", "stat_sys_data_connected_3g");
		DATA.put("zz_moto_ic_qs_signal_full_3g_plus",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_ic_qs_signal_full_3g_plus_wide",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_ic_qs_signal_full_3g_wide",
				"stat_sys_data_connected_3g");
		DATA.put("zz_moto_ic_qs_signal_full_4g", "stat_sys_data_connected_4g");
		DATA.put("zz_moto_ic_qs_signal_full_4g_lte",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_full_4g_lte_waves_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_full_4g_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_full_4g_waves_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_ic_qs_signal_full_4g_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_ic_qs_signal_full_e", "stat_sys_data_connected_e");
		DATA.put("zz_moto_ic_qs_signal_full_e_waves_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_ic_qs_signal_full_e_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_ic_qs_signal_full_g", "stat_sys_data_connected_g");
		DATA.put("zz_moto_ic_qs_signal_full_g_waves_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_ic_qs_signal_full_g_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_ic_qs_signal_full_h", "stat_sys_data_connected_h");
		DATA.put("zz_moto_ic_qs_signal_full_h_wide",
				"stat_sys_data_connected_h");
		DATA.put("zz_moto_ic_qs_signal_full_hplus",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_ic_qs_signal_full_hplus_wide",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_ic_qs_signal_full_lte", "stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_full_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_g", "stat_sys_data_connected_g");
		DATA.put("zz_moto_ic_qs_signal_g_waves_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_ic_qs_signal_g_wide", "stat_sys_data_connected_g");
		DATA.put("zz_moto_ic_qs_signal_h", "stat_sys_data_connected_h");
		DATA.put("zz_moto_ic_qs_signal_h_wide", "stat_sys_data_connected_h");
		DATA.put("zz_moto_ic_qs_signal_hplus", "stat_sys_data_connected_hplus");
		DATA.put("zz_moto_ic_qs_signal_hplus_wide",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_ic_qs_signal_lte", "stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_lte_wide", "stat_sys_data_connected_lte");
		DATA.put("zz_moto_ic_qs_signal_suspended_1x",
				"stat_sys_data_disabled_1x");
		DATA.put("zz_moto_ic_qs_signal_suspended_1x_wide",
				"stat_sys_data_disabled_1x");
		DATA.put("zz_moto_ic_qs_signal_suspended_2g",
				"stat_sys_data_disabled_2g");
		DATA.put("zz_moto_ic_qs_signal_suspended_2g_wide",
				"stat_sys_data_disabled_2g");
		DATA.put("zz_moto_ic_qs_signal_suspended_3g",
				"stat_sys_data_disabled_3g");
		DATA.put("zz_moto_ic_qs_signal_suspended_3g_plus",
				"stat_sys_data_disabled_3gplus");
		DATA.put("zz_moto_ic_qs_signal_suspended_3g_plus_wide",
				"stat_sys_data_disabled_3gplus");
		DATA.put("zz_moto_ic_qs_signal_suspended_3g_wide",
				"stat_sys_data_disabled_3g");
		DATA.put("zz_moto_ic_qs_signal_suspended_4g",
				"stat_sys_data_disabled_4g");
		DATA.put("zz_moto_ic_qs_signal_suspended_4g_lte",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_ic_qs_signal_suspended_4g_lte_waves_wide",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_ic_qs_signal_suspended_4g_lte_wide",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_ic_qs_signal_suspended_4g_waves_wide",
				"stat_sys_data_disabled_4g");
		DATA.put("zz_moto_ic_qs_signal_suspended_4g_wide",
				"stat_sys_data_disabled_4g");
		DATA.put("zz_moto_ic_qs_signal_suspended_e", "stat_sys_data_disabled_e");
		DATA.put("zz_moto_ic_qs_signal_suspended_e_waves_wide",
				"stat_sys_data_disabled_e");
		DATA.put("zz_moto_ic_qs_signal_suspended_e_wide",
				"stat_sys_data_disabled_e");
		DATA.put("zz_moto_ic_qs_signal_suspended_g", "stat_sys_data_disabled_g");
		DATA.put("zz_moto_ic_qs_signal_suspended_g_waves_wide",
				"stat_sys_data_disabled_g");
		DATA.put("zz_moto_ic_qs_signal_suspended_g_wide",
				"stat_sys_data_disabled_g");
		DATA.put("zz_moto_ic_qs_signal_suspended_h", "stat_sys_data_disabled_h");
		DATA.put("zz_moto_ic_qs_signal_suspended_h_wide",
				"stat_sys_data_disabled_h");
		DATA.put("zz_moto_ic_qs_signal_suspended_hplus",
				"stat_sys_data_disabled_hplus");
		DATA.put("zz_moto_ic_qs_signal_suspended_hplus_wide",
				"stat_sys_data_disabled_hplus");
		DATA.put("zz_moto_ic_qs_signal_suspended_lte",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_ic_qs_signal_suspended_lte_wide",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_stat_sys_data_attached_1x",
				"stat_sys_data_connected_1x");
		DATA.put("zz_moto_stat_sys_data_attached_1x_wide",
				"stat_sys_data_connected_1x");
		DATA.put("zz_moto_stat_sys_data_attached_2g",
				"stat_sys_data_connected_2g");
		DATA.put("zz_moto_stat_sys_data_attached_2g_wide",
				"stat_sys_data_connected_2g");
		DATA.put("zz_moto_stat_sys_data_attached_3g",
				"stat_sys_data_connected_3g");
		DATA.put("zz_moto_stat_sys_data_attached_3g_plus",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_stat_sys_data_attached_3g_plus_wide",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_stat_sys_data_attached_3g_wide",
				"stat_sys_data_connected_3g");
		DATA.put("zz_moto_stat_sys_data_attached_4g",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_stat_sys_data_attached_4g_lte",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_attached_4g_lte_waves_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_attached_4g_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_attached_4g_waves_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_stat_sys_data_attached_4g_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_stat_sys_data_attached_e",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_stat_sys_data_attached_e_waves_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_stat_sys_data_attached_e_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_stat_sys_data_attached_g",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_stat_sys_data_attached_g_waves_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_stat_sys_data_attached_g_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_stat_sys_data_attached_h",
				"stat_sys_data_connected_h");
		DATA.put("zz_moto_stat_sys_data_attached_h_wide",
				"stat_sys_data_connected_h");
		DATA.put("zz_moto_stat_sys_data_attached_hplus",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_stat_sys_data_attached_hplus_wide",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_stat_sys_data_attached_lte",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_attached_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_connected_1x",
				"stat_sys_data_connected_1x");
		DATA.put("zz_moto_stat_sys_data_connected_1x_wide",
				"stat_sys_data_connected_1x");
		DATA.put("zz_moto_stat_sys_data_connected_2g",
				"stat_sys_data_connected_2g");
		DATA.put("zz_moto_stat_sys_data_connected_2g_wide",
				"stat_sys_data_connected_2g");
		DATA.put("zz_moto_stat_sys_data_connected_3g",
				"stat_sys_data_connected_3g");
		DATA.put("zz_moto_stat_sys_data_connected_3g_plus",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_stat_sys_data_connected_3g_plus_wide",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_stat_sys_data_connected_3g_wide",
				"stat_sys_data_connected_3g");
		DATA.put("zz_moto_stat_sys_data_connected_4g",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_stat_sys_data_connected_4g_lte",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_connected_4g_lte_waves_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_connected_4g_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_connected_4g_waves_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_stat_sys_data_connected_4g_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_stat_sys_data_connected_e",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_stat_sys_data_connected_e_waves_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_stat_sys_data_connected_e_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_stat_sys_data_connected_g",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_stat_sys_data_connected_g_waves_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_stat_sys_data_connected_g_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_stat_sys_data_connected_h",
				"stat_sys_data_connected_h");
		DATA.put("zz_moto_stat_sys_data_connected_h_wide",
				"stat_sys_data_connected_h");
		DATA.put("zz_moto_stat_sys_data_connected_hplus",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_stat_sys_data_connected_hplus_wide",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_stat_sys_data_connected_lte",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_connected_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_disabled_1x",
				"stat_sys_data_disabled_1x");
		DATA.put("zz_moto_stat_sys_data_disabled_1x_wide",
				"stat_sys_data_disabled_1x");
		DATA.put("zz_moto_stat_sys_data_disabled_2g",
				"stat_sys_data_disabled_2g");
		DATA.put("zz_moto_stat_sys_data_disabled_2g_wide",
				"stat_sys_data_disabled_2g");
		DATA.put("zz_moto_stat_sys_data_disabled_3g",
				"stat_sys_data_disabled_3g");
		DATA.put("zz_moto_stat_sys_data_disabled_3g_plus",
				"stat_sys_data_disabled_3gplus");
		DATA.put("zz_moto_stat_sys_data_disabled_3g_plus_wide",
				"stat_sys_data_disabled_3gplus");
		DATA.put("zz_moto_stat_sys_data_disabled_3g_wide",
				"stat_sys_data_disabled_3g");
		DATA.put("zz_moto_stat_sys_data_disabled_4g",
				"stat_sys_data_disabled_4g");
		DATA.put("zz_moto_stat_sys_data_disabled_4g_lte",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_stat_sys_data_disabled_4g_lte_wide",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_stat_sys_data_disabled_4g_wide",
				"stat_sys_data_disabled_4g");
		DATA.put("zz_moto_stat_sys_data_disabled_e", "stat_sys_data_disabled_e");
		DATA.put("zz_moto_stat_sys_data_disabled_e_wide",
				"stat_sys_data_disabled_e");
		DATA.put("zz_moto_stat_sys_data_disabled_g", "stat_sys_data_disabled_g");
		DATA.put("zz_moto_stat_sys_data_disabled_g_wide",
				"stat_sys_data_disabled_g");
		DATA.put("zz_moto_stat_sys_data_disabled_h", "stat_sys_data_disabled_h");
		DATA.put("zz_moto_stat_sys_data_disabled_h_wide",
				"stat_sys_data_disabled_h");
		DATA.put("zz_moto_stat_sys_data_disabled_hplus",
				"stat_sys_data_disabled_hplus");
		DATA.put("zz_moto_stat_sys_data_disabled_hplus_wide",
				"stat_sys_data_disabled_hplus");
		DATA.put("zz_moto_stat_sys_data_disabled_lte",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_stat_sys_data_disabled_lte_wide",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_stat_sys_data_disabled_wide",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_stat_sys_data_fully_connected_1x",
				"stat_sys_data_connected_1x");
		DATA.put("zz_moto_stat_sys_data_fully_connected_1x_wide",
				"stat_sys_data_connected_1x");
		DATA.put("zz_moto_stat_sys_data_fully_connected_2g",
				"stat_sys_data_connected_2g");
		DATA.put("zz_moto_stat_sys_data_fully_connected_2g_wide",
				"stat_sys_data_connected_2g");
		DATA.put("zz_moto_stat_sys_data_fully_connected_3g",
				"stat_sys_data_connected_3g");
		DATA.put("zz_moto_stat_sys_data_fully_connected_3g_plus",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_stat_sys_data_fully_connected_3g_plus_wide",
				"stat_sys_data_connected_3gplus");
		DATA.put("zz_moto_stat_sys_data_fully_connected_3g_wide",
				"stat_sys_data_connected_3g");
		DATA.put("zz_moto_stat_sys_data_fully_connected_4g",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_stat_sys_data_fully_connected_4g_lte",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_fully_connected_4g_lte_waves_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_fully_connected_4g_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_fully_connected_4g_waves_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_stat_sys_data_fully_connected_4g_wide",
				"stat_sys_data_connected_4g");
		DATA.put("zz_moto_stat_sys_data_fully_connected_e",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_stat_sys_data_fully_connected_e_waves_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_stat_sys_data_fully_connected_e_wide",
				"stat_sys_data_connected_e");
		DATA.put("zz_moto_stat_sys_data_fully_connected_g",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_stat_sys_data_fully_connected_g_waves_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_stat_sys_data_fully_connected_g_wide",
				"stat_sys_data_connected_g");
		DATA.put("zz_moto_stat_sys_data_fully_connected_h",
				"stat_sys_data_connected_h");
		DATA.put("zz_moto_stat_sys_data_fully_connected_h_wide",
				"stat_sys_data_connected_h");
		DATA.put("zz_moto_stat_sys_data_fully_connected_hplus",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_stat_sys_data_fully_connected_hplus_wide",
				"stat_sys_data_connected_hplus");
		DATA.put("zz_moto_stat_sys_data_fully_connected_lte",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_fully_connected_lte_wide",
				"stat_sys_data_connected_lte");
		DATA.put("zz_moto_stat_sys_data_suspended_1x",
				"stat_sys_data_disabled_1x");
		DATA.put("zz_moto_stat_sys_data_suspended_1x_wide",
				"stat_sys_data_disabled_1x");
		DATA.put("zz_moto_stat_sys_data_suspended_2g",
				"stat_sys_data_disabled_2g");
		DATA.put("zz_moto_stat_sys_data_suspended_2g_wide",
				"stat_sys_data_disabled_2g");
		DATA.put("zz_moto_stat_sys_data_suspended_3g",
				"stat_sys_data_disabled_3g");
		DATA.put("zz_moto_stat_sys_data_suspended_3g_plus",
				"stat_sys_data_disabled_3gplus");
		DATA.put("zz_moto_stat_sys_data_suspended_3g_plus_wide",
				"stat_sys_data_disabled_3gplus");
		DATA.put("zz_moto_stat_sys_data_suspended_3g_wide",
				"stat_sys_data_disabled_3g");
		DATA.put("zz_moto_stat_sys_data_suspended_4g",
				"stat_sys_data_disabled_4g");
		DATA.put("zz_moto_stat_sys_data_suspended_4g_lte",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_stat_sys_data_suspended_4g_lte_waves_wide",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_stat_sys_data_suspended_4g_lte_wide",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_stat_sys_data_suspended_4g_waves_wide",
				"stat_sys_data_disabled_4g");
		DATA.put("zz_moto_stat_sys_data_suspended_4g_wide",
				"stat_sys_data_disabled_4g");
		DATA.put("zz_moto_stat_sys_data_suspended_e",
				"stat_sys_data_disabled_e");
		DATA.put("zz_moto_stat_sys_data_suspended_e_waves_wide",
				"stat_sys_data_disabled_e");
		DATA.put("zz_moto_stat_sys_data_suspended_e_wide",
				"stat_sys_data_disabled_e");
		DATA.put("zz_moto_stat_sys_data_suspended_g",
				"stat_sys_data_disabled_g");
		DATA.put("zz_moto_stat_sys_data_suspended_g_waves_wide",
				"stat_sys_data_disabled_g");
		DATA.put("zz_moto_stat_sys_data_suspended_g_wide",
				"stat_sys_data_disabled_g");
		DATA.put("zz_moto_stat_sys_data_suspended_h",
				"stat_sys_data_disabled_h");
		DATA.put("zz_moto_stat_sys_data_suspended_h_wide",
				"stat_sys_data_disabled_h");
		DATA.put("zz_moto_stat_sys_data_suspended_hplus",
				"stat_sys_data_disabled_hplus");
		DATA.put("zz_moto_stat_sys_data_suspended_hplus_wide",
				"stat_sys_data_disabled_hplus");
		DATA.put("zz_moto_stat_sys_data_suspended_lte",
				"stat_sys_data_disabled_lte");
		DATA.put("zz_moto_stat_sys_data_suspended_lte_wide",
				"stat_sys_data_disabled_lte");

		// XXX
		WIFI.put("stat_sys_wifi_signal_null", "stat_sys_wifi_signal_0");
		WIFI.put("stat_sys_wifi_signal_0", "stat_sys_wifi_signal_0");
		WIFI.put("stat_sys_wifi_signal_0_fully", "stat_sys_wifi_signal_0");
		WIFI.put("stat_sys_wifi_signal_1", "stat_sys_wifi_signal_1");
		WIFI.put("stat_sys_wifi_signal_1_fully", "stat_sys_wifi_signal_1");
		WIFI.put("stat_sys_wifi_signal_2", "stat_sys_wifi_signal_2");
		WIFI.put("stat_sys_wifi_signal_2_fully", "stat_sys_wifi_signal_2");
		WIFI.put("stat_sys_wifi_signal_3", "stat_sys_wifi_signal_3");
		WIFI.put("stat_sys_wifi_signal_3_fully", "stat_sys_wifi_signal_3");
		WIFI.put("stat_sys_wifi_signal_4", "stat_sys_wifi_signal_4");
		WIFI.put("stat_sys_wifi_signal_4_fully", "stat_sys_wifi_signal_4");

		/** **/
		IC_SYSBAR.add("ic_sysbar_back");
		IC_SYSBAR.add("ic_sysbar_back_ime");
		IC_SYSBAR.add("ic_sysbar_back_land");
		IC_SYSBAR.add("ic_sysbar_home");
		IC_SYSBAR.add("ic_sysbar_home_land");
		IC_SYSBAR.add("ic_sysbar_menu");
		IC_SYSBAR.add("ic_sysbar_menu_land");
		IC_SYSBAR.add("ic_sysbar_recent");
		IC_SYSBAR.add("ic_sysbar_recent_land");

		STATUS.add("stat_sys_zen_important");
		STATUS.add("stat_sys_zen_none");
		STATUS.add("stat_notify_more");
		STATUS.add("stat_sys_alarm");
		STATUS.add("stat_sys_data_bluetooth");
		STATUS.add("stat_sys_data_bluetooth_connected");
		STATUS.add("stat_notify_image");
		STATUS.add("stat_notify_image_error");

		/** true **/
		BOOL.put("config_statusBarShowNumber", "true");
		BOOL.put("show_data_disabled_icon", "true");
		BOOL.put("config_hspap_data_distinguishable", "true");
		BOOL.put("show_brazil_settings", "true");
		BOOL.put("config_enable_carrier_custom_icons", "true");
		// BOOL.put("config_show_slow_charger_ui", "true");
		// BOOL.put("config_show_turbo_charger_ui", "true");
		// BOOL.put("config_show_separated_bars_in_signal_strength",
		// "true");
		// BOOL.put("config_allow_differentiated_inet_condition", "true");
		/** false **/
		// BOOL.put("config_enable_inet_condition_on_wide_statusbar_icons",
		// "false");
		// BOOL.put("config_enable_inet_condition_on_narrow_statusbar_icons",
		// "false");
		// BOOL.put("config_show_signal_bars_when_data_only_service",
		// "false");

	}

	private static Map<String, String> getListOtherPackageIcons() {
		Map<String, String> ret = new HashMap<String, String>();
		File iconOtherDir = new File(PATH_ICON_OTHER);

		if (iconOtherDir.isDirectory()) {
			File[] listFile = iconOtherDir.listFiles();

			for (File file : listFile) {
				String fullFileName = file.getName();
				if (fullFileName.endsWith("png")) {
					String[] fileName = fullFileName.split("#");
					String key = fileName[0]; // pkg
					String value = fileName[1].substring(0,
							fileName[1].length() - 4); // res
					ret.put(key, value);

				}
			}
		}
		return ret;
	}

	/** icon notification from other package **/
	private static void replaceOtherPackage(InitPackageResourcesParam resparam)
			throws Throwable {
		OTHER_NOTIF = getListOtherPackageIcons();
		if ((OTHER_NOTIF != null) && (OTHER_NOTIF.size() > 0)) {

			for (String pkg : OTHER_NOTIF.keySet()) {
				String res = OTHER_NOTIF.get(pkg);
				String png = String.format("%s#%s", pkg, res);
				if (pkg.equals(resparam.packageName)) {
					try {
						resparam.res.setReplacement(pkg, DRAWABLE, res,
								DrawUtils.getIcon(pref, PATH_ICON_OTHER, png));
					} catch (Throwable t) {
						MotoGrepModule.xLog(TAG,
								"replaceOther " + t.getMessage());
					}

				}
			}
		}

	}

	/** resource SystemUI **/
	private static void replaceResourceSytemUI(
			InitPackageResourcesParam resparam) {
		if (!resparam.packageName.equals(SYSTEMUI_PKG)) {
			return;
		}
		XResources xresource = resparam.res;
		try {

			for (String string : IC_SYSBAR) {
				if (isResAndPngExistedSysUI(xresource, string, string)) {
					xresource.setReplacement(SYSTEMUI_PKG, DRAWABLE, string,
							DrawUtils.getIcon(pref, PATH_ICON, string));
				}
			}

			for (String string : STATUS) {
				if (isResAndPngExistedSysUI(xresource, string, string)) {
					xresource.setReplacement(SYSTEMUI_PKG, DRAWABLE, string,
							DrawUtils.getIcon(pref, PATH_ICON, string));
				}
			}

			// FIXME
			for (String resName : DATA.keySet()) {
				String png = SIGNAL.get(resName);
				if (isResAndPngExistedSysUI(xresource, resName, png)) {
					xresource.setReplacement(SYSTEMUI_PKG, DRAWABLE, resName,
							DrawUtils.getIcon(pref, PATH_ICON, png));
				}

			}

			for (String resName : SIGNAL.keySet()) {
				String png = SIGNAL.get(resName);
				if (isResAndPngExistedSysUI(xresource, resName, png)) {
					xresource.setReplacement(SYSTEMUI_PKG, DRAWABLE, resName,
							DrawUtils.getIcon(pref, PATH_ICON, png));
				}
			}

			for (String resName : WIFI.keySet()) {
				String png = WIFI.get(resName);
				if (isResAndPngExistedSysUI(xresource, resName, png)) {
					xresource.setReplacement(SYSTEMUI_PKG, DRAWABLE, resName,
							DrawUtils.getIcon(pref, PATH_ICON, png));
				}
			}

			pref.reload();
			if (pref.getBoolean(PREF_KEY_ICON_DATA, false)) {
				for (String string : BOOL.keySet()) {
					boolean bool = Boolean.valueOf(BOOL.get(string));
					xresource
							.setReplacement(SYSTEMUI_PKG, "bool", string, bool);
				}
			}

		} catch (Throwable t) {
			MotoGrepModule
					.xLog(TAG, "replaceResourceSytemUI " + t.getMessage());
		}
	}

	private static Boolean isResAndPngExistedSysUI(Resources res,
			String resname, String pngname) {
		return isResExisted(SYSTEMUI_PKG, res, resname)
				&& isIconReplacementExisted(PATH_ICON, pngname);
	}

	private static Boolean isResExisted(String pkgName, Resources resources,
			String string) {
		return (resources.getIdentifier(string, DRAWABLE, pkgName) != 0);
	}

	private static Boolean isIconReplacementExisted(String stringPath,
			String name) {
		return ((File) new File(stringPath + "/" + name + ".png")).exists();
	}

	/** public Xposed method initZygote */
	public static void initZygote(XSharedPreferences xpref) throws Throwable {
		pref = xpref;
		setResListSystemUI();
	}

	/** public Xposed method handleLoadPackage */
	public static void handleLoadPackage(LoadPackageParam lpparam)
			throws Throwable {
		// TODO
	}

	/** public Xposed method handleInitPackageResources */
	public static void handleInitPackageResources(
			InitPackageResourcesParam resparam) throws Throwable {

		replaceResourceSytemUI(resparam);
		replaceOtherPackage(resparam);

	}
}
