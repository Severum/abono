package fr.eni.abono.dao;

import android.content.Context;

import androidx.room.Room;

public class Connexion {
    public static AppDatabase getConnexion(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "abono.db").build();
    }
}
