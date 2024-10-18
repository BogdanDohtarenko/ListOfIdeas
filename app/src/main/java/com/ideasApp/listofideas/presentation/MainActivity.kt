package com.ideasApp.listofideas.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ideasApp.listofideas.R
import com.ideasApp.listofideas.domain.IdeaItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var ideaItemAdapter: IdeaItemAdapter

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.ideaList.observe(this) {
            ideaItemAdapter.ideaList = it
        }

    }

    private fun setupRecyclerView() {
        val rvIdeaList = findViewById<RecyclerView>(R.id.rv_idea_list)
        with(rvIdeaList) {
            ideaItemAdapter = IdeaItemAdapter()
            adapter = ideaItemAdapter
            recycledViewPool.setMaxRecycledViews(
                IdeaItemAdapter.VIEW_TYPE_ENABLED,
                IdeaItemAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                IdeaItemAdapter.VIEW_TYPE_DISABLED,
                IdeaItemAdapter.MAX_POOL_SIZE
            )
        }
        ideaItemAdapter.onIdeaItemLongClickListener = object : IdeaItemAdapter.OnIdeaItemLongClickListener {
            override fun onIdeaItemClick(ideaItem: IdeaItem) {
                viewModel.changeEnableState(ideaItem)
            }
        }
    }


}