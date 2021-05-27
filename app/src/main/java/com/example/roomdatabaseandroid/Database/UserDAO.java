package com.example.roomdatabaseandroid.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabaseandroid.user;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(user user);
    @Query("select * from user")
    List<user> getListUser();
   @Query("select * from user where username= :username")
    List<user> checkUser(String username);
   @Update
   void updateUser(user user);
   @Delete
   void deleteUser(user user);
   @Query("DELETE FROM user")
   void deleteAllUser();
   @Query("SELECT * FROM user WHERE username LIKE '%'||:name||'%'")
   List<user> searchUser(String name);
}
