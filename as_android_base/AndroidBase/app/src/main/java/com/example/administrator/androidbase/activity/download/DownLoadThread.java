package com.example.administrator.androidbase.activity.download;

import android.content.Context;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadThread extends Thread {

	private final String url;
	private final String savePath;
	private final int index;
	DownLoadEngine service;

	private int startPoint, endPoint;
	private DownLoadManager.DownLoadProgressListener listener;

	public DownLoadThread(Context context, String url, String savePath, int block, int index,
	                      DownLoadManager.DownLoadProgressListener listener) {
		this.url = url;
		this.savePath = savePath;
		this.listener = listener;
		this.index = index;
		startPoint = block * index;
		endPoint = (index + 1) * block - 1;
		service = new DownLoadEngine(context);

	}

	@Override
	public void run() {
		super.run();

		try {

			DownLoadEntity downLoadEntity = new DownLoadEntity();
			downLoadEntity.setThreadId(index);
			downLoadEntity.setUrl(url);
			int progress = service.getProgressByThreadId(downLoadEntity);
			startPoint += progress;


			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			//设置请求文件的字节范围
			conn.setRequestProperty("Range", "bytes=" + startPoint + "-" + endPoint);
			InputStream inputStream = conn.getInputStream();
			RandomAccessFile raf = new RandomAccessFile(savePath, "rwd");
			//从哪里开始写
			raf.seek(startPoint);
			int len = 0;
			int summery = progress;
			byte[] buff = new byte[1024];
			while ((len = inputStream.read(buff)) != -1) {
				Thread.sleep(500);
				raf.write(buff, 0, len);
				listener.setProgress(len);
				summery += len;
				//暂停线程
				if (DownLoadManager.isPause) {
					DownLoadEntity download = new DownLoadEntity();
					download.setUrl(url);
					download.setThreadId(index);
					download.setProgress(summery);
					service.updateProgress(download);
					break;
				}
			}
			raf.close();
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
