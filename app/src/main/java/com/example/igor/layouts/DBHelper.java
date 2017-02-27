package com.example.igor.layouts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by igor on 23/02/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "teste";
    private static String TABLE_ITENS = "CREATE TABLE itens (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_ITENS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
