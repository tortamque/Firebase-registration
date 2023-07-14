package com.example.firebaseregistration.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseregistration.Activities.UpdateUserActivity
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

        holder.adapterBinding.linearLayout.setOnClickListener {
            val intent = Intent(context, UpdateUserActivity::class.java)
            intent.putExtra("id", users[position].id)
                .putExtra("name", users[position].name)
                .putExtra("age", users[position].age)
                .putExtra("email", users[position].email)

            context.startActivity(intent)
        }
    }

    fun getUserId(position: Int): String{
        return users[position].id
    }
}