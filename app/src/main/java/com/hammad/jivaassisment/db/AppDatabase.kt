package com.hammad.jivaassisment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hammad.jivaassisment.db.dao.UserDao
import com.hammad.jivaassisment.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}