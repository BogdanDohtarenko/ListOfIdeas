package com.ideasApp.listofideas.domain

class EditIdeaItemUseCase(
    private val ideaListRepository: IdeaListRepository
) {
    suspend fun editIdeaItem(ideaItem: IdeaItem) {
        ideaListRepository.editIdeaItem(ideaItem)
    }
}