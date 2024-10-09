package com.ideasApp.listofideas.domain

class DeleteIdeaItemUseCase(
    private val itemListRepository: ItemListRepository
) {

    fun deleteIdeaItem(ideaItem: IdeaItem) {
        itemListRepository.deleteIdeaItem(ideaItem)
    }
}