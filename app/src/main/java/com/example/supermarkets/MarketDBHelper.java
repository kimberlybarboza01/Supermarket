package com.example.supermarkets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class MarketDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mysupermarket.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_CONTACT =
            "create table contact(_id integer primary key autoincrement, "
                + "supermarketname text not null, address text, "
                + "city text, state text, zipcode text,"
                + "liquor text, "
                + "produce text, "
                + "meal text, "
                + "cheese text, "
                + "Easeofcheckout text);";

    public MarketDBHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MarketDBHelper.class.getName(),
                "Upgrading database from version" + oldVersion + " to "
                        + newVersion + ", which will destroy all old data" );
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);

    }
}
