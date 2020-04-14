package fr.eni.abono.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.eni.abono.bo.Subscription;

public interface SubscriptionDao  {
    @Query("SELECT * FROM Subscription")
    List<Subscription> findAll();

    @Query("SELECT * FROM Subscription WHERE id = (:id)")
    Subscription findById(int id);

    @Insert
    void insert(Subscription s);

    @Delete
    void delete(Subscription s);
}
