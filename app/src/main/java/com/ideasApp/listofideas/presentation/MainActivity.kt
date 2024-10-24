package com.ideasApp.listofideas.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ideasApp.listofideas.R
import com.ideasApp.listofideas.domain.IdeaItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var ideaListAdapter: IdeaListAdapter

    private var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.ideaList.observe(this) {
            ideaListAdapter.submitList(it)
        }

    }

    private fun setupRecyclerView() {
        val rvIdeaList = findViewById<RecyclerView>(R.id.rv_idea_list)
        with(rvIdeaList) {
            ideaListAdapter = IdeaListAdapter()
            adapter = ideaListAdapter
            recycledViewPool.setMaxRecycledViews(
                IdeaListAdapter.VIEW_TYPE_ENABLED,
                IdeaListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                IdeaListAdapter.VIEW_TYPE_DISABLED,
                IdeaListAdapter.MAX_POOL_SIZE
            )
        }
        setUpOnLongClickListener()
        setUpOnClickListener()
        setUpSwipeListener(rvIdeaList)
    }

    private fun setUpSwipeListener(rvIdeaList: RecyclerView?) {
        val itemTouchCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(
                0 ,
                ItemTouchHelper.LEFT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView ,
                    viewHolder: RecyclerView.ViewHolder ,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder , direction: Int) {
                    val ideaItem = ideaListAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteIdeaItem(ideaItem)
                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rvIdeaList)
    }

    private fun setUpOnClickListener() {
        ideaListAdapter.onIdeaItemClickListener = { ideaItem: IdeaItem ->
            Log.d("MainActivity" , "Clicked on ${ideaItem.id} item")
        }
    }

    private fun setUpOnLongClickListener() {
        ideaListAdapter.onIdeaItemLongClickListener = { ideaItem: IdeaItem ->
            viewModel.changeEnableState(ideaItem)
        }
    }


}