package org.illegaler.ratabb.motogrep.lollipop.fragment;

import static org.illegaler.ratabb.motogrep.lollipop.Constant.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.illegaler.ratabb.motogrep.lollipop.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.os.Handler;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SettingFragment extends PreferenceFragment implements
		OnSharedPreferenceChangeListener, OnPreferenceClickListener {
	private SharedPreferences mSPreferences;
	private Context mContext;

	private Handler mHandler;
	private ProgressDialog mDialog;

	private EditTextPreference etClock;
	private Preference pRestartUI;

	public SettingFragment(Context context, SharedPreferences pref) {
		mContext = context;
		mSPreferences = pref;
	}

	@Override
	public void onResume() {
		super.onResume();
		pRestartUI.setOnPreferenceClickListener(this);
		mSPreferences.registerOnSharedPreferenceChangeListener(this);
		updateSumPreference(null);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		addPreferencesFromResource(R.xml.motogrep_setting);

		etClock = (EditTextPreference) findPreference(PREF_KEY_CLOCK_TEXT);
		pRestartUI = (Preference) findPreference(PREF_KEY_RESTART_UI);
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		dismissDialog();
		runHandler(key);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		if (preference.equals(pRestartUI)) {
			showRestartSystemUIDialog();
			return true;
		}

		return false;
	}

	private void updateSumPreference(String pref) {
		if ((pref == null) || (pref.equals(PREF_KEY_CLOCK_TEXT))) {
			etClock.setSummary(etClock.getText());
		}

	}

	private void showRestartSystemUIDialog() {
		AlertDialog.Builder d = new AlertDialog.Builder(mContext);
		d.setTitle(R.string.restart_ui);
		d.setMessage(R.string.restart_menu_message);
		d.setNegativeButton(android.R.string.cancel, null);
		d.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(ACTION_RESTART_SYSTEMUI);
						mContext.sendBroadcast(intent);
						dialog.dismiss();
					}
				});
		d.show();
	}

	private void dismissDialog() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
		mDialog = null;
	}

	private ProgressDialog getProgressDialog(Context c) {
		ProgressDialog ret = new ProgressDialog(c);
		ret.setTitle("Wait");
		ret.setMessage("Loading...");
		ret.setIndeterminate(true);

		return ret;
	}

	private void runHandler(String string) {
		mHandler = new Handler();
		mDialog = getProgressDialog(mContext);
		mDialog.show();
		updateSumPreference(string);
		Intent intent = null;

		if (string.equals(PREF_KEY_CLOCK_TEXT)) { // clocktext
			if (isFalidFormat(etClock.getText().toString())) {
				intent = new Intent(ACTION_CLOCK_TEXT);
			} else {
				etClock.setText(getString(R.string.default_clock));
				Toast.makeText(mContext, "Input wrong", Toast.LENGTH_SHORT)
						.show();
			}
		}
		final Intent i = intent;
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (i != null) {
					dismissDialog();
					mContext.sendBroadcast(i);
				}
			}
		}, 1000);
	}

	private boolean isFalidFormat(String string) {
		if (string.contains("^"))
			string = string.replace("^", "");

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(string,
					Locale.getDefault());
			sdf.format(new Date());
			return true;
		} catch (NullPointerException npe) {
			return false;
		} catch (IllegalArgumentException iae) {
			return false;
		}
	}
}
