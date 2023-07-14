package com.example.firebaseregistration.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseregistration.Adapters.UsersAdapter
import com.example.firebaseregistration.Models.Users
import com.example.firebaseregistration.databinding.ActivityMainBinding
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val database = FirebaseDatabase.getInstance()
    val dbReference = database.getReference().child("Users")

    val users = ArrayList<Users>()
    lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

        retrieveData()
    }

    fun retrieveData(){
        dbReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                users.clear()

                for (dbUser in snapshot.children){
                    val user = dbUser.getValue(Users::class.java)

                    if(user != null){
                        users.add(user)
                    }

                    usersAdapter = UsersAdapter(this@MainActivity, users)

                    binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.recyclerView.adapter = usersAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}