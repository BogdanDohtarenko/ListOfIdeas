package com.ideasApp.listofideas.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ideasApp.listofideas.data.IdeaListRepositoryImpl
import com.ideasApp.listofideas.domain.DeleteIdeaItemUseCase
import com.ideasApp.listofideas.domain.EditIdeaItemUseCase
import com.ideasApp.listofideas.domain.GetIdeasListUseCase
import com.ideasApp.listofideas.domain.IdeaItem

class MainViewModel: ViewModel() {

    private var repository = IdeaListRepositoryImpl

    private val getIdeasListUseCase = GetIdeasListUseCase(repository)
    private val deleteIdeaItemUseCase  = DeleteIdeaItemUseCase(repository)
    private val editIdeaItemUseCase = EditIdeaItemUseCase(repository)

    val ideaList = getIdeasListUseCase.getIdeasList()

    fun deleteIdeaItem(ideaItem: IdeaItem) {
        deleteIdeaItemUseCase.deleteIdeaItem(ideaItem)
    }

    fun changeEnableState(ideaItem: IdeaItem) {
        val newItem = ideaItem.copy(isEnabled = !ideaItem.isEnabled)
        editIdeaItemUseCase.editIdeaItem(newItem)
    }

}