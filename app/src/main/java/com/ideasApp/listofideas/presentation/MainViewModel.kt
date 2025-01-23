package com.ideasApp.listofideas.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ideasApp.listofideas.data.IdeaListRepositoryImpl
import com.ideasApp.listofideas.domain.DeleteIdeaItemUseCase
import com.ideasApp.listofideas.domain.EditIdeaItemUseCase
import com.ideasApp.listofideas.domain.GetIdeasListUseCase
import com.ideasApp.listofideas.domain.IdeaItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application : Application): AndroidViewModel(application) {

    private var repository = IdeaListRepositoryImpl(application)

    private val getIdeasListUseCase = GetIdeasListUseCase(repository)
    private val deleteIdeaItemUseCase  = DeleteIdeaItemUseCase(repository)
    private val editIdeaItemUseCase = EditIdeaItemUseCase(repository)

    val ideaList = getIdeasListUseCase.getIdeasList()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun deleteIdeaItem(ideaItem: IdeaItem) {
        coroutineScope.launch {
            deleteIdeaItemUseCase.deleteIdeaItem(ideaItem)
        }
    }

    fun changeEnableState(ideaItem: IdeaItem) {
        coroutineScope.launch {
            val newItem = ideaItem.copy(isEnabled = !ideaItem.isEnabled)
            editIdeaItemUseCase.editIdeaItem(newItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}