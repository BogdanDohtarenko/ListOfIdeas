package com.ideasApp.listofideas.domain

interface ItemListRepository {

    fun addIdeaItem(ideaItem: IdeaItem)

    fun deleteIdeaItem(ideaItem: IdeaItem)

    fun editIdeaItem(ideaItem: IdeaItem)

    fun getIdeaItem(ideaItemId: Int):IdeaItem

    fun getIdeasList(): List<IdeaItem>
}