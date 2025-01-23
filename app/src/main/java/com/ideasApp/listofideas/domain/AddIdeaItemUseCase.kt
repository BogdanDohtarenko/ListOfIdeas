package com.ideasApp.listofideas.domain

class AddIdeaItemUseCase(
    private val ideaListRepository: IdeaListRepository
) {
    suspend fun addIdeaItem(ideaItem: IdeaItem) {
        ideaListRepository.addIdeaItem(ideaItem)
    }
}