package com.example.igor.layouts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by igor on 23/02/17.
 */

public class DBManager {
    private static DBHelper dbHelper = null;

    public DBManager(Context context){
        if (dbHelper == null){
            dbHelper = new DBHelper(context);
        }
    }

    public void addItem(String nome){
        ContentValues value = new ContentValues();
        value.put("nome", nome);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("itens", null,value);
    }

    public void  deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("itens", null, null);
    }

    public JSONArray getAllItens(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM itens";
        Cursor cursor = db.rawQuery(sql,null);

        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();
            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        rowObject.put(cursor.getColumnName(i),
                                cursor.getString(i));
                    } catch (Exception e) {
                        Log.d(TAG, e.getMessage());
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }

        cursor.close();
        return resultSet;
    }
}
