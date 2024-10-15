package com.ideasApp.listofideas.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ideasApp.listofideas.R
import com.ideasApp.listofideas.domain.IdeaItem

class IdeaItemAdapter: RecyclerView.Adapter<IdeaItemAdapter.IdeaItemViewHolder>() {

    private val list = listOf<IdeaItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdeaItemViewHolder {
//        val layoutId = if (ideaItem.isEnabled == false) {
//            R.layout.item_idea_disabled
//        } else {
//            R.layout.item_idea_enabled
//        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_idea_disabled, parent, false)
        return IdeaItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: IdeaItemViewHolder, position: Int) {
        val ideaItem = list[position]
        holder.tvName.text = ideaItem.ideaName
        holder.tvCount.text = ideaItem.count.toString()
        holder.view.setOnLongClickListener {
            true
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class IdeaItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView = view.findViewById(R.id.tv_count)
    }


}