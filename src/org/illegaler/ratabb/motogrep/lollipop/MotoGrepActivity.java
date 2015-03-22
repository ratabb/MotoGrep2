package org.illegaler.ratabb.motogrep.lollipop;

import static org.illegaler.ratabb.motogrep.lollipop.Constant.*;

import org.illegaler.ratabb.motogrep.lollipop.fragment.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MotoGrepActivity extends Activity implements
		MenuAdapter.OnItemClickListener {
	private SharedPreferences mSPreferences;
	private DrawerLayout mDrawerLayout;
	private RecyclerView mDrawerList;
	private ActionBarDrawerToggle mDrawerTogle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mDataMenus;

	@SuppressWarnings("deprecation")
	@SuppressLint("WorldReadableFiles")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.motogrep_activity);

		mTitle = mDrawerTitle = getTitle();
		mDataMenus = getResources().getStringArray(R.array.menus);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.rootView);
		mDrawerList = (RecyclerView) findViewById(R.id.menu_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

		mDrawerList.setHasFixedSize(true);
		mDrawerList.setLayoutManager(new LinearLayoutManager(this));
		mDrawerList.setAdapter(new MenuAdapter(mDataMenus, this));

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerTogle = getActionBarDrawerToggle();

		mDrawerLayout.setDrawerListener(mDrawerTogle);

		mSPreferences = getSharedPreferences(getPackageName() + "_preferences",
				Context.MODE_WORLD_READABLE);
		setDensity();
		if (savedInstanceState == null) {
			selectItem(getString(R.string.menu_about));
		}
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
			mDrawerLayout.closeDrawer(mDrawerList);
			return;
		}
		super.onBackPressed();
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerTogle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerTogle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerTogle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View view, int position) {
		selectItem(position);
	}

	private void selectItem(int position) {
		selectItem(mDataMenus[position]);
	}

	private void selectItem(CharSequence menu) {
		Object fragment = null;
		if (menu.equals(getString(R.string.menu_style))) {
			fragment = new StyleFragment(this, mSPreferences);
		} else if (menu.equals(getString(R.string.menu_setting))) {
			fragment = new SettingFragment(this, mSPreferences);
		} else if (menu.equals(getString(R.string.menu_theme))) {
			// TODO
			comingSoon();
		} else if (menu.equals(getString(R.string.menu_backup))) {
			// TODO
			comingSoon();
		} else if (menu.equals(getString(R.string.menu_online))) {
			// TODO
			comingSoon();
		} else if (menu.equals(getString(R.string.menu_about))) {
			fragment = new AboutFragment(this);
		}

		if (fragment != null) {
			getFragmentManager().beginTransaction()
					.replace(R.id.content_frame, (Fragment) fragment).commit();
		}
		setTitle(menu);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	// XXX
	private void comingSoon() {
		Toast.makeText(this, "coming soon *7o7*", Toast.LENGTH_SHORT).show();
	}

	private void setDensity() {
		int d = getApplicationContext().getResources().getConfiguration().densityDpi;
		mSPreferences.edit().putInt(PREF_KEY_DENSITY, d).commit();
	}

	private ActionBarDrawerToggle getActionBarDrawerToggle() {
		return new ActionBarDrawerToggle(this, mDrawerLayout,
				R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
	}
}
