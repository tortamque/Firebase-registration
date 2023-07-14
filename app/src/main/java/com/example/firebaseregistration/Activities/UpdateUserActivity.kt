package com.example.firebaseregistration.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseregistration.databinding.ActivityUpdateUserBinding

class UpdateUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Update User"

        initData()
    }

    fun initData(){
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0).toString()
        val email = intent.getStringExtra("email")

        binding.nameEditTextUpdate.setText(name)
        binding.ageEditTextUpdate.setText(age)
        binding.emailEditTextUpdate.setText(email)
    }
}