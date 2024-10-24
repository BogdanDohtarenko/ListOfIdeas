package com.ideasApp.listofideas.presentation

import androidx.recyclerview.widget.DiffUtil
import com.ideasApp.listofideas.domain.IdeaItem

class IdeaItemDiffItemCallback: DiffUtil.ItemCallback<IdeaItem>() {
    override fun areItemsTheSame(oldItem: IdeaItem , newItem: IdeaItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: IdeaItem , newItem: IdeaItem): Boolean {
        return oldItem == newItem
    }

}