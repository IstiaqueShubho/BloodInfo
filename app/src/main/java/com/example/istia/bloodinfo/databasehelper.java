package com.example.istia.bloodinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

public class databasehelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "Blood_Info.db";
    private static final String TABLE_NAME= "People_Info";
    private static final String ID= "ID";
    private static final String NAME= "Name";
    private static final String EMAIL= "Email";
    private static final String PASSWORD= "Password";
    private static final String MOBILE= "Mobile";
    private static final String B_GROUP= "Blood_Group";
    private static final String DISTRICT= "District";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL,"+PASSWORD+" TEXT NOT NULL,"+DISTRICT+" TEXT NOT NULL,"+MOBILE+" TEXT NOT NULL,"+B_GROUP+" TEXT NOT NULL ); ";
    private Context context;
    public databasehelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try
        {
            Toast.makeText(context, "onCreate is called", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
        catch(Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(sqLiteDatabase);
        }catch(Exception e) {
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_LONG).show();
        }
    }

    public long insertData(String name,String email,String password,String b_group,String district,String mobile) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD,password);
        contentValues.put(B_GROUP, b_group);
        contentValues.put(DISTRICT, district);
        contentValues.put(MOBILE, mobile);
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }
    public Boolean findpassword(String phone,String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Boolean result=false;
        if(cursor.getCount()==0){
            Toast.makeText(context, "No Data is Found!!", Toast.LENGTH_LONG).show();
        }
        else{
            while(cursor.moveToNext()){
                String phn=cursor.getString(5);
                String pass=cursor.getString(3);
                if(phn.equals(phone) && pass.equals(password)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    public  Cursor showalldata(String bgroup,String dis){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM People_Info WHERE Blood_Group = ? AND District = ?", new String[] {bgroup,dis});
        return cursor;
    }
    public Cursor showdata(String phn){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM People_Info WHERE Mobile = ?", new String[]{phn});
        return cursor;
    }
    public boolean updatedata(String name,String email,String password,String b_group,String district,String mobile){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD,password);
        contentValues.put(B_GROUP, b_group);
        contentValues.put(DISTRICT, district);
        contentValues.put(MOBILE, mobile);
        sqLiteDatabase.update(TABLE_NAME,contentValues,MOBILE+" = ?",new String[]{mobile});
        return true;
    }
}