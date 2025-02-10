package com.ideasApp.listofideas.domain

import javax.inject.Inject

class GetIdeaItemUseCase @Inject constructor(
    private val ideaListRepository: IdeaListRepository
) {
    suspend fun getIdeaItem(ideaItemId: Int):IdeaItem {
        return ideaListRepository.getIdeaItem(ideaItemId)
    }
}