package com.sqllite.example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    Context context;

    static String DB_NAME = "todo.db";
    static int DB_VERSION = 1;
    static String TABLE_NAME = "todos";
    static String COLUMN_ID = "id";
    static String COLUMN_TASK = "task";
    static String COLUMN_DESCRIPTION = "description";


    DbHelper(Context context) {
        //DB CREATING DONE
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create db table here
        // create table table_name
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
    }

    // CREATE/INSERT TODOS
      public long  insertTodo(String task, String description){
          SQLiteDatabase db  = this.getWritableDatabase();
          ContentValues values = new ContentValues();
          values.put(COLUMN_TASK, task);
          values.put(COLUMN_DESCRIPTION, description);
          return  db.insert(TABLE_NAME,null,values);
        }

       Cursor readData(){
           SQLiteDatabase db  = this.getReadableDatabase();
           String readQuery = " SELECT * FROM " + TABLE_NAME;
           Cursor cursor = null;
           if(db!=null){
               cursor = db.rawQuery(readQuery,null);
           }
           return cursor;

        }

}
