package com.ideasApp.listofideas.presentation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.getColumnIndex
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ideasApp.listofideas.R
import com.ideasApp.listofideas.domain.IdeaItem
import javax.inject.Inject
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), IdeaItemFragment.OnEditingItem {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var ideaListAdapter: IdeaListAdapter
    @Inject
    lateinit var ideaListViewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as IdeaApplication).component
    }

    private var ideaItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupContainerAndRecyclerView()
        setupViewModelAndObservers()
        setOnAddButtonClickListener()
        thread {
            val cursor = contentResolver.query(
                Uri.parse("content://com.ideasApp.listofideas/IdeaItems"),
                null,
                null,
                null,
                null,
                null,
            )
            while (cursor?.moveToNext() == true) {
                val ideaName = cursor.getString(cursor.getColumnIndexOrThrow("ideaName"))
                val description = cursor.getString(cursor.getColumnIndexOrThrow("description"))
                val isEnabled = cursor.getInt(cursor.getColumnIndexOrThrow("isEnabled")) > 0
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val ideaItem = IdeaItem(ideaName, description, isEnabled, id)
                Log.d("IdeaListProvider", ideaItem.toString())
            }
        }
    }

    private fun setupViewModelAndObservers() {
        viewModel=ViewModelProvider(this,ideaListViewModelFactory)[MainViewModel::class.java]
        viewModel.ideaList.observe(this) {
            ideaListAdapter.submitList(it)
        }
    }

    private fun setupContainerAndRecyclerView() {
        ideaItemContainer=findViewById(R.id.idea_item_container)
        setupRecyclerView()
    }

    override fun onEditingFinished() {
        supportFragmentManager.popBackStack()
    }

    private fun setupRecyclerView() {
        val rvIdeaList = findViewById<RecyclerView>(R.id.rv_idea_list)
        with(rvIdeaList) {
            ideaListAdapter = IdeaListAdapter()
            adapter = ideaListAdapter
            recycledViewPool.setMaxRecycledViews(
                IdeaListAdapter.VIEW_TYPE_ENABLED ,
                IdeaListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                IdeaListAdapter.VIEW_TYPE_DISABLED ,
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

    private fun isLandscapeOrientation(): Boolean {
        return ideaItemContainer != null
    }

    private fun setUpOnClickListener() {
        val landscapeOrientation = isLandscapeOrientation()
        ideaListAdapter.onIdeaItemClickListener = {
            if (landscapeOrientation) {
                launchIdeaItemFragmentEditMode(it.id)
            } else {
                startActivity(IdeaItemActivity.newIntentEditItem(this , it.id))
            }
        }
    }

    private fun setUpOnLongClickListener() {
        ideaListAdapter.onIdeaItemLongClickListener = { ideaItem: IdeaItem ->
            viewModel.changeEnableState(ideaItem)
        }
    }

    private fun setOnAddButtonClickListener() {
        val addIdeaItemButton = findViewById<FloatingActionButton>(R.id.button_add_idea_item)
        val landscapeOrientation = isLandscapeOrientation()
        addIdeaItemButton.setOnClickListener {
            if (landscapeOrientation) {
                launchIdeaItemFragmentAddMode()
            } else {
                startActivity(IdeaItemActivity.newIntentAddItem(this))
            }
        }
    }

    private fun launchIdeaItemFragmentEditMode(ideaItemId: Int) {
        val fragment = IdeaItemFragment.newInstanceEditItem(ideaItemId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.idea_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun launchIdeaItemFragmentAddMode() {
        val fragment = IdeaItemFragment.newInstanceAddItem()
        supportFragmentManager.beginTransaction()
            .replace(R.id.idea_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }


}