package com.ideasApp.listofideas.domain

class DeleteIdeaItemUseCase(
    private val ideaListRepository: IdeaListRepository
) {

    fun deleteIdeaItem(ideaItem: IdeaItem) {
        ideaListRepository.deleteIdeaItem(ideaItem)
    }
}