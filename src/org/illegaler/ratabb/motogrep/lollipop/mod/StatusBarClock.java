package org.illegaler.ratabb.motogrep.lollipop.mod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.illegaler.ratabb.motogrep.lollipop.MotoGrepModule;
import static org.illegaler.ratabb.motogrep.lollipop.Constant.*;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class StatusBarClock implements BroadcastSubReceiver {
	private static final String TAG = "SB_Clock";
	private static final String key = "sbClock";
	private TextView mClock;
	private XSharedPreferences xpref;

	public StatusBarClock(XSharedPreferences pref) {
		xpref = pref;
	}

	private String getStringClock() {
		xpref.reload();
		String txt = xpref.getString(PREF_KEY_CLOCK_TEXT, DEFAULT_CLOCK);
		txt = txt.isEmpty() ? DEFAULT_CLOCK : txt;
		boolean uppercase = txt.contains("^") ? true : false;
		String ok = (uppercase) ? txt.replace("^", "") : txt;
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(ok, Locale.US);
		} catch (NullPointerException npe) {
			sdf = new SimpleDateFormat(DEFAULT_CLOCK, Locale.US);
		} catch (IllegalArgumentException iae) {
			sdf = new SimpleDateFormat(DEFAULT_CLOCK, Locale.US);
		}

		String currentDateTime = (uppercase) ? sdf.format(new Date())
				.toUpperCase(Locale.US) : sdf.format(new Date());

		return currentDateTime;
	}

	private void hookClock() {
		try {
			XposedHelpers.findAndHookMethod(mClock.getClass(), "getSmallTime",
					new XC_MethodHook() {
						@Override
						protected void afterHookedMethod(MethodHookParam param)
								throws Throwable {
							Object sbClock = XposedHelpers
									.getAdditionalInstanceField(
											param.thisObject, key);
							if (sbClock != null) {
								param.setResult(getStringClock());
							}

						}
					});
		} catch (Throwable t) {
			MotoGrepModule.xLog(TAG + "hookClock " + t.getMessage());
		}

	}

	private void updateClock() {
		// TODO
		mClock.setText(getStringClock());
	}

	/** public method */
	public TextView getClock() {
		return mClock;
	}

	public void initialize(TextView paramTextView) {
		mClock = paramTextView;
		XposedHelpers.setAdditionalInstanceField(mClock, key, true);
		hookClock();
		updateClock();
	}

	@Override
	public void onBroadcastReceived(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_TIME_TICK)
				|| action.equals(Intent.ACTION_TIME_CHANGED)
				|| action.equals(Intent.ACTION_TIMEZONE_CHANGED)
				|| action.equals(ACTION_CLOCK_TEXT)) {
			updateClock();

		}
	}

}
