package com.example.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.model.UserEntity
import com.example.roomdatabase.ui.fragments.ListFragmentDirections
import kotlinx.android.synthetic.main.userlist_row.view.*

class UserAdapter(val listener:UserClickListener): RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private var userList= emptyList<UserEntity>()
    fun setDataList(user:List<UserEntity>){
        this.userList=user
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.userlist_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val a=userList[position]

        holder.itemView.text_id.text= a.id.toString()
        holder.itemView.text_firstname.text=a.firstname
        holder.itemView.text_lastname.text=a.lastname

        holder.itemView.image_delete.setOnClickListener {
           listener.onItemClickListener(a)
        }
//        holder.itemView.image_edit.setOnClickListener {
//            listener.onUpdateClickListener(a)
//        }

        holder.itemView.image_edit.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(a)
            holder.itemView.findNavController().navigate(action)
        }

    }
    interface UserClickListener{
        fun onItemClickListener(entity:UserEntity)
        fun onUpdateClickListener(entity: UserEntity)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}