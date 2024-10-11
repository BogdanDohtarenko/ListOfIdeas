package com.ideasApp.listofideas.domain

class AddIdeaItemUseCase(
    private val ideaListRepository: IdeaListRepository
) {
    fun addIdeaItem(ideaItem: IdeaItem) {
        ideaListRepository.addIdeaItem(ideaItem)
    }
}