package com.example.administrator.androidbase.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.androidbase.R;

/**
 * Created by Administrator on 2016/3/7.
 */
public class WindowManagerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window_manager);
		findViewById(R.id.btn_start_window).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		findViewById(R.id.btn_stop_window).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
}
