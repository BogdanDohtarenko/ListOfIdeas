package com.ideasApp.listofideas.domain

class DeleteIdeaItemUseCase(
    private val ideaListRepository: IdeaListRepository
) {

    suspend fun deleteIdeaItem(ideaItem: IdeaItem) {
        ideaListRepository.deleteIdeaItem(ideaItem)
    }
}