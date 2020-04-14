package fr.eni.abono.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import fr.eni.abono.bo.Category;
import fr.eni.abono.bo.Subscription;

@Database(entities = {Subscription.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SubscriptionDao subscriptionDao();
    public abstract CategoryDao categoryDao();
}
