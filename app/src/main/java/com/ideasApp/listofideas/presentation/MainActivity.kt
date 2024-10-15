package com.ideasApp.listofideas.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ideasApp.listofideas.R
import com.ideasApp.listofideas.domain.IdeaItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llIdeaList: LinearLayout

    private var count = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        llIdeaList = findViewById(R.id.ll_idea_list)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.ideaList.observe(this) {
          showList(it)
        }

    }

    fun showList(list: List<IdeaItem>) {
        llIdeaList.removeAllViews()
        for (ideaItem in list){
            val layoutId = if (ideaItem.isEnabled == false) {
                R.layout.item_idea_disabled
            } else {
                R.layout.item_idea_enabled
            }

            val view = LayoutInflater.from(this).inflate(layoutId, llIdeaList, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = ideaItem.ideaName
            tvCount.text = ideaItem.count.toString()
            view.setOnLongClickListener {
                viewModel.changeEnableState(ideaItem)
                true
            }
            llIdeaList.addView(view)
        }
    }
}