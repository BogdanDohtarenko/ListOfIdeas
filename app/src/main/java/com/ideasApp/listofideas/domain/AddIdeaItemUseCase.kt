package com.ideasApp.listofideas.domain

class AddIdeaItemUseCase(
    private val itemListRepository: ItemListRepository
) {
    fun addIdeaItem(ideaItem: IdeaItem) {
        itemListRepository.addIdeaItem(ideaItem)
    }
}