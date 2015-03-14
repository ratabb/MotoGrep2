package org.illegaler.ratabb.motogrep.lollipop.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.illegaler.ratabb.motogrep.lollipop.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class AboutFragment extends Fragment implements OnClickListener {
	private final String TAG = "MG";
	private Context mContext;

	public AboutFragment(Context c) {
		mContext = c;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		((TextView) view.findViewById(R.id.version)).setText(String.format(
				getString(R.string.version), getString(R.string.app_ver)));
		((Button) view.findViewById(R.id.formatclock)).setOnClickListener(this);
		((Button) view.findViewById(R.id.thanks)).setOnClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.about, container, false);
	}

	private Dialog getDialog(View view, String title) {
		AlertDialog.Builder adBuilder = new AlertDialog.Builder(mContext);
		adBuilder.setTitle(title);
		adBuilder.setView(view);
		adBuilder.setNeutralButton(android.R.string.ok, null);
		return adBuilder.show();
	}

	private String getStringFromAssets(String asset) {
		StringBuilder sb = new StringBuilder();
		try {
			InputStream is = mContext.getAssets().open(asset,
					AssetManager.ACCESS_BUFFER);

			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String str;

			while ((str = br.readLine()) != null) {
				sb.append(str);
				sb.append("<br>");// html?"<br>":"\n"
			}
			br.close();
			is.close();
		} catch (IOException ioe) {
			Log.e(TAG, asset + ioe.getMessage());
			sb.append("");
		} catch (NullPointerException npe) {
			Log.e(TAG, asset + npe.getMessage());
			sb.append("");
		}

		return sb.toString();
	}

	private static final int KEY_JAM = 0;
	private static final int KEY_THANKS = 1;

	private View getView(int key) {
		ScrollView sv = new ScrollView(mContext);
		TextView ret = new TextView(mContext);
		ret.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		ret.setPaddingRelative(8, 8, 8, 8);
		ret.setTextAppearance(mContext, android.R.style.TextAppearance);
		ret.setHorizontallyScrolling(true);
		switch (key) {
		case KEY_JAM:
			ret.setText(Html.fromHtml(getStringFromAssets("SDF")));
			break;
		case KEY_THANKS:
			ret.setText(Html.fromHtml(getString(R.string.thanks_to_all)));
			ret.setMovementMethod(LinkMovementMethod.getInstance());
			break;
		default:
			break;
		}

		sv.addView(ret);
		return sv;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.formatclock:
			getDialog(getView(KEY_JAM), getString(R.string.clock_text));
			break;
		case R.id.thanks:
			getDialog(getView(KEY_THANKS), getString(R.string.thanksto_title));
			break;

		default:
			break;
		}

	}

}
