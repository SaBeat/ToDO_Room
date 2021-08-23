package com.example.roomdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase.model.UserEntity

@Database(entities = [UserEntity::class],version = 1,exportSchema = false)
abstract class UserDatabase:RoomDatabase() {

    abstract fun dao():UserDao

    companion object{
        private var INSTANCE:UserDatabase?=null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}