package com.LH1109038.teamviewapp;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.LH1109038.teamviewapp.databinding.ActivityViewRosterBinding

class ViewRosterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewRosterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewRosterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val model : PlayerListViewModel by viewModels()
        model.getPlayers().observe(this, Observer <List<Player>>{   players->
            var recyclerAdapter = RecyclerViewAdapter(this, players)
            binding.verticalRecyclerView.adapter = recyclerAdapter
        })

//        val model : PlayerListViewModel by viewModels()
//        model.getPlayers().observe( this) {
//            binding.linearLayout.removeAllViews()
//
//            for (player in it) {
//                val textView = TextView(this)
//                val playerString = player.fName + " " + player.lName + ", Averages: " + player.appg.toString() + " Points per game."
//                textView.text = playerString
//                textView.textSize = 20f
//
//                binding.linearLayout.addView(textView)
//            }
//        }

//        val db = FirebaseFirestore.getInstance().collection("players")
//        db.get().addOnSuccessListener { documents ->
//            for (document in documents){
//                val player = document.toObject(Player::class.java)
//                val textView = TextView(this)
//                val playerString = player.fName + " " + player.lName + ", Averages: " + player.appg.toString() + " Points per game."
//                textView.text = playerString
//                textView.textSize = 20f
//
//                binding.linearLayout.addView(textView)
//            }
//        }

        binding.createPlayerButton.setOnClickListener {
            var intent = Intent(this, CreatePlayerActivity::class.java)
            startActivity(intent)
        }
    }
}