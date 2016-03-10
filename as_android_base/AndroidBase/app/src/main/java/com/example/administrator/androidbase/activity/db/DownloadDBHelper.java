package com.example.administrator.androidbase.activity.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.androidbase.activity.download.DownLoadEngine;

/**
 * Created by Administrator on 2016/2/23.
 */
public class DownloadDBHelper extends SQLiteOpenHelper {

	private static final String db_name = "download_db";
	private static final int db_version = 1;

	public DownloadDBHelper(Context context) {
		super(context, db_name, null, db_version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + DownLoadEngine.TABLE_NAME + "(" +
				DownLoadEngine.ID+" integer primary key, " +
				DownLoadEngine.URL + " text, " +
				DownLoadEngine.THREADID + " integer, " +
				DownLoadEngine.PROGRESS +" integer, " +
				DownLoadEngine.MAXLENGTH + " integer" +
				")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
