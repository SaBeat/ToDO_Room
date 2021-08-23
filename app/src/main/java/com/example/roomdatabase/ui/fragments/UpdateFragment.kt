package com.example.roomdatabase.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabase.R
import com.example.roomdatabase.adapter.UserAdapter
import com.example.roomdatabase.model.UserEntity
import com.example.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.userlist_row.*


class UpdateFragment : Fragment(R.layout.fragment_update) {
    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var viewModel: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        et_update_firstname.setText(args.current.firstname)
        et_update_lastname.setText(args.current.lastname)


        btn_update.setOnClickListener {
            updateData()
        }
    }

    fun updateData() {
        val firstName = et_update_firstname.text.toString()
        val lastName = et_update_lastname.text.toString()

        if (inputCheck(firstName, lastName)) {
            val updateUser = UserEntity(args.current.id, firstName, lastName)

            viewModel.update(updateUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName))
    }


}