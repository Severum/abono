package fr.eni.abono.dao;

import android.content.Context;
import android.provider.DocumentsContract;

import androidx.room.Room;

public class Connexion {
    public static AppDatabase getConnexion(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "AbonoDB").build();
    }
}
