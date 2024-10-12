package com.ideasApp.listofideas.domain

import androidx.lifecycle.LiveData

class GetIdeasListUseCase(
    private val ideaListRepository: IdeaListRepository
) {
    fun getIdeasList(): LiveData<List<IdeaItem>> {
        return ideaListRepository.getIdeasList()
    }
}