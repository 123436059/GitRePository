package com.example.administrator.androidbase.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.androidbase.R;
import com.example.administrator.androidbase.activity.download.DownLoadEngine;
import com.example.administrator.androidbase.activity.download.DownLoadManager;

public class DownLoadActivity extends Activity {
	private EditText et_url;
	private ProgressBar pb;
	private Button btn_download;
	private TextView tv_progress;
	private static final int DOWNLOAD_FINISH = 0;
	private static final int DOWNLOAD_DOING = 1;
	private static final int DOWNLOAD_NO_SDK = 2;
	private static final int DOWNLOAD_ERROR = 3;
	private String url;
	private DownLoadEngine service;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_activitiy);
		initView();
		readLocalProgress();
		setListener();
	}

	private void readLocalProgress() {
		url = et_url.getText().toString();
		service = new DownLoadEngine(this);

		int maxLength = service.getMaxLength(url);
		if (maxLength > 0) {
			btn_download.setText("继续");


			Message message = uiHandler.obtainMessage();
			message.what = DOWNLOAD_FINISH;
			message.arg1 = maxLength;
			uiHandler.sendMessage(message);

			int progress = service.getAllProgressByUrl(url);
			message = uiHandler.obtainMessage();
			message.what = DOWNLOAD_DOING;
			message.arg1 = progress;
			uiHandler.sendMessage(message);
		} else {
			btn_download.setText("开始");
		}
	}

	private void setListener() {
		btn_download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (DownLoadManager.isPause) {
					downLoad();
				} else {
					pauseDownload();
				}
			}
		});
	}

	private void pauseDownload() {
		DownLoadManager.isPause = true;
		btn_download.setText("继续");
	}

	Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case DOWNLOAD_FINISH:
					//初始化
					pb.setMax(msg.arg1);
					pb.setProgress(msg.arg2);
					tv_progress.setText("下载：" + pb.getProgress() * 100 / pb.getMax() + "%");
					break;

				case DOWNLOAD_DOING:
					int progress = pb.getProgress() + msg.arg1;
					Log.d("taxi", "下载中");
					pb.setProgress(progress);
					tv_progress.setText("下载：" + pb.getProgress() * 100 / pb.getMax() + "%");
					if (progress == pb.getMax()) {
						//下载完成
						service.deleteProgress(url);
						Toast.makeText(DownLoadActivity.this, "下载完成", Toast.LENGTH_LONG).show();
					}
					break;

				case DOWNLOAD_NO_SDK:
					Toast.makeText(DownLoadActivity.this, "SDK不存在", Toast.LENGTH_LONG).show();
					break;

				case DOWNLOAD_ERROR:
					Toast.makeText(DownLoadActivity.this, "下载出错", Toast.LENGTH_LONG).show();
					break;
			}
		}
	};

	String savePath;

	private void downLoad() {
		final String download_url = et_url.getText().toString();
		//判断sd卡是否存在
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getFileName(download_url);
		} else {
			uiHandler.sendEmptyMessage(DOWNLOAD_NO_SDK);
		}

		DownLoadManager.isPause = false;
		btn_download.setText("暂停");

		final DownLoadManager manager = new DownLoadManager(DownLoadActivity.this);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					manager.downLoad(download_url, savePath, new DownLoadManager.DownLoadProgressListener() {
						@Override
						public void setProgress(int progress) {
							Message msg = uiHandler.obtainMessage();
							msg.what = DOWNLOAD_DOING;
							msg.arg1 = progress;
							uiHandler.sendMessage(msg);
						}

						@Override
						public void setMax(int max, int curProgress) {
							Message msg = uiHandler.obtainMessage();
							msg.what = DOWNLOAD_FINISH;
							msg.arg1 = max;
							msg.arg2 = curProgress;
							uiHandler.sendMessage(msg);

						}
					});
				} catch (Exception e) {
					e.printStackTrace();
					//下载出错
					uiHandler.sendEmptyMessage(DOWNLOAD_ERROR);
				}
			}
		}).start();

	}

	private String getFileName(String download_url) {
		return download_url.substring(download_url.lastIndexOf("/") + 1);
	}


	private void initView() {
		et_url = (EditText) findViewById(R.id.et_url);
		pb = (ProgressBar) findViewById(R.id.pb);
		btn_download = (Button) findViewById(R.id.btn_download);
		tv_progress = (TextView) findViewById(R.id.tv_progress);
	}

}
