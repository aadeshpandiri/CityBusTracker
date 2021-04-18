package com.example.CityBus.database;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance==null)
            instance = new DatabaseAccess(context);
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null)
            this.db.close();
    }

    public Cursor getBusData(String sourcename, String destname){
        // %sourcename%destinationname%
        c=db.rawQuery("select  BUS_NAME, ROUTE_START, ROUTE_END,ROUTE_MAP from BusesData where ROUTE_MAP like  '%"+sourcename+"%"+destname+"%'",new String[]{});
//        List<ArrayList<String>> data = new ArrayList<>();
//        while(c.moveToNext()){
//            String busnumber = c.getString(0);
//            String source = c.getString(1);
//            String destination1 = c.getString(2);
//            String routemap = c.getString(3);
//            ArrayList<String> data1 = new ArrayList<>();
//            data1.add(busnumber);
//            data1.add(source);
//            data1.add(destination1);
//            data1.add(routemap);
//            data.add(data1);
//        }
        return c;
    }
}
