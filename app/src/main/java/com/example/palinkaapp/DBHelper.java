package com.example.palinkaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "palinkaAdatbazis.db";
    public static final String PALINKA_TABLE = "palinka";

    public static final String COL_ID = "id";
    public static final String COL_FOZO = "fozo";
    public static final String COL_GYUMOLCS = "gyumolcs";
    public static final String COL_ALKOHOL = "alkohol";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE " + PALINKA_TABLE + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_FOZO + " VARCHAR(255) NOT NULL, " +
                COL_GYUMOLCS + " VARCHAR(255) NOT NULL, " +
                COL_ALKOHOL + " INTEGER NOT NULL, " +
                "UNIQUE (" + COL_FOZO + "," + COL_GYUMOLCS + ")" +
                ")";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String sql = "DROP TABLE IF EXISTS " + PALINKA_TABLE;
        db.execSQL(sql);
        onCreate(db);
    }


    //SELECT
    public Cursor adatLekerdezes()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        //paraméteres stringmegszakítós
        //String nev ="Jozsi";
        //return db.rawQuery("SELECT * FROM "+TANULO_TABLE+" WHERE nev = ?",new String[]{nev});

        //fullos
        return db.query(PALINKA_TABLE,new String[]{COL_ID,COL_FOZO,COL_GYUMOLCS,COL_ALKOHOL},null,null,null,null,null,null);
    }

    //INSERT
    public boolean adatRogzites(String fozo, String gyumolcs, int alkohol)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FOZO, fozo);
        values.put(COL_GYUMOLCS, gyumolcs);
        values.put(COL_ALKOHOL, alkohol);
        long result = db.insert(PALINKA_TABLE, null, values);
        return result != -1;
    }
}
