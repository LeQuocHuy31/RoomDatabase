package com.example.roomdatabaseandroid.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomdatabaseandroid.user;

@Database(entities = {user.class},version = 2)
public abstract class UserDatabase extends RoomDatabase {

    static Migration Migration_from_1_to_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user add COLUMN year TEXT");
        }
    };
    private  static  final  String DATABASE_NAME="user.db";
    private  static  UserDatabase instance;
    //Hàm khởi tạo instance
    public  static synchronized  UserDatabase getInstance(Context context){
        if(instance==null){
            //có ba thành phần biến môi trường,class room database và database name
            instance= Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()// cho phép thực hiện truy vấn mainThread
                    .addMigrations(Migration_from_1_to_2)
                    .build();
        }
        return instance;
    }
    public abstract UserDAO UserDAO();
}
