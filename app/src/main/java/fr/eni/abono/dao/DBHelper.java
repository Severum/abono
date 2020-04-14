package fr.eni.abono.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import fr.eni.abono.contracts.SubscriptionContract;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "abono.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SubscriptionContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SubscriptionContract.DROP_TABLE);
        db.execSQL(SubscriptionContract.CREATE_TABLE);
    }
}
