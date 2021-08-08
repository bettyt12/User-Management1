package com.example.ums;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "UserManagement", null, 1);
        db = this.getWritableDatabase();
        //   insertBefore();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (full_name VARCHAR(30),user_name VARCHAR(30),email VARCHAR(30)" +
                ",password VARCHAR(30),mobile VARCHAR(20),gender VARCHAR(10))");
        Log.d("Message", "Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean insertUser(String fullName, String userName, String email, String password, String mobile, String gender) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("full_name", fullName);
        values.put("user_name", userName);
        values.put("email", email);
        values.put("password", password);
        values.put("mobile", mobile);
        values.put("gender", gender);
        long result = db.insert("user", null, values);
        if (result == -1) {
            return false;
        } else
            return true;
    }


    public Cursor getAll() {
        db = this.getReadableDatabase();
        return db.rawQuery("SELECT *FROM user", null);
    }

    public boolean is_vallide(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        boolean vallide = false;
        while (cursor.moveToNext()) {
            String n = cursor.getString(1);
            String p = cursor.getString(3);

            if (name.equals(n) && password.equals(p)) {
                vallide = true;
                break;
            }

        }
        return vallide;
    }

    public Cursor getLogedUser(String user) {
        SQLiteDatabase database=this.getReadableDatabase();
        String[] columns=new String[]{"full_name","user_name","email","password","mobile","gender"};
       String where="user_name= ?";
       String []whereArgs=new String[]{user};
      return database.query("user", columns, where, whereArgs, null, null, null);
        //return database.rawQuery("SELECT * FROM user where ? ",new String[]{user});
    }

    public int delete(int p){
       Cursor cursor=getAll();
       cursor.moveToPosition(p);
      // cursor.getString(1);
       return db.delete("user","user_name=?",new String[]{cursor.getString(1)});
    }


}