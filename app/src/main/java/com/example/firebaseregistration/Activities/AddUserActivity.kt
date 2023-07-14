package com.example.firebaseregistration.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseregistration.Models.Users
import com.example.firebaseregistration.databinding.ActivityAddUserBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddUserBinding

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val dbReference: DatabaseReference = database.reference.child("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title = "Add user"

        binding.registerButton.setOnClickListener {
            addUserToDatabase()
        }
    }

    fun addUserToDatabase(){
        val name = binding.nameEditText.text.toString()
        val age = binding.ageEditText.text.toString().toInt()
        val email = binding.emailEditText.text.toString()
        val id = dbReference.push().key.toString()

        val user = Users(id, name, age, email)

        dbReference.child(id).setValue(user).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(applicationContext, "User was successfully registered", Toast.LENGTH_SHORT).show()
                finish()
            } else{
                Toast.makeText(applicationContext, "Oops :(\nAn error occurred: ${task.exception}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}