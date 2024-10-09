package com.ideasApp.listofideas.domain

class GetIdeaItemUseCase(
    private val ideaListRepository: IdeaListRepository
) {
    fun getIdeaItem(ideaItemId: Int):IdeaItem {
        return ideaListRepository.getIdeaItem(ideaItemId)
    }
}