package ru.teacher.retrofit_sqliyte.API.Model;

import androidx.room.*;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User ORDER BY name DESC")
    List<User> getAllOrderByName();

    @Query("SELECT * FROM User WHERE id = :id")
    User getById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        //@Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Iterable<User> users);

    @Update
    void update(User user);

    @Update
    void update(Iterable<User> users);

    @Delete
    void delete(User user);
}


