package com.example.administrator.androidbase.activity.download;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.administrator.androidbase.activity.db.DownloadDBHelper;

/**
 * Created by Administrator on 2016/2/23.
 */
public class DownloadProvider extends ContentProvider {

	SQLiteDatabase db;

	private static final String authority = "www.xxx.com";
	private static final int DOWNLOADCODE = 0;

	static {
		UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(authority, DownLoadEngine.TABLE_NAME, DOWNLOADCODE);
	}

	@Override
	public boolean onCreate() {
		DownloadDBHelper dbHelper = new DownloadDBHelper(getContext());
		db = dbHelper.getWritableDatabase();
		return true;
	}

	@Nullable
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor cursor = db.query(DownLoadEngine.TABLE_NAME, null, selection, selectionArgs, null, null, sortOrder);
		return cursor;
	}

	@Nullable
	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Nullable
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long id = db.insert(DownLoadEngine.TABLE_NAME, DownLoadEngine.ID, values);
		//content://www.xxx.com/download/id
		Uri uriId = ContentUris.withAppendedId(uri, id);
		return uriId;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int delete = db.delete(DownLoadEngine.TABLE_NAME, selection, selectionArgs);
		return delete;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return db.update(DownLoadEngine.TABLE_NAME, values, selection, selectionArgs);
	}
}
