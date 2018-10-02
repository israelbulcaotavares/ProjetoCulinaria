package com.tavares.projetoculinaria.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


@SuppressWarnings("WeakerAccess")
public class ReceitaHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "receitass.db";
    private static final int DATABASE_VERSION = 1;


    public ReceitaHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_QUERY = "CREATE TABLE " +
                ReceitaContract.ReceitaEntry.TABLE_NAME + " ( " +
                ReceitaContract.ReceitaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ReceitaContract.ReceitaEntry.RECEITA_NAME_COLUMN + " TEXT NOT NULL, " +
                ReceitaContract.ReceitaEntry.INGREDIENTS_COLUMN + " TEXT NOT NULL, " +
                ReceitaContract.ReceitaEntry.TIME_STAMP_COLUMN + " TIMESTAMP NOT NULL " +
                ");";
        sqLiteDatabase.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
