package com.ideasApp.listofideas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideasApp.listofideas.domain.DeleteIdeaItemUseCase
import com.ideasApp.listofideas.domain.EditIdeaItemUseCase
import com.ideasApp.listofideas.domain.GetIdeasListUseCase
import com.ideasApp.listofideas.domain.IdeaItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getIdeasListUseCase: GetIdeasListUseCase,
    private val deleteIdeaItemUseCase: DeleteIdeaItemUseCase,
    private val editIdeaItemUseCase: EditIdeaItemUseCase,
): ViewModel() {

    val ideaList = getIdeasListUseCase.getIdeasList()

    fun deleteIdeaItem(ideaItem: IdeaItem) {
        viewModelScope.launch {
            deleteIdeaItemUseCase.deleteIdeaItem(ideaItem)
        }
    }

    fun changeEnableState(ideaItem: IdeaItem) {
        viewModelScope.launch {
            val newItem = ideaItem.copy(isEnabled = !ideaItem.isEnabled)
            editIdeaItemUseCase.editIdeaItem(newItem)
        }
    }
}