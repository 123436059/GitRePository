package com.example.administrator.androidbase.activity.download;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Administrator on 2016/2/22.
 */
public class DownLoadEngine {

	public static final String ID = "_id";
	public static final String TABLE_NAME = "download";
	public static final String URL = "_url";
	public static final String THREADID = "_threadID";
	public static final String PROGRESS = "_progress";
	public static final String MAXLENGTH = "_maxlength";
	private Uri uri = Uri.parse("content://www.xxx.com/" + TABLE_NAME);

	/**
	 * Download
	 * insert,update,delete,query
	 * 1.插入操作；url,threadId,progress,maxLength
	 * 插入操作前要先判断是否下载过：boolean isExist(String url)
	 * 2.update;
	 * 3.delete;
	 * 4.query:
	 * 退出以后再进来要显示之前的下载进度 getAllProgressByUrl(String url);
	 * 查询某个url对应的某个线程下载的进度：getProgressByThreadId(String url,int threadId);
	 */


	private final Context context;
	private ContentResolver cr;

	public DownLoadEngine(Context context) {
		this.context = context;
		cr = context.getContentResolver();

	}

	public void saveProgress(DownLoadEntity download) {
		ContentValues values = getValues(download);
		Uri insert = cr.insert(uri, values);
		Log.d("taxi", "插入的uri = " + insert);
	}

	public void updateProgress(DownLoadEntity download) {
		ContentValues values = new ContentValues();
		values.put(PROGRESS, download.getProgress());
		cr.update(uri, values, URL + " = ? and " + THREADID + " = ?", new String[]{download.getUrl(),
				String.valueOf(download.getThreadId())});
	}

	public void deleteProgress(String url) {
		cr.delete(uri, URL + " = ?", new String[]{url});
	}

	public int getAllProgressByUrl(String url) {
		int allProgress = 0;
		Cursor cursor = cr.query(uri, new String[]{PROGRESS}, URL + " = ?", new String[]{url}, null);
		if (cursor == null) {
			return 0;
		}

		while (cursor.moveToNext()) {
			int progress = cursor.getInt(cursor.getColumnIndex(PROGRESS));
			allProgress += progress;
		}
		return allProgress;
	}

	public int getProgressByThreadId(DownLoadEntity download) {
		Cursor cursor = cr.query(uri, new String[]{PROGRESS}, URL + " = ? and " + THREADID + " = ?",
				new String[]{download.getUrl(), String.valueOf(download.getThreadId())}, null);
		if (cursor == null)
			return 0;
		if (cursor.moveToNext()) {
			int progress = cursor.getInt(cursor.getColumnIndex(PROGRESS));
			return progress;
		}
		return 0;
	}

	public boolean isExit(String url) {
		Cursor c = cr.query(uri, new String[]{PROGRESS}, URL + "= ?", new String[]{url}, null);
		if (c == null) {
			return false;
		}
		if (c.moveToNext()) {
			return true;
		}
		return false;
	}

	public int getMaxLength(String url) {
		Cursor cursor = cr.query(uri, null, URL + " = ?", new String[]{url}, null);
		if (cursor == null) {
			return 0;
		}

		if (cursor.moveToNext()) {
			int maxLength = cursor.getInt(cursor.getColumnIndex(MAXLENGTH));
			return maxLength;
		}
		return 0;
	}

	private ContentValues getValues(DownLoadEntity download) {
		ContentValues values = new ContentValues();
		values.put(URL, download.getUrl());
		values.put(THREADID, download.getThreadId());
		values.put(PROGRESS, download.getProgress());
		values.put(MAXLENGTH, download.getMaxLength());
		return values;
	}
}
