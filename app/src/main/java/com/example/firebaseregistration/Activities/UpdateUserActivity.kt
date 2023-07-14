package com.example.firebaseregistration.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseregistration.databinding.ActivityUpdateUserBinding
import com.google.firebase.database.FirebaseDatabase

class UpdateUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateUserBinding

    val database = FirebaseDatabase.getInstance()
    val dbReference = database.reference.child("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Update User"

        initData()

        binding.updateButton.setOnClickListener {
            updateData()
        }
    }

    fun initData(){
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0).toString()
        val email = intent.getStringExtra("email")

        binding.nameEditTextUpdate.setText(name)
        binding.ageEditTextUpdate.setText(age)
        binding.emailEditTextUpdate.setText(email)
    }

    fun updateData(){
        val newName = binding.nameEditTextUpdate.text.toString()
        val newAge = binding.ageEditTextUpdate.text.toString().toInt()
        val newEmail = binding.emailEditTextUpdate.text.toString()
        val id = intent.getStringExtra("id").toString()

        val userMap = mutableMapOf<String, Any>(
            "id" to id,
            "name" to newName,
            "age" to newAge,
            "email" to newEmail
        )

        dbReference.child(id).updateChildren(userMap).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(applicationContext, "The user has been updated", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}