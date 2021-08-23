package com.example.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.db.UserDatabase
import com.example.roomdatabase.model.UserEntity
import com.example.roomdatabase.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {

    val readAllData:LiveData<List<UserEntity>>
    private val repository:UserRepository

    init {
       val dao=UserDatabase.getDatabase(application).dao()
       repository= UserRepository(dao)
        readAllData=repository.getAllData
    }

   fun insert(entity: UserEntity)=viewModelScope.launch {
       repository.insert(entity)
   }
    fun delete(entity: UserEntity)=viewModelScope.launch {
        repository.delete(entity)
    }
    fun update(entity: UserEntity)=viewModelScope.launch {
        repository.update(entity)
    }

}