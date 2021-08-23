package com.example.roomdatabase.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.R
import com.example.roomdatabase.model.UserEntity
import com.example.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.userlist_row.*


class AddFragment : Fragment(R.layout.fragment_add) {
    lateinit var viewModel: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        btn_add.setOnClickListener {
            insertData()
        }

    }
    fun insertData(){
        val firstname=et_firstname.text.toString()
        val lastname=et_lastname.text.toString()

        if(inputCheck(firstname,lastname)){
            val user=UserEntity(0,firstname,lastname)
            viewModel.insert(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }
    fun inputCheck(firstname:String,lastname:String):Boolean{
        return !(TextUtils.isEmpty(firstname)||TextUtils.isEmpty(lastname))
    }
}