package com.example.administrator.androidbase.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.androidbase.R;
import com.example.administrator.androidbase.activity.views.PullSeparateListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class ListViewSeparatorActivity extends Activity {

	private PullSeparateListView listView;
	private List<String> list;
	private MyAdapter myAdapter;
	CheckBox cb_all;
	CheckBox cb_one;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_separator);
		list = new ArrayList<>();
		listView = (PullSeparateListView) findViewById(R.id.pullSeparator);


		cb_all = (CheckBox) findViewById(R.id.check_separatorall);
		cb_one = (CheckBox) findViewById(R.id.check_separatorone);


		cb_all.setChecked(listView.isSeparateAll());
		cb_one.setChecked(listView.isShowDownAnim());

		cb_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				listView.setSeparateAll(true);
			}
		});

		cb_one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				listView.setShowDownAnim(true);
			}
		});

		myAdapter = new MyAdapter();
		listView.setAdapter(myAdapter);
		initData();


	}

	private void initData() {
		for (int i = 0; i < 10; i++) {
			list.add("顶顶顶" + i);
		}
		myAdapter.notifyDataSetChanged();
	}


	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public String getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = getLayoutInflater().inflate(R.layout.item_listview_separator, null);

			TextView title = (TextView) convertView.findViewById(R.id.tv_positon);
			TextView content = (TextView) convertView.findViewById(R.id.tv_content);

			title.setText(position + 1 + "");
			content.setText(getItem(position));

			return convertView;
		}
	}
}
