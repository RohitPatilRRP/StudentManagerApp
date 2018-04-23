package com.example.dell.studentmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SqliteHelper extends SQLiteOpenHelper {
    //DATABASE NAME
    private static final String DATABASE_NAME = "StudentManager";

    //DATABASE VERSION
    private static final int DATABASE_VERSION = 1;

    //TABLE NAME
    private static final String TABLE_NAME = "Users";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    private static final String ID = "Id";

    //COLUMN number
    private static final String NUMBER = "Number";

    //COLUMN email
    private static final String EMAIL = "Email";

    //COLUMN password
    private static final String PASSWORD = "Password";

    //SQL for creating users table
    private static final String createQuery = " CREATE TABLE " + TABLE_NAME
            + " ( "
            + ID + " INTEGER PRIMARY KEY, "
            + NUMBER + " TEXT, "
            + EMAIL + " TEXT, "
            + PASSWORD + " TEXT "
            + " ) ";
    private final Context context;


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
    }


    public boolean insertData(String email, String number, String pass) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EMAIL, email);
        cv.put(NUMBER, number);
        cv.put(PASSWORD, pass);

        final long result = db.insert(TABLE_NAME, null , cv);

//        if(result == -1){
//            return false;
//        }else {
//            return true;
//        }

        if (result != -1){
            //Toast.makeText(context, "New row added, row id: " + result, Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            //Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
            return false;

        }
    }

    public Cursor ifEmailExists(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] project = {EMAIL,NUMBER};
        String selection = EMAIL+" LIKE ?";
        String [] selection_args = {email};

        Cursor cursor = db.query(TABLE_NAME,project,selection,selection_args,null,null,null);
        return cursor;
    }

    public Cursor ifNumExists(String number){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] project = {NUMBER};
        String selection = NUMBER  + " LIKE ?";
        String [] selection_args = {number};

        //Toast.makeText(context, "function called!!!", Toast.LENGTH_SHORT).show();
        Cursor cursor = db.query(TABLE_NAME,project,selection,selection_args,null,null,null);

        if (cursor == null){
            Toast.makeText(context, "Its null!!!", Toast.LENGTH_SHORT).show();
        }

        return cursor;
    }


    public Cursor ifPasswordForTheNumberIsCorrect(String num,String pass){

        SQLiteDatabase db = this.getReadableDatabase();

        String[] project = {NUMBER,PASSWORD};
//        String selection =  " SELECT * FROM "+TABLE_NAME+" WHERE "+NUMBER+" =? AND "+PASSWORD+" =?";
        String selection = NUMBER  + " LIKE ?";
        String [] selection_args = {num};
        //Toast.makeText(context, "function called!!!", Toast.LENGTH_SHORT).show();
        Cursor cursor = db.query(TABLE_NAME,project,selection,selection_args,null,null,null);

        return cursor;
    }

}


