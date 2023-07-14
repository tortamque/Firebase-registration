package com.example.firebaseregistration.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseregistration.Models.Users
import com.example.firebaseregistration.databinding.UserItemBinding

class UsersAdapter(
    var context: Context,
    var users: ArrayList<Users>
): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    inner class UsersViewHolder(val adapterBinding: UserItemBinding) : RecyclerView.ViewHolder(adapterBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UsersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.adapterBinding.nameTextView.text = users[position].name
        holder.adapterBinding.ageTextView.text = users[position].age.toString()
        holder.adapterBinding.emailTextView.text = users[position].email
    }
}