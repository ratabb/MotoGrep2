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

public class MotoGrepActivity extends Activity implements
		MenuAdapter.OnItemClickListener {
	private SharedPreferences mSPreferences;
	private DrawerLayout mDrawerLayout;
	private RecyclerView mDrawerList;
	private ActionBarDrawerToggle mDrawerTogle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] menus;

	@SuppressWarnings("deprecation")
	@SuppressLint("WorldReadableFiles")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.motogrep_activity);

		mTitle = mDrawerTitle = getTitle();
		menus = getResources().getStringArray(R.array.menus);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.rootView);
		mDrawerList = (RecyclerView) findViewById(R.id.menu_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

		mDrawerList.setHasFixedSize(true);
		mDrawerList.setLayoutManager(new LinearLayoutManager(this));
		mDrawerList.setAdapter(new MenuAdapter(menus, this));

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerTogle = new ActionBarDrawerToggle(this, mDrawerLayout,
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

		mDrawerLayout.setDrawerListener(mDrawerTogle);

		mSPreferences = getSharedPreferences(getPackageName() + "_preferences",
				Context.MODE_WORLD_READABLE);
		setDensity();
		if (savedInstanceState == null) {
			// selectItem(4);// XXX default fragment
			selectItem(getString(R.string.menu_about));
		}
	}

	private void setDensity() {
		int d = getApplicationContext().getResources().getConfiguration().densityDpi;
		mSPreferences.edit().putInt(PREF_KEY_DENSITY, d).commit();
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

	private void selectItem(CharSequence menu) {
		Object fragment = null;
		if (menu.equals(getString(R.string.menu_style))) {// position 0
			fragment = new StyleFragment(this, mSPreferences);
		} else if (menu.equals(getString(R.string.menu_setting))) {// position 1
			fragment = new SettingFragment(this, mSPreferences);
		} else if (menu.equals(getString(R.string.menu_theme))) {// position 2
			// TODO
		} else if (menu.equals(getString(R.string.menu_backup))) {// position 3
			// TODO
		} else if (menu.equals(getString(R.string.menu_about))) {// position 4
			fragment = new AboutFragment(this);
		}

		if (fragment != null) {
			getFragmentManager().beginTransaction()
					.replace(R.id.content_frame, (Fragment) fragment).commit();
		}
		setTitle(menu);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	private void selectItem(int position) {
		selectItem(menus[position]);
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
}
