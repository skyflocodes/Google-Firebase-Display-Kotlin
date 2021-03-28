package com.example.teamviewapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.teamviewapp.databinding.ActivityCreatePlayerBinding

class CreatePlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.viewRosterButton.setOnClickListener {
            var intent = Intent(this, ViewRosterActivity::class.java)
            startActivity(intent)
        }
    }
}