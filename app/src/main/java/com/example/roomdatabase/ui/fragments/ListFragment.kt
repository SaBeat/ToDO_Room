package com.example.roomdatabase.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.R
import com.example.roomdatabase.adapter.UserAdapter
import com.example.roomdatabase.model.UserEntity
import com.example.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.custom_delete_dialog.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.userlist_row.*


class ListFragment : Fragment(R.layout.fragment_list),UserAdapter.UserClickListener {
    lateinit var viewModel: UserViewModel
   // private val args by navArgs<>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userAdapter=UserAdapter(this)
        rv_listFragment.apply {
            adapter=userAdapter
            layoutManager=LinearLayoutManager(requireContext())
        }

        viewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer {
            userAdapter.setDataList(it)
        })


        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

//       btn_update.setOnClickListener {
//           findNavController().navigate(R.id.action_listFragment_to_updateFragment)
//       }

       }


    override fun onItemClickListener(entity: UserEntity) {
        viewModel.delete(entity)
    }

    override fun onUpdateClickListener(entity: UserEntity) {
        findNavController().navigate(R.id.action_listFragment_to_updateFragment)
    }


}