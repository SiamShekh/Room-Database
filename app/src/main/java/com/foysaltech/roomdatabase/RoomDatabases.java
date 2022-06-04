package com.foysaltech.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class RoomDatabases extends androidx.room.RoomDatabase {
    public abstract UserDao userDao();
}

