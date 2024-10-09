package com.ideasApp.listofideas.data

import com.ideasApp.listofideas.domain.IdeaItem
import com.ideasApp.listofideas.domain.IdeaListRepository

object IdeaListRepositoryImpl: IdeaListRepository {

    private val ideaList = mutableListOf<IdeaItem>()

    override fun addIdeaItem(ideaItem: IdeaItem) {
        ideaList.add(ideaItem)
    }

    override fun deleteIdeaItem(ideaItem: IdeaItem) {
        ideaList.remove(ideaItem)
    }

    override fun editIdeaItem(ideaItem: IdeaItem) {
        val oldItem = getIdeaItem(ideaItem.id)
        ideaList.remove(oldItem)
        ideaList.add(ideaItem)
    }

    override fun getIdeaItem(ideaItemId: Int): IdeaItem {
        return ideaList.find {
            it.id == ideaItemId
        } ?: throw RuntimeException("ELement with id $ideaItemId not found")
    }

    override fun getIdeasList(): List<IdeaItem> {
        return ideaList.toList()
    }
}