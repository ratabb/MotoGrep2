package org.illegaler.ratabb.motogrep.lollipop.mod;

import org.illegaler.ratabb.motogrep.lollipop.MotoGrepModule;

import static org.illegaler.ratabb.motogrep.lollipop.Constant.*;
import de.robv.android.xposed.XSharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class StatusBarBattery implements BroadcastSubReceiver {

	private static final String[] BATT_IMAGE = { "stat_sys_battery_0",
			"stat_sys_battery_15", "stat_sys_battery_28",
			"stat_sys_battery_43", "stat_sys_battery_57",
			"stat_sys_battery_71", "stat_sys_battery_85",
			"stat_sys_battery_100" };
	private static final String[] BATT_IMAGE_CHARGE = {
			"stat_sys_battery_charge_anim0", "stat_sys_battery_charge_anim15",
			"stat_sys_battery_charge_anim28", "stat_sys_battery_charge_anim43",
			"stat_sys_battery_charge_anim57", "stat_sys_battery_charge_anim71",
			"stat_sys_battery_charge_anim85", "stat_sys_battery_charge_anim100" };
	private static final String TAG = "SB_Battery";

	private boolean charging;
	private int level;
	private View mBattery;
	private ImageView mBatteryImage;
	private TextView mBatteryText;
	private Context mContext;
	private XSharedPreferences xpref;

	public StatusBarBattery(XSharedPreferences pref) {
		xpref = pref;

	}

	private void setViewBattery() {
		mBattery.setVisibility(View.GONE);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(1, 0, 1, 0);
		mBatteryImage.setLayoutParams(layoutParams);
		mBatteryImage.setAdjustViewBounds(true);
		mBatteryText.setLayoutParams(layoutParams);
		mBatteryText.setGravity(Gravity.CENTER_VERTICAL);

	}

	private void updateBattery() {
		String stringPath = PATH_ICON;
		String[] imgBatt = (charging) ? BATT_IMAGE_CHARGE : BATT_IMAGE;
		Drawable d = null;
		try {
			d = DrawUtils.getDrawable(xpref, stringPath,
					"stat_sys_battery_unknown");
			if (level <= 14) {
				d = DrawUtils.getDrawable(xpref, stringPath, imgBatt[0]);
			} else if (level >= 15 && level <= 27) {
				d = DrawUtils.getDrawable(xpref, stringPath, imgBatt[1]);
			} else if (level >= 28 && level <= 42) {
				d = DrawUtils.getDrawable(xpref, stringPath, imgBatt[2]);
			} else if (level >= 43 && level <= 56) {
				d = DrawUtils.getDrawable(xpref, stringPath, imgBatt[3]);
			} else if (level >= 57 && level <= 70) {
				d = DrawUtils.getDrawable(xpref, stringPath, imgBatt[4]);
			} else if (level >= 71 && level <= 84) {
				d = DrawUtils.getDrawable(xpref, stringPath, imgBatt[5]);
			} else if (level >= 85 && level <= 98) {
				d = DrawUtils.getDrawable(xpref, stringPath, imgBatt[6]);
			} else if (level >= 99) {
				d = DrawUtils.getDrawable(xpref, stringPath, imgBatt[7]);
			}

		} catch (Throwable t) {
			MotoGrepModule.xLog(TAG + "updateBattery " + t.getMessage());
		}
		mBatteryImage.setImageDrawable(d);
		updateBatteryText();
	}

	private void updateBatteryText() {
		String sLevel = String.valueOf(level);

		mBatteryText.setText(sLevel + "%");
	}

	/** public method */

	public ImageView getBatteryImage() {
		return mBatteryImage;
	}

	public TextView getBatteryText() {
		return mBatteryText;
	}

	public void initialize(View v) {
		mBattery = v;
		mContext = mBattery.getContext();
		mBatteryImage = new ImageView(mContext);
		mBatteryText = new TextView(mContext);
		setViewBattery();
	}

	@Override
	public void onBroadcastReceived(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
			int i = (int) (100.0f * intent.getIntExtra(
					BatteryManager.EXTRA_LEVEL, 0) / intent.getIntExtra(
					BatteryManager.EXTRA_SCALE, 100));
			int j = (int) intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
			boolean bool = (j != 0) ? true : false;
			if (level != i || charging != bool) {
				level = i;
				charging = bool;
				updateBattery();
			}
		}

	}

}
