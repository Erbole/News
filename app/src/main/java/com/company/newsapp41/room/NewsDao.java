package com.company.newsapp41.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.company.newsapp41.model.Model;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Model model);

    @Query("SELECT * FROM  model ")
    List<Model> getAll();

    @Query("SELECT * FROM model ORDER BY `create` DESC")
    List<Model> sortAll();

    @Delete
    void deleteTask(Model model);

    @Query("SELECT * FROM model WHERE title LIKE '%' || :search || '%'")
    List<Model> getSearch(String search);

    @Query("SELECT * FROM model ORDER BY title ASC")
    List<Model> sort();
}
