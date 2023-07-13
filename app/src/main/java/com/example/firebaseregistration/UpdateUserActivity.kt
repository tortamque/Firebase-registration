package com.example.firebaseregistration

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
    }
}