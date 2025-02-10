package com.ideasApp.listofideas.domain

import javax.inject.Inject

class DeleteIdeaItemUseCase @Inject constructor(
    private val ideaListRepository: IdeaListRepository
) {

    suspend fun deleteIdeaItem(ideaItem: IdeaItem) {
        ideaListRepository.deleteIdeaItem(ideaItem)
    }
}