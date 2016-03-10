package com.example.administrator.androidbase.activity.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.administrator.androidbase.R;
import com.example.administrator.androidbase.activity.activity.tabhostactivity.AAcvtivity;
import com.example.administrator.androidbase.activity.activity.tabhostactivity.BActivity;
import com.example.administrator.androidbase.activity.activity.tabhostactivity.CActivity;

/**
 * Created by Administrator on 2016/2/23.
 */
public class TabHostActivity extends TabActivity {

	private static final String MOD1 = "mod1";
	private static final String MOD2 = "mod2";
	private static final String MOD3 = "mod3";
	private static final String MOD4 = "mod4";

	TabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabhost);
		mTabHost = getTabHost();
		mTabHost.setup();
		TabHost.TabSpec tabSpec;
		tabSpec = mTabHost.newTabSpec(MOD1).setIndicator(MOD1).setContent(new Intent(this, AAcvtivity.class));
		mTabHost.addTab(tabSpec);

		tabSpec = mTabHost.newTabSpec(MOD2).setIndicator(MOD2).setContent(new Intent(this, BActivity.class));
		mTabHost.addTab(tabSpec);

		tabSpec = mTabHost.newTabSpec(MOD3).setIndicator(MOD3).setContent(new Intent(this, CActivity.class));
		mTabHost.addTab(tabSpec);


	}
}
