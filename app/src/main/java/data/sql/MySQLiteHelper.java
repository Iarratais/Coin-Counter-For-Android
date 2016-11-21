package data.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.karl.android.templates.Save;


public class MySQLiteHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "TotalDB.db";
    private static final String TABLE_NAME = "totals";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_DATE = "date";
    private static final String KEY_COMMENT = "comment";
    private static final String KEY_GROUPING = "grouping";
    private static final String TABLE_DENOMINATIONS_NAME = "denominations";


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
                + KEY_COMMENT + " TEXT, "
                + KEY_GROUPING + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_DENOMINATIONS_NAME
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Save.DatabaseExtras.KEY_NOTE_200000 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_100000 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_50000 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_20000 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_10000 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_5000 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_2000 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_1000 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_500 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_200 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_100 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_50 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_20 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_10 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_5 + " TEXT, "
                + Save.DatabaseExtras.KEY_NOTE_1 + " TEXT, "
                + Save.DatabaseExtras.KEY_COIN_2 + " TEXT, "
                + Save.DatabaseExtras.KEY_COIN_1 + " TEXT, "
                + Save.DatabaseExtras.KEY_CENT_50 + " TEXT, "
                + Save.DatabaseExtras.KEY_CENT_25 + " TEXT, "
                + Save.DatabaseExtras.KEY_CENT_20 + " TEXT, "
                + Save.DatabaseExtras.KEY_CENT_10 + " TEXT, "
                + Save.DatabaseExtras.KEY_CENT_5 + " TEXT, "
                + Save.DatabaseExtras.KEY_CENT_2 + " TEXT, "
                + Save.DatabaseExtras.KEY_CENT_1 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert data into the database
    public boolean insertData(Save save) {
        SQLiteDatabase db = this.getWritableDatabase();

        // insert information into totals
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, save.getDate());
        values.put(KEY_TITLE, save.getTitle());
        values.put(KEY_AMOUNT, save.getAmount());
        values.put(KEY_COMMENT, save.getComment());
        values.put(KEY_GROUPING, save.getGrouping());

        long result = db.insert(TABLE_NAME, null, values);

        // insert information into

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
