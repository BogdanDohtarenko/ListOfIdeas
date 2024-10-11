package com.ideasApp.listofideas.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ideasApp.listofideas.data.IdeaListRepositoryImpl
import com.ideasApp.listofideas.domain.DeleteIdeaItemUseCase
import com.ideasApp.listofideas.domain.EditIdeaItemUseCase
import com.ideasApp.listofideas.domain.GetIdeasListUseCase
import com.ideasApp.listofideas.domain.IdeaItem

class MainViewModel: ViewModel() {

    var repository = IdeaListRepositoryImpl

    val getIdeasListUseCase = GetIdeasListUseCase(repository)
    val deleteIdeaItemUseCase  = DeleteIdeaItemUseCase(repository)
    val editIdeaItemUseCase = EditIdeaItemUseCase(repository)

    val ideaList = MutableLiveData<List<IdeaItem>>()

    fun getIdeaList() {
        val list = getIdeasListUseCase.getIdeasList()
        ideaList.value = list
    }

}