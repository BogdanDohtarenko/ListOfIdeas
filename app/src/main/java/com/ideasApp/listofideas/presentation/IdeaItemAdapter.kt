package com.ideasApp.listofideas.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ideasApp.listofideas.R
import com.ideasApp.listofideas.domain.IdeaItem

class IdeaItemAdapter: RecyclerView.Adapter<IdeaItemAdapter.IdeaItemViewHolder>() {

    companion object {
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
        const val MAX_POOL_SIZE = 10
    }

    interface OnIdeaItemLongClickListener {

        fun onIdeaItemClick(ideaItem: IdeaItem)

    }

    var onIdeaItemLongClickListener: OnIdeaItemLongClickListener? = null


    var ideaList = listOf<IdeaItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
        return if (ideaList[position].isEnabled)
            VIEW_TYPE_ENABLED
            else
            VIEW_TYPE_DISABLED
    }

    override fun onBindViewHolder(holder: IdeaItemViewHolder, position: Int) {
        val ideaItem = ideaList[position]
        holder.tvName.text = ideaItem.ideaName
        holder.tvCount.text = ideaItem.count.toString()
        holder.view.setOnLongClickListener {
            onIdeaItemLongClickListener?.onIdeaItemClick(ideaItem)
            true
        }
    }

    override fun getItemCount(): Int {
        return ideaList.size
    }

    class IdeaItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView = view.findViewById(R.id.tv_count)
    }


}