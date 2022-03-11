package com.example.game;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class user_Data extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "dataq";

    // below int is our database version
    private static final int DB_VERSION = 1;



    // below variable is for our table name.
    private static final String TABLE_NAME = "user_data";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String username_COL = "username";

    // below variable id for our course duration column.
    private static final String password_COL = "password";

    private static final String record_COL = "record";


    public static String getIdCol() {
        return ID_COL;
    }

    public static String getusername_COLL() {
        return username_COL;
    }

    public static String getpassword_COL() {
        return password_COL;
    }

    public static String getrecord_COLL() {
        return record_COL;
    }



    // creating a constructor for our database handler.
    public user_Data(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + username_COL + " TEXT,"
                + password_COL + " TEXT,"


                + record_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public boolean add_user(String username,String password) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(username_COL, username);

        values.put(password_COL, password);

        values.put(record_COL, "");



        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
        return  true;
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int checkUserExist(String username, String password){
        String[] columns = {"username"};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "username=? and password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();


        cursor.close();
        close();

        if(count > 0){


            return count;
        } else {
            return 0;
        }
    }
    public String getid(String user,String pass){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery( "select * from "+TABLE_NAME+" WHERE username='"+user+"'"+"AND password='"+pass+"'", null );
        res.moveToFirst();
        int n=res.getColumnIndex(ID_COL);
        String s=(res.getString(n));

        return s;
    }
    public boolean update(String id,String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COL,id);

        values.put(record_COL, score);
        db.update(TABLE_NAME,values,"Id = ?",new String[] { id });
        return true;
    }


    @SuppressLint("Range")
    public String[] getdata(String CName) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        Cursor res = db.rawQuery( "select * from "+TABLE_NAME+" EXCEPT select * from "+TABLE_NAME+ " WHERE username='admin'"+"AND password='admin'"+" ORDER BY record DESC", null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(CName)));

            res.moveToNext();
        }
        return array_list.toArray(new String[0]);
    }

}
