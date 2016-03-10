package com.example.administrator.androidbase.activity.activity.tabhostactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/2/23.
 */
public class BActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(BActivity.this);
		textView.setText("页面2");
		textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		setContentView(textView);
	}
}
