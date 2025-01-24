package com.ideasApp.listofideas.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface IdeaListRepository {

    suspend fun addIdeaItem(ideaItem: IdeaItem)

    suspend fun deleteIdeaItem(ideaItem: IdeaItem)

    suspend fun editIdeaItem(ideaItem: IdeaItem)

    suspend fun getIdeaItem(ideaItemId: Int):IdeaItem

    fun getIdeasList(): LiveData<List<IdeaItem>>
}