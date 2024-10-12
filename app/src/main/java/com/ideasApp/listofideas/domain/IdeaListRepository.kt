package com.ideasApp.listofideas.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface IdeaListRepository {

    fun addIdeaItem(ideaItem: IdeaItem)

    fun deleteIdeaItem(ideaItem: IdeaItem)

    fun editIdeaItem(ideaItem: IdeaItem)

    fun getIdeaItem(ideaItemId: Int):IdeaItem

    fun getIdeasList(): LiveData<List<IdeaItem>>
}