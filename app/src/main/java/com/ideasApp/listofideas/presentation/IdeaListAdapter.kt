package com.ideasApp.listofideas.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ideasApp.listofideas.R
import com.ideasApp.listofideas.domain.IdeaItem

class IdeaListAdapter: ListAdapter<IdeaItem, IdeaItemViewHolder>(IdeaItemDiffItemCallback()) {

    companion object {
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
        const val MAX_POOL_SIZE = 10
    }


    var onIdeaItemLongClickListener: ((IdeaItem) -> Unit)? = null
    var onIdeaItemClickListener: ((IdeaItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdeaItemViewHolder {
        val viewId = when(viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_idea_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_idea_disabled
            else -> throw RuntimeException("Unknown view type")
        }
        val view = LayoutInflater.from(parent.context).inflate(viewId, parent, false)
        return IdeaItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isEnabled)
            VIEW_TYPE_ENABLED
            else
            VIEW_TYPE_DISABLED
    }

    override fun onBindViewHolder(holder: IdeaItemViewHolder, position: Int) {
        val ideaItem = getItem(position)
        holder.tvName.text = ideaItem.ideaName
        holder.tvCount.text = ideaItem.count.toString()
        holder.view.setOnLongClickListener {
            onIdeaItemLongClickListener?.invoke(ideaItem)
            true
        }
        holder.view.setOnClickListener {
            onIdeaItemClickListener?.invoke(ideaItem)
        }
    }



}