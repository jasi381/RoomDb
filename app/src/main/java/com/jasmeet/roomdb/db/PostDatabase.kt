package com.jasmeet.roomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jasmeet.roomdb.models.PostsItem

@Database(entities = [PostsItem::class], version = 1, exportSchema = false)
abstract class PostDatabase :RoomDatabase() {

    abstract fun getPostDao(): PostDao

    companion object{

        private var database: PostDatabase? = null
        private const val DATABASE_NAME = "post_db"

        @Synchronized
        fun getInstance(context: Context): PostDatabase? {
            if (database == null) {
                synchronized(PostDatabase::class) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        PostDatabase::class.java, DATABASE_NAME
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return database
        }
    }
}