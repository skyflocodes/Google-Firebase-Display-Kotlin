package com.example.teamviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.teamviewapp.databinding.ActivityRecyclerViewPlayerListBinding


class RecyclerViewPlayerListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewPlayerListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewPlayerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_recycler_view_player_list)

        val model : PlayerListViewModel by viewModels()
        model.getPlayers().observe(this, Observer <List<Player>>{   players->
            var recyclerAdapter = RecyclerViewAdapter(this, players)
            binding.verticalRecyclerView.adapter = recyclerAdapter
        })
    }
}