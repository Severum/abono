package fr.eni.abono.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.eni.abono.bo.Subscription;
import fr.eni.abono.contracts.SubscriptionContract;

public class SubscriptionDAO {
    private SQLiteDatabase db;
    private DBHelper helper;

    public SubscriptionDAO(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insert(Subscription subscription) {
        ContentValues cv = new ContentValues();
        cv.put(SubscriptionContract.COLUMN_NAME, subscription.getName());
        cv.put(SubscriptionContract.COLUMN_DESCRIPTION, subscription.getDescription());
        cv.put(SubscriptionContract.COLUMN_FREQUENCY, subscription.getFrequency());
        cv.put(SubscriptionContract.COLUMN_PRICE, subscription.getPrice());
        cv.put(SubscriptionContract.COLUMN_PRIORITY, subscription.getPriority().toString());
        return db.insert(SubscriptionContract.TABLE_NAME, null, cv);
    }
}