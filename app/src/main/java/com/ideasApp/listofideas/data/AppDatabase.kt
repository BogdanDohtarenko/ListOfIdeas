package com.ideasApp.listofideas.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [IdeaItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        private var INSTANCE:AppDatabase? = null
        private val LOCK = Any()
        private const val DATABASE_NAME = "idea_item.db"

        fun getInstance(application : Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db =  Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

}