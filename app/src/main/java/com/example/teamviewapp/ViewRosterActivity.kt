package com.example.teamviewapp;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.teamviewapp.databinding.ActivityViewRosterBinding
import com.google.firebase.firestore.FirebaseFirestore

class ViewRosterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewRosterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewRosterBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val db = FirebaseFirestore.getInstance().collection("players")
        db.get().addOnSuccessListener { documents ->
            for (document in documents){
                val player = document.toObject(Player::class.java)
                val textView = TextView(this)
                val playerString = player.fName + " " + player.lName + ", Averages: " + player.appg.toString() + " Points per game."
                textView.text = playerString
                textView.textSize = 20f

                binding.linearLayout.addView(textView)
            }
        }

        binding.createPlayerButton.setOnClickListener {
            var intent = Intent(this, CreatePlayerActivity::class.java)
            startActivity(intent)
        }
    }
}