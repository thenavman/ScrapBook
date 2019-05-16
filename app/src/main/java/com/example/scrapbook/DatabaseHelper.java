package com.example.scrapbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG="DatabaseHelper";
    private static final String TABLE_NAME ="SlamBook";

    private static final String COL1 ="myname";
    private static final String COL2="Nickname";
    private static final String COL3="D.O.B.";
    private static final String COL4 ="Address";
    private static final String COL5 ="MobileNo";
    private static final String COL6 ="instagram_id";
    private static final String COL7 ="Zodiac_Sign";
    private static final String COL8 ="Favourite_Quote";
    private static final String COL9 ="Gender";




    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String createTable="CREATE TABLE "+TABLE_NAME+"("+COL1+"TEXT"+COL2+"TEXT"+COL3+"TEXT"+COL4+"TEXT"+COL5+"TEXT"+COL6+"TEXT"+COL7+"TEXT"+COL8+"TEXT"+COL9+"TEXT)";
     db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP If TABLE EXISTS "+TABLE_NAME);
        onCreate(db);
    }
public boolean addData(String item){
        SQLiteDatabase db=this.getWritableDatabase();
    ContentValues contentValues=new ContentValues();
    contentValues.put(COL1,item);
    contentValues.put(COL2,item);
    contentValues.put(COL3,item);
    contentValues.put(COL4,item);
    contentValues.put(COL5,item);
    contentValues.put(COL6,item);
    contentValues.put(COL7,item);
    contentValues.put(COL8,item);
    contentValues.put(COL9,item);
    Log.d(TAG,"addData Addig"+item+"to"+TABLE_NAME);
    long result=db.insert(TABLE_NAME,null,contentValues);
    //if data is inserted incorrectly it will return -1
    if (result==-1){
        return false;
    }
    else{
        return true;
    }
}
}
