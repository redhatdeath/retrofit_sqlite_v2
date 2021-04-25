package ru.teacher.retrofit_sqliyte.API.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppUserDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
}