package com.ideasApp.listofideas.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetIdeasListUseCase @Inject constructor(
    private val ideaListRepository: IdeaListRepository
) {
    fun getIdeasList(): LiveData<List<IdeaItem>> {
        return ideaListRepository.getIdeasList()
    }
}