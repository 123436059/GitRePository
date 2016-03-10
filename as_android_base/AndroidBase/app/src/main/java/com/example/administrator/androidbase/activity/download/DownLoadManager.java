package com.example.administrator.androidbase.activity.download;

import android.content.Context;
import android.util.Log;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/2/22.
 */
public class DownLoadManager {

	private static final int THREADSIZE = 3;
	private final Context context;
	DownLoadEngine service;
	public static boolean isPause = true;

	public DownLoadManager(Context context) {
		this.context = context;
		service = new DownLoadEngine(context);
	}

	public void downLoad(String url, String savePath, DownLoadProgressListener listener) throws Exception {
		URL u = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) u.openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		int responseCode = connection.getResponseCode();
		if (200 == responseCode) {
			int maxLength = connection.getContentLength();

			if (!service.isExit(url)) {
				for (int i = 0; i < THREADSIZE; i++) {
					DownLoadEntity downLoadEntity = new DownLoadEntity();
					downLoadEntity.setUrl(url);
					downLoadEntity.setThreadId(i);
					downLoadEntity.setProgress(0);
					downLoadEntity.setMaxLength(maxLength);
					service.saveProgress(downLoadEntity);
				}
			}
			int allProgress = service.getAllProgressByUrl(url);
			listener.setMax(maxLength, allProgress);
			RandomAccessFile raf = new RandomAccessFile(savePath, "rwd");
			raf.setLength(maxLength);
			raf.close();
			int block = maxLength % THREADSIZE == 0 ? maxLength / THREADSIZE : maxLength / THREADSIZE + 1;
			for (int i = 0; i < THREADSIZE; i++) {
				new DownLoadThread(context, url, savePath, block, i, listener).start();
			}
		}
	}


	public interface DownLoadProgressListener {
		public void setProgress(int progress);

		public void setMax(int max, int curProgress);
	}
}
