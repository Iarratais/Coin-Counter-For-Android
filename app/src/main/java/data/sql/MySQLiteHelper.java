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
    private static final String KEY_CURRENCY = "currency";
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
                + KEY_GROUPING + " TEXT, "
                + KEY_CURRENCY + " TEXT)");

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

    /**
     * Insert data into the two database tables.
     *
     * @param save object with all the information contained within.
     *
     * @return true if the information has been saved, false if not.
     */
    public boolean insertData(Save save) {
        SQLiteDatabase db = this.getWritableDatabase();

        // insert information into totals table.
        ContentValues totalsInformation = new ContentValues();
        totalsInformation.put(KEY_DATE, save.getDate());
        totalsInformation.put(KEY_TITLE, save.getTitle());
        totalsInformation.put(KEY_AMOUNT, save.getAmount());
        totalsInformation.put(KEY_COMMENT, save.getComment());
        totalsInformation.put(KEY_GROUPING, save.getGrouping());
        totalsInformation.put(KEY_CURRENCY, save.getCurrency());

        long totalsResult = db.insert(TABLE_NAME, null, totalsInformation);

        // only insert this if the totals information has been saved correctly, so this does not
        // knock the two tables out of line.
        if(totalsResult != -1) {

            // insert information into denominations
            ContentValues denominations = new ContentValues();
            denominations.put(Save.DatabaseExtras.KEY_NOTE_200000, save.getNote200000());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_100000, save.getNote100000());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_50000, save.getNote50000());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_20000, save.getNote20000());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_10000, save.getNote10000());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_5000, save.getNote5000());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_2000, save.getNote2000());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_1000, save.getNote1000());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_500, save.getNote500());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_100, save.getNote100());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_50, save.getNote50());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_20, save.getNote20());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_10, save.getNote10());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_5, save.getNote5());
            denominations.put(Save.DatabaseExtras.KEY_NOTE_1, save.getNote1());
            denominations.put(Save.DatabaseExtras.KEY_COIN_2, save.getCoin2());
            denominations.put(Save.DatabaseExtras.KEY_COIN_1, save.getCoin1());
            denominations.put(Save.DatabaseExtras.KEY_CENT_50, save.getCent50());
            denominations.put(Save.DatabaseExtras.KEY_CENT_25, save.getCent25());
            denominations.put(Save.DatabaseExtras.KEY_CENT_20, save.getCent20());
            denominations.put(Save.DatabaseExtras.KEY_CENT_10, save.getCent10());
            denominations.put(Save.DatabaseExtras.KEY_CENT_5, save.getCent5());
            denominations.put(Save.DatabaseExtras.KEY_CENT_2, save.getCent2());
            denominations.put(Save.DatabaseExtras.KEY_CENT_1, save.getCent1());

            totalsResult = db.insert(TABLE_DENOMINATIONS_NAME, null, denominations);
        }

        return totalsResult != -1;
    }

    /**
     * Get all of the data from the totals database.
     *
     * @return Cursor with information of total from totals database.
     */
    public Cursor getDataFromTotals() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT " + KEY_ID + ", " + KEY_TITLE + ", " + KEY_AMOUNT + ", " + KEY_DATE + ", " + KEY_CURRENCY + " FROM " + TABLE_NAME, null);
    }

    /**
     * Delete a single entry from the totals table and from the denominations table.
     *
     * @param id of the entry to delete from the database.
     */
    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{id});
        db.delete(TABLE_DENOMINATIONS_NAME, "id = ?", new String[]{id});
    }

    /**
     * Delete all entries from both of the databases
     */
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.execSQL("DELETE FROM " + TABLE_DENOMINATIONS_NAME);
    }

    public Cursor searchData(String search){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + "WHERE date = ?";
        Cursor res = db.rawQuery(query, new String[] { search });
        return res;
    }
}
