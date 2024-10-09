package com.ideasApp.listofideas.domain

class GetIdeaItemUseCase(
    private val itemListRepository: ItemListRepository
) {
    fun getIdeaItem(ideaItemId: Int):IdeaItem {
        return itemListRepository.getIdeaItem(ideaItemId)
    }
}