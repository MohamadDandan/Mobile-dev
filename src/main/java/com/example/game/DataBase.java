package com.example.game;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "data";

    // below int is our database version
    private static final int DB_VERSION = 1;



    // below variable is for our table name.
    private static final String TABLE_NAME = "game_data";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String qusetion_COL = "question";

    // below variable id for our course duration column.
    private static final String answer_a_COL = "answer_a";
    private static final String answer_b_COL = "answer_b";
    private static final String answer_c_COL = "answer_c";
    private static final String answer_d_COL = "answer_d";
    private static final String answer_COL = "answer";


    public static String getQusetion_COL() {
        return qusetion_COL;
    }

    public static String getAnswer_a_COL() {
        return answer_a_COL;
    }

    public static String getAnswer_b_COL() {
        return answer_b_COL;
    }

    public static String getAnswer_c_COL() {
        return answer_c_COL;
    }

    public static String getAnswer_d_COL() {
        return answer_d_COL;
    }

    public static String getAnswer_COL() {
        return answer_COL;
    }

    // creating a constructor for our database handler.
    public DataBase(Context context) {
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
                + qusetion_COL + " TEXT,"
                + answer_a_COL + " TEXT,"
                + answer_b_COL + " TEXT,"
                + answer_c_COL + " TEXT,"
                + answer_d_COL + " TEXT,"

                + answer_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewQusetion(String question,
                             String a,
                             String b,
                             String c,
                             String d,
                             String answer) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(qusetion_COL, question);

        values.put(answer_a_COL, a);
        values.put(answer_b_COL, b);
        values.put(answer_c_COL, c);
        values.put(answer_d_COL, d);

        values.put(answer_COL, answer);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public String getAllCotacts(String Cname, Object i) {
        SQLiteDatabase db = this.getReadableDatabase();
        //ArrayList<String> array_list = new ArrayList<String>();
        //String name;
        Cursor res = db.rawQuery( "select * from "+TABLE_NAME+" WHERE id="+i, null );
        res.moveToFirst();


            int n=res.getColumnIndex(Cname);
            String s=(res.getString(n));

        return s;

    }
    public ArrayList getAllId() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        String name;
        Cursor res = db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {


            array_list.add(res.getString(0));
            res.moveToNext();
        }
        return array_list;

    }
}
