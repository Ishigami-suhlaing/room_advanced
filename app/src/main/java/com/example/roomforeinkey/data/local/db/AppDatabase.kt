package com.example.roomforeinkey.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomforeinkey.data.local.entities.ContactEntity
import com.example.roomforeinkey.data.local.entities.GroupEntity
import com.example.roomforeinkey.data.local.dao.ContactDao
import com.example.roomforeinkey.data.local.dao.GroupDao

@Database(entities = [GroupEntity::class, ContactEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "group_contact_db"
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
        }
    }
}