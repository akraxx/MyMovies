package fr.flst.mmargr.mymovies.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DictionaryOpenHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 2;
	public static final String DATABASE_NAME = "mymovies";
    public static final String TABLE_NAME = "movies";
    public static final String COLUMN_NAME_TYPE = "type";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_YEAR = "year";
    public static final String COLUMN_NAME_NOTE = "note";
    public static final String COLUMN_NAME_RATE = "rate";
    public static final String COLUMN_ID = "_id";
    
    private static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_TYPE + " TEXT, " +
                COLUMN_NAME_TITLE + " TEXT, " +
                COLUMN_NAME_YEAR + " NUMBER, " +
                COLUMN_NAME_NOTE + " TEXT, " +		
                COLUMN_NAME_RATE + " NUMBER);";

    public DictionaryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
