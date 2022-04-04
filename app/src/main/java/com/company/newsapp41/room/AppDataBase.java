package com.company.newsapp41.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.company.newsapp41.model.Model;

@Database(entities = {Model.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract  NewsDao newsDao();
}