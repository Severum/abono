package fr.eni.abono.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.eni.abono.bo.Category;

public interface CategoryDao {
    @Query("SELECT * FROM Category")
    List<Category> findAll();

    @Query("SELECT * FROM Subscription WHERE id = (:id)")
    Category findById(int id);

    @Query("SELECT * FROM Subscription WHERE name = (:name)")
    Category findByName(String name);

    @Insert
    void insert(Category s);

    @Delete
    void delete(Category s);
}
