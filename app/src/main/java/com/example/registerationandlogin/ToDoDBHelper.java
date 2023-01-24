package com.example.registerationandlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ToDoDBHelper extends SQLiteOpenHelper {
    public ToDoDBHelper(Context context) {
        super(context, "ToDoItems", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ToDoItems(id integer primary key autoincrement, username text, description text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ToDoItems");
    }

    public boolean insertData(String username, String description){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("description", description);
        long result = sqLiteDatabase.insert("ToDoItems", null, cv);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

//    public boolean deleteData(){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long result = sqLiteDatabase.delete("ToDoItems", ,);
//        if (result == -1){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }
//
//    public Cursor getData(){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ToDoItems WHERE ")
//    }

}
