package com.example.datvl.appqls;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Database extends SQLiteOpenHelper {

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertsach(byte[] hinh, String ten, String theloai){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO sach VALUES(null, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, hinh);
        statement.bindString(2, ten);
        statement.bindString(3,theloai);

        statement.executeInsert();
    }

    public void inserttk(String tentk, String matkhau){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO sach VALUES(null, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, tentk);
        statement.bindString(2,matkhau);

        statement.executeInsert();
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

