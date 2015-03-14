package org.illegaler.ratabb.motogrep.lollipop.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.illegaler.ratabb.motogrep.lollipop.R;
import org.illegaler.ratabb.motogrep.lollipop.TouchInterceptor;
import org.illegaler.ratabb.motogrep.lollipop.mod.Utils;
import static org.illegaler.ratabb.motogrep.lollipop.Constant.*;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class StyleFragment extends ListFragment {
	private Context mContext;
	private ListView mListView;
	private SharedPreferences mSPreferences;
	private Resources mResources;
	private CustomStyleAdapter mStyleAdapter;
	private Map<String, String> mTittleStyle;

	public StyleFragment(Context context, SharedPreferences pref) {
		mContext = context;
		mSPreferences = pref;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.custom_style, container, false);
	};

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mResources = mContext.getResources();
		mListView = getListView();

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

		if (!mSPreferences.contains(PREF_KEY_STYLE_ORDER)) {
			mSPreferences.edit().putString(PREF_KEY_STYLE_ORDER, DEFAULT_STYLE)
					.commit();
		}

	}

	@Override
	public void onDestroy() {
		((TouchInterceptor) mListView).setDropListener(null);
		mListView.setAdapter(null);
		super.onDestroy();
	}

	@Override
	public void onResume() {
		super.onResume();
		mStyleAdapter.reloadStyle();
		mListView.invalidateViews();
	}

	private void saveCurrentStyle(List<String> paramList) {
		String[] newList = new String[paramList.size()];
		newList = paramList.toArray(newList);
		final String value = Utils.join(newList, ",");
		mSPreferences.edit().putString(PREF_KEY_STYLE_ORDER, value).commit();

		Intent i = new Intent(ACTION_UPDATE);
		mContext.sendBroadcast(i);
	}

	private void invalidateList() {
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
			to = (to == 0) ? 1 : to;// dibawah LEFT
			if (from < mList.size()) {
				String tile = mList.remove(from);
				if (to <= mList.size()) {
					mList.add(to, tile);
					saveCurrentStyle(mList);
					invalidateList();
				}
			}
		}
	};

	private int getResIdByNameAndType(String name, String type) {
		return getResources().getIdentifier(name, type,
				mContext.getPackageName());
	}

	private List<String> getOrderStyle() {
		String style = mSPreferences.getString(PREF_KEY_STYLE_ORDER,
				DEFAULT_STYLE);
		return new ArrayList<String>(Arrays.asList(style.split(",")));
	}

	private class CustomStyleAdapter extends BaseAdapter {
		private static final int TYPE_HEADER = 0;
		private static final int TYPE_STYLE = 1;
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
					int id = ((Integer) getResIdByNameAndType("ic_pref_" + key,
							"drawable")).intValue();
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
