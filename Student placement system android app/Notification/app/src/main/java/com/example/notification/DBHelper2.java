package com.example.notification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper2 extends SQLiteOpenHelper {
   public static final String DATABASE_NAME="Student3.db";
    public static final String TABLE_NAME="Student3_table";
    public static final String COL1="COMPANYNAME";
    public static final String COL2="CGPA";
    public static final String COL3="BRANCH";
    public static final String COL4="DATE";
    public static final String COL5="JOB";


    public DBHelper2(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (COMPANYNAME TEXT,CGPA TEXT,BRANCH TEXT,DATE TEXT,JOB TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertdata(String companyname,String cgpa,String branch,String date,String job)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,companyname);
        contentValues.put(COL2,cgpa);
        contentValues.put(COL3,branch);
        contentValues.put(COL4,date);
        contentValues.put(COL5,job);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return  res;
    }

}
