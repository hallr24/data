
package com.example.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context) {
        super(context, "PlayerDB", null,1);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "create table PlayerTable(";
        sql += "id integer primary key autoincrement, ";
        sql += "player text, team text, rate text)";
        db.execSQL(sql);
    }

    public void insert(String playerName, String teamName, String rateName) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into PlayerTable values (";
        sql += "null, '"+playerName+"','"+teamName+"','"+rateName+"')";
        db.execSQL(sql);
        db.close();
    }

    public void delete(String playerTitle){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from PlayerTable where player = '"+playerTitle+"'";
        db.execSQL(sql);
        db.close();
    }

    public void updateByPlayer (String player, String team) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update PlayerTable set team = '"+team+"'";
        sql += "where player = '"+player+"'";
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<String> getPlayers() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from PlayerTable";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()) {
            String player = cursor.getString(1);
            list.add(player);
        }
        return list;
    }

    public String[] get(String playerTitle) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from PlayerTable where player = '"+playerTitle+"'";
        Cursor cursor = db.rawQuery(sql, null);
        String[] entry = new String[3];
        if (cursor.moveToFirst()) {
            String player = cursor.getString(1);
            String team = cursor.getString(2);
            String rate = cursor.getString(3);
            entry[0] = player;
            entry[1] = team;
            entry[2] = rate;
        }
        db.close();
        return entry;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
