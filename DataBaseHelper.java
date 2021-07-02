package com.example.weguideu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String PWD_TABLE = "PWD_TABLE";
    public static final String COL_PWD_NAME= "PWD_NAME";
    public static final String COL_PWD_AGE = "PWD_AGE";
    public static final String COL_PWD_ADDRESS = "PWD_ADDRESS";
    public static final String COL_PWD_PHONE= "PWD_PHONE";
    public static final String COL_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "pwd.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " +  PWD_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PWD_NAME + " TEXT, " + COL_PWD_AGE + " INT, " + COL_PWD_ADDRESS + " TEXT, " + COL_PWD_PHONE + " INT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(PWDModelClass pwdModelClass){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_PWD_NAME, pwdModelClass.getName());
        cv.put(COL_PWD_AGE, pwdModelClass.getAge());
        cv.put(COL_PWD_ADDRESS, pwdModelClass.getAddress());
        cv.put(COL_PWD_PHONE, pwdModelClass.getPhone());

        long insert = db.insert(PWD_TABLE, null, cv);

        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean deleteOne(PWDModelClass pwdModelClass){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PWD_TABLE + " WHERE " + COL_ID + " = " + pwdModelClass.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }
    
    public List<PWDModelClass> getEveryone(){

        List<PWDModelClass> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + PWD_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int pwdID = cursor.getInt(0);
                String pwdName = cursor.getString(1);
                int pwdAge = cursor.getInt(2);
                String pwdAddress = cursor.getString(3);
                int pwdPhone = cursor.getInt(4);

                PWDModelClass newPWD = new PWDModelClass(pwdID, pwdName, pwdAge, pwdAddress, pwdPhone);
                returnList.add(newPWD);

            }while (cursor.moveToNext());

        }else {
            //error
        }

        cursor.close();
        db.close();
        return returnList;
    }
    
}
