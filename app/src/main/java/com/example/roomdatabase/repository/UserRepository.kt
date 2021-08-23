package com.example.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabase.db.UserDao
import com.example.roomdatabase.model.UserEntity

class UserRepository (private val dao:UserDao){

    val getAllData:LiveData<List<UserEntity>> = dao.getAllData()

    suspend fun insert(entity: UserEntity)=dao.insertData(entity)
    suspend fun delete(entity: UserEntity)=dao.deleteData(entity)
    suspend fun update(entity: UserEntity)=dao.updateData(entity)
}