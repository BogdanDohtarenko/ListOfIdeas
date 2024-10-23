package com.ideasApp.listofideas.presentation

import androidx.recyclerview.widget.DiffUtil
import com.ideasApp.listofideas.domain.IdeaItem

class IdeaItemDiffCallback(
    private val oldList: List<IdeaItem>,
    private val newList: List<IdeaItem>,
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int , newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int , newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition] //equals()
    }
}