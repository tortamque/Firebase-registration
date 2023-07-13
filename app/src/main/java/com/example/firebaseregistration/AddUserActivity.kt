package com.example.firebaseregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseregistration.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}