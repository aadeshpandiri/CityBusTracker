package com.example.CityBus.database;

import android.content.Context;


import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "busesdata.db";
    private static final int DATABASE_VERSION =1;

    public DatabaseOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
}
