package com.example.teamviewapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (val context: Context, val players : List<Player>) : RecyclerView.Adapter<RecyclerViewAdapter.PlayerViewHolder>(){
    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val pointsTextView = itemView.findViewById<TextView>(R.id.pointsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        with(holder){
            val nameString = player.lName + ", " + player.fName
            nameTextView.text = nameString
            val pointsString = "APPG: " + player.appg.toString()
            pointsTextView.text = pointsString
        }
    }

    override fun getItemCount(): Int {
        return players.size
    }


}