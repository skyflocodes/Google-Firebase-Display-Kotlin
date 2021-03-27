package com.example.teamviewapp;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.teamviewapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.i("LifeCycle", "MainActivity.onCreate() method")
        binding.button.setOnClickListener {
            var intent = Intent(this, ViewRosterActivity::class.java)
            startActivity(intent)
        }

    }
}