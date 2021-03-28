package com.example.teamviewapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.teamviewapp.databinding.ActivityCreatePlayerBinding
import com.google.firebase.firestore.FirebaseFirestore

class CreatePlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.viewRosterButton.setOnClickListener {
            if (binding.editTextPersonName.text.toString().isNotBlank() && binding.spinner.selectedItemPosition>0){
                val player = Player()
                player.name = binding.editTextPersonName.text.toString()
                player.appg = binding.spinner.selectedItem.toString().toInt()

                val db = FirebaseFirestore.getInstance().collection("players")
                player.id = db.document().id
                db.document(player.id!!).set(player)

            } else {
                Toast.makeText(this,"player name and average PPG required", Toast.LENGTH_LONG).show()
            }
            var intent = Intent(this, ViewRosterActivity::class.java)
            startActivity(intent)
        }
    }
}