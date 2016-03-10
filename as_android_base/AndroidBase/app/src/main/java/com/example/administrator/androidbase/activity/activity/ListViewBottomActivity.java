package com.example.administrator.androidbase.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.androidbase.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class ListViewBottomActivity extends Activity {

	ListView listView;
	List<String> list;
	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listviewbottom);
		list = new ArrayList<>();
		listView = (ListView) findViewById(R.id.lv_listbottom);

		 adapter = new MyAdapter();
		listView.setAdapter(adapter);
		findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addData();
			}
		});

	}

	private void addData() {
		for (int i = 0; i < 6; i++) {
			list.add("测试");
		}

		adapter.notifyDataSetChanged();
//		listView.setSelection(listView.getBottom());
		listView.setSelection(adapter.getCount()-1);
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = getLayoutInflater().inflate(R.layout.item_listbottom, null);
			TextView tv = (TextView) convertView.findViewById(R.id.tv_content);
			tv.setText(list.get(position));
			return convertView;
		}
	}


}
