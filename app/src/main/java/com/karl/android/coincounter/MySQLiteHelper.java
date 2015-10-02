package com.karl.android.coincounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "TotalDB.db";
    private static final String TABLE_NAME = "totals";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_DATE = "date";
    private static final String KEY_COMMENT = "comment";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_DATE + " TEXT, "
                + KEY_TITLE + " TEXT, "
                + KEY_AMOUNT + " TEXT, "
                + KEY_COMMENT + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert data into the database
    public boolean insertData(String title, String amount, String date, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, date);
        values.put(KEY_TITLE, title);
        values.put(KEY_AMOUNT, amount);
        values.put(KEY_COMMENT, comment);

        long result = db.insert(TABLE_NAME, null, values);

        return result != -1;
    }

    // Returns all the data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    // Delete a single entry from the database
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer delete = db.delete(TABLE_NAME, "id = ?", new String[]{id});
        return delete;
    }

    // Clear all entries from the database
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public Cursor searchData(String search){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + "WHERE date = ?";
        Cursor res = db.rawQuery(query, new String[] { search });
        return res;
    }
}
