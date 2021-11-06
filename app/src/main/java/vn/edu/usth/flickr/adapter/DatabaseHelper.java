package vn.edu.usth.flickr.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import vn.edu.usth.flickr.repository.Data;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists User (id integer primary key autoincrement, email text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User");
        onCreate(db);
    }

    public boolean registerUser(Data data) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        boolean success = false;

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", data.getEmail());
        contentValues.put("password", data.getPassword());
        long user = sqLiteDatabase.insert("User", null, contentValues);
        if (user != -1) {
            Log.e(TAG, "registerUser: User Register successful");
            success = true;

        } else {
            Log.e(TAG, "registerUser: error in registering user");
            success = false;

        }
        return success;

    }

    public boolean loginUser(Data data) {
        boolean success = false;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User", null, null);
        while (cursor.moveToNext()) {
            String email = cursor.getString(1);
            String password = cursor.getString(2);
            if (email.equals(data.getEmail()) && password.equals(data.getPassword())) {
                Log.e(TAG, "Login successful");
                success = true;
            } else {
                Log.e(TAG, "Login Fail");
                success = false;
            }
        }
        return success;
    }
}
