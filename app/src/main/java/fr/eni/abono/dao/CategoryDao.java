package fr.eni.abono.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.eni.abono.bo.Category;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM Category")
    List<Category> findAll();

    @Query("SELECT * FROM Category WHERE id = (:id)")
    Category findById(int id);

    @Query("SELECT * FROM Category WHERE name = (:name)")
    Category findByName(String name);

    @Insert
    void insert(Category s);

    @Update
    void update(Category s);

    @Delete
    void delete(Category s);
}
