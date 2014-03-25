package com.yuli.mhealth.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseAdapter {
	//����log
	private static final String TAG = "MyDataBaseAdapter";
	//һ����¼������
	//���
	private static final String KEY_ID = "_id";
	//����������
	public static final String KEY_NAME = "Dealer_name";
	//�ݷ�����
	public static final String KEY_DATA = "data";
	//�Ƿ�ݷ�
	private static final String KEY_VISITED = "visited";
	//�Ƿ�Ԥ��
	private static final String KEY_YUGOU = "yugou";
	//Ԥ��ҩ��
	private static final String KEY_YAOMING = "yaoming";
	//ҩƷ����
	private static final String KEY_YAOPINNUM = "yaonum";
	
	//���ݿ�����
	private static final String DB_NAME = "huihao.db";
	//���ݿ��
	private static final String DB_TABLE = "worktable";
	//���ݿ�汾
	private static final int DB_VERSON = 1;
	//����context����
	private Context mContext = null;
	
	//����
	private static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY,"
			+ KEY_NAME
			+ " TEXT,"
			+ KEY_DATA
			+ " TEXT,"
			+ KEY_VISITED
			+ " INTEGER,"
			+ KEY_YUGOU
			+ " INTEGER,"
			+ KEY_YAOMING
			+ " TEXT,"
			+ KEY_YAOPINNUM
			+ " INTEGER)";
	//ִ��open()�����ݿ�ʱ�����淵�ص����ݶ���
	private SQLiteDatabase mSQLiteDatabase = null;
	//��SQLiteopenHelper�̳й���
	private DatabaseHelper mDatabaseHelper = null;
	private static class DatabaseHelper extends SQLiteOpenHelper {

		//���캯�����������ݿ�
		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSON);
		}
		//����һ����
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			//���ݿ�û�б�ʱ
			db.execSQL(DB_CREATE);
		}
		//�������ݿ�
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}
	//���캯����ȡ��Context
	public MyDataBaseAdapter(Context context) {
		mContext = context;
	}
	//�����ݿ⣬�������ݿ����
	public void open() throws SQLException {
		mDatabaseHelper = new DatabaseHelper(mContext);
		mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
	}
	//�ر����ݿ�
	public void close()
	{
		mDatabaseHelper.close();
	}
	
	/* ����һ������ */
	public long insertData(String Dealer_name, String data, int visited, int yugou, String yaoming, int yaonum)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, Dealer_name);
		initialValues.put(KEY_DATA, data);
		initialValues.put(KEY_VISITED, visited);
		initialValues.put(KEY_YUGOU, yugou);
		initialValues.put(KEY_YAOMING, yaoming);
		initialValues.put(KEY_YAOPINNUM, yaonum);
		return mSQLiteDatabase.insert(DB_TABLE, KEY_ID, initialValues);
	}
	/* ɾ��һ������ */
	public boolean deleteData(long rowId)
	{
		return mSQLiteDatabase.delete(DB_TABLE, KEY_ID + "=" + rowId, null) > 0;
	}
	//ͨ��Cursor��ѯ��������
	public Cursor fetchAllData() {
		return mSQLiteDatabase.query(DB_TABLE, new String[] {KEY_ID, KEY_NAME, KEY_DATA, KEY_VISITED, 
				KEY_YUGOU, KEY_YAOMING, KEY_YAOPINNUM}, null, null, null, null, null);
	}
	//��ѯָ������
	public Cursor fetchData(long rowId) throws SQLException {
		Cursor mCursor = mSQLiteDatabase.query(true, DB_TABLE, new String[] {KEY_ID, KEY_NAME, KEY_DATA, KEY_VISITED, KEY_YUGOU, KEY_YAOMING, KEY_YAOPINNUM}, KEY_ID + "=" +rowId, 
						null, null, null, null, null);
		if(mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	//����һ������
	public boolean updateData(long rowId, String Dealer_name, String data, int visited, int yugou, String yaoming, int yaonum)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, Dealer_name);
		args.put(KEY_DATA, data);
		args.put(KEY_VISITED, visited);
		args.put(KEY_YUGOU, yugou);
		args.put(KEY_YAOMING, yaoming);
		args.put(KEY_YAOPINNUM, yaonum);
		return mSQLiteDatabase.update(DB_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
	}
}
