package com.LH1109038.teamviewapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.LH1109038.teamviewapp.databinding.ActivityCreatePlayerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreatePlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePlayerBinding
    private val authDb = FirebaseAuth.getInstance();

    private fun logout(){
        authDb.signOut()
        finish()
        var intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        if (authDb.currentUser == null){
            logout()
        }

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
                Toast.makeText(this,"Player name and average PPG required. PPG must also be between 0 and 99.", Toast.LENGTH_LONG).show()
            }

        }
    }
}