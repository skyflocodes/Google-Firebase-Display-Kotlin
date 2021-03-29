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
            if (binding.editTextPersonLastName.text.toString().isNotBlank() && binding.editTextPersonFirstName.text.toString().isNotBlank() && binding.editPlayerNumber.text.isNotBlank() && binding.editPlayerNumber.text.toString().toInt()>=0 && binding.editPlayerNumber.text.toString().toInt()<100){
                val player = Player()
                val firstName = binding.editTextPersonFirstName.text.toString()
                player.fName = firstName.capitalize()
                val lastName = binding.editTextPersonLastName.text.toString()
                player.lName = lastName.capitalize()
                player.appg = binding.editPlayerNumber.text.toString().toInt()

                val db = FirebaseFirestore.getInstance().collection("players")
                player.id = db.document().id
                db.document(player.id!!).set(player)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Player Added!", Toast.LENGTH_LONG).show()
                        binding.editTextPersonFirstName.setText("")
                        binding.editTextPersonLastName.setText("")
                        binding.editPlayerNumber.setSelection(0)
                    }
                    .addOnFailureListener{
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                var intent = Intent(this, ViewRosterActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this,"Player name and average PPG required. PPG must also be between 1 and 99.", Toast.LENGTH_LONG).show()
            }

        }
    }
}