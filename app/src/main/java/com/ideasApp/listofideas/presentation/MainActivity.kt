package com.ideasApp.listofideas.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ideasApp.listofideas.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: IdeaItemAdapter

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.ideaList.observe(this) {
            adapter.ideaList = it
        }

    }

    private fun setupRecyclerView() {
        val rvIdeaList = findViewById<RecyclerView>(R.id.rv_idea_list)
        adapter = IdeaItemAdapter()
        rvIdeaList.adapter = adapter
    }


}