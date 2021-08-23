package com.example.roomdatabase.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabase.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(entity:UserEntity)

    @Query("SELECT * FROM user_table")
    fun getAllData():LiveData<List<UserEntity>>

    @Delete
    suspend fun deleteData(entity: UserEntity)

    @Update
    suspend fun updateData(entity: UserEntity)
}