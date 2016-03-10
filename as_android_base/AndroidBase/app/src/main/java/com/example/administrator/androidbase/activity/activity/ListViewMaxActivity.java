package com.example.administrator.androidbase.activity.activity;

import android.app.Activity;
import android.gesture.Gesture;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.androidbase.R;

/**
 * Created by Administrator on 2016/2/26.
 */
public class ListViewMaxActivity extends Activity {

	ListView mListView;
	long mCurrentTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listviewmax);
		mListView = (ListView) findViewById(R.id.lv);
		LinearLayout ll_content = (LinearLayout) findViewById(R.id.ll_content);
		final LinearLayout ll_buttons = (LinearLayout) findViewById(R.id.ll_buttons);

		ll_content.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (System.currentTimeMillis() - mCurrentTime < 500) {
					//双击
					if (ll_buttons.getVisibility() == View.VISIBLE){
						ll_buttons.setVisibility(View.GONE);
					}else {
						ll_buttons.setVisibility(View.VISIBLE);
					}
				} else {
					//单击
				}
				mCurrentTime = System.currentTimeMillis();
			}
		});

	}
}
