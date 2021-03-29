package com.example.teamviewapp

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class PlayerListViewModel : ViewModel(){

    private val players = MutableLiveData<List<Player>>()

    init {
        loadPlayers()
    }

    fun getPlayers() : LiveData<List<Player>>{
        return players
    }

    private fun loadPlayers(){
        val db = FirebaseFirestore.getInstance().collection("players")
                .orderBy("lname", Query.Direction.ASCENDING)
        db.addSnapshotListener{ documents, exception ->
            if (exception != null){
                return@addSnapshotListener
            }

            val playerList = ArrayList<Player>()

            if (documents != null) {
                for (document in documents){
                    val player = document.toObject(Player::class.java)
                    playerList.add(player)
                }
                players.value = playerList
            }
        }
    }

}