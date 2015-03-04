package org.illegaler.ratabb.motogrep.lollipop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import static org.illegaler.ratabb.motogrep.lollipop.Constant.*;

public class MotoGrepActivity extends ListActivity {

	private Context mContext;
	private ListView mListView;
	private SharedPreferences mPref;
	private Resources mResources;
	private CustomStyleAdapter mStyleAdapter;
	private Map<String, String> mTittleStyle;

	private NinePatchDrawable backgroundActionBar;

	private BroadcastReceiver reciver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(ACTION_SAVE_PREF)) {
				// TODO save pref from mod
				// EXTRA ={ PREF_KEY, PREF_VALUE}
				String[] stringExtra = intent
						.getStringArrayExtra(EXTRA_SAVE_PREF);
				if (stringExtra != null) {
					mPref.edit().putString(stringExtra[0], stringExtra[1])
							.commit();
				}
			}
		}
	};

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_style);

		mContext = getApplicationContext();
		mResources = mContext.getResources();
		mListView = getListView();

		backgroundActionBar = (NinePatchDrawable) mResources
				.getDrawable(R.drawable.status_bar_background);

		getActionBar().setBackgroundDrawable(backgroundActionBar);
		getActionBar().setSubtitle(R.string.app_desc);

		String str = mContext.getPackageName() + "_preferences";

		mPref = mContext.getSharedPreferences(str, Context.MODE_WORLD_READABLE);
		mStyleAdapter = new CustomStyleAdapter(mContext);
		mListView.setAdapter(mStyleAdapter);
		((TouchInterceptor) this.mListView).setDropListener(mDropListener);

		String[] allKeyStyle = mResources
				.getStringArray(R.array.customstyle_value);
		String[] allNameStyle = mResources
				.getStringArray(R.array.customstyle_entries);

		mTittleStyle = new HashMap<String, String>();

		for (int i = 0; i < allKeyStyle.length; i++) {
			mTittleStyle.put(allKeyStyle[i], allNameStyle[i]);

		}

		if (!mPref.contains(PREF_KEY_STYLE_ORDER)) {

			mPref.edit().putString(PREF_KEY_STYLE_ORDER, DEFAULT_STYLE)
					.commit();
		}

	}

	@Override
	protected void onDestroy() {
		((TouchInterceptor) mListView).setDropListener(null);
		mListView.setAdapter(null);
		mContext.unregisterReceiver(reciver);
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mStyleAdapter.reloadStyle();
		mListView.invalidateViews();

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ACTION_SAVE_PREF);
		mContext.registerReceiver(reciver, intentFilter);
	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.restart_ui:
			Intent intent = new Intent(ACTION_RESTART_SYSTEMUI);
			mContext.sendBroadcast(intent);
			return true;
		case R.id.exit:
			finish();
			return true;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void saveCurrentStyle(List<String> paramList) {
		String[] newList = new String[paramList.size()];
		newList = paramList.toArray(newList);
		final String value = Utils.join(newList, ",");
		mPref.edit().putString(PREF_KEY_STYLE_ORDER, value).commit();

		Intent i = new Intent(ACTION_UPDATE);
		mContext.sendBroadcast(i);
	}

	private void forceInvalidateList() {
		mStyleAdapter = new CustomStyleAdapter(mContext);
		mListView.setAdapter(mStyleAdapter);
		((TouchInterceptor) this.mListView).setDropListener(mDropListener);
		mStyleAdapter.reloadStyle();
		mListView.invalidateViews();
	}

	private TouchInterceptor.DropListener mDropListener = new TouchInterceptor.DropListener() {

		@Override
		public void drop(int from, int to) {

			List<String> mList = getOrderStyle();
			if (to == 0) {
				to = 1;
			}

			if (from < mList.size()) {
				String tile = mList.remove(from);
				if (to <= mList.size()) {
					mList.add(to, tile);

					saveCurrentStyle(mList);
					forceInvalidateList();

				}
			}

		}
	};

	private int getResIdByNameAndType(String name, String type) {
		return getResources().getIdentifier(name, type, getPackageName());
	}

	private List<String> getOrderStyle() {
		String style = mPref.getString(PREF_KEY_STYLE_ORDER, DEFAULT_STYLE);
		return new ArrayList<String>(Arrays.asList(style.split(",")));
	}

	private class CustomStyleAdapter extends BaseAdapter {
		private static final int TYPE_HEADER = 0x101;
		private static final int TYPE_STYLE = 0x102;
		private Context mContext;
		private LayoutInflater mInflater;
		private List<String> mStyle;

		public void reloadStyle() {
			mStyle = getOrderStyle();
		}

		public CustomStyleAdapter(Context paramContext) {
			mContext = paramContext;
			mInflater = LayoutInflater.from(mContext);
			reloadStyle();

		}

		@Override
		public int getCount() {
			return this.mStyle.size();
		}

		@Override
		public Object getItem(int position) {
			return mStyle.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		public int getItemViewType(String string) {
			int ret = 0;
			String[] arrHeadTitle = { getString(R.string.customstyle_left),
					getString(R.string.customstyle_center),
					getString(R.string.customstyle_right) };

			if (string.equals(arrHeadTitle[0])
					|| string.equals(arrHeadTitle[1])
					|| string.equals(arrHeadTitle[2])) {

				ret = TYPE_HEADER;
			} else {
				ret = TYPE_STYLE;
			}

			return ret;
		}

		@SuppressLint({ "InflateParams", "DefaultLocale" })
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = null;

			String str = (String) mTittleStyle.get(mStyle.get(position));
			String key = (String) mStyle.get(position);

			int type = getItemViewType(str);
			if (convertView == null) {
				if (type == TYPE_HEADER) {

					v = mInflater.inflate(R.layout.header_list, null);
					((TextView) v.findViewById(R.id.head_text)).setText(str
							.toUpperCase());
					parent.setEnabled(false);
					return v;
				} else {

					v = mInflater.inflate(R.layout.order_list, null);

					((TextView) v.findViewById(R.id.name)).setText(str
							.toUpperCase());

					int id = ((Integer) getResIdByNameAndType("ic_pref_"
							+ key, "drawable")).intValue();

					((ImageView) v.findViewById(R.id.icon))
							.setImageResource(id);

					return v;
				}

			} else {
				v = convertView;
			}
			return v;
		}
	}
}
