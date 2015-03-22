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
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StatusBarClock implements BroadcastSubReceiver {
	private static final String TAG = "SBClock";
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

		final SimpleDateFormat defaultSdf = new SimpleDateFormat(DEFAULT_CLOCK,
				Locale.US);
		Date now = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(ok, Locale.US);
			return (uppercase) ? sdf.format(now).toUpperCase(Locale.US) : sdf
					.format(now);
		} catch (NullPointerException npe) {
			return defaultSdf.format(now);
		} catch (IllegalArgumentException iae) {
			return defaultSdf.format(now);
		}

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
			MotoGrepModule.xLog(TAG, "hookClock " + t.getMessage());
		}

	}

	private void updateClock() {
		mClock.setText(getStringClock());
	}

	private void setViewClock(Context context) {
		LinearLayout.LayoutParams lParamsText = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.MATCH_PARENT);

		int mMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources()
						.getDisplayMetrics());
		lParamsText.setMarginStart(mMargin);
		lParamsText.setMarginEnd(mMargin);
		mClock.setLayoutParams(lParamsText);
		mClock.setGravity(Gravity.CENTER_VERTICAL);
		mClock.setTextAppearance(
				context,
				context.getResources()
						.getIdentifier("TextAppearance.StatusBar.Clock",
								"style", SYSTEMUI_PKG));
	}

	/** public method */
	public TextView getClock() {
		return mClock;
	}

	public void initialize(TextView paramTextView) {
		mClock = paramTextView;
		setViewClock(mClock.getContext());
		XposedHelpers.setAdditionalInstanceField(mClock, key, true);
		hookClock();
		updateClock();
	}

	@Override
	public void onBroadcastReceived(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_TIME_TICK)
				|| action.equals(Intent.ACTION_TIME_CHANGED)
				|| action.equals(Intent.ACTION_TIMEZONE_CHANGED)
				|| action.equals(ACTION_CLOCK_TEXT)) {
			updateClock();
		}
	}

}
