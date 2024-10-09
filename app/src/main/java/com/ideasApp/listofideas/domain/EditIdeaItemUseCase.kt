package com.ideasApp.listofideas.domain

class EditIdeaItemUseCase(
    private val itemListRepository: ItemListRepository
) {
    fun editIdeaItem(ideaItem: IdeaItem) {
        itemListRepository.editIdeaItem(ideaItem)
    }
}