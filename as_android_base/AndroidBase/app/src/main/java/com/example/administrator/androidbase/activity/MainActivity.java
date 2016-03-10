package com.example.administrator.androidbase.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.androidbase.R;
import com.example.administrator.androidbase.activity.activity.DownLoadActivity;
import com.example.administrator.androidbase.activity.activity.ListViewBottomActivity;
import com.example.administrator.androidbase.activity.activity.ListViewSeparatorActivity;
import com.example.administrator.androidbase.activity.activity.TabHostActivity;
import com.example.administrator.androidbase.activity.activity.ViewsActivity;
import com.example.administrator.androidbase.activity.activity.WindowManagerActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.test_down_load).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, DownLoadActivity.class));
			}
		});


		findViewById(R.id.test_tabhost).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, TabHostActivity.class));
			}
		});


		findViewById(R.id.btn_testWidget).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, ViewsActivity.class));
			}
		});

		findViewById(R.id.btn_testListViewBottom).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, ListViewBottomActivity.class));
			}
		});

		findViewById(R.id.btn_testListSeparator).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, ListViewSeparatorActivity.class));
			}
		});

		findViewById(R.id.btn_test_windownManager).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, WindowManagerActivity.class));
			}
		});
	}
}

