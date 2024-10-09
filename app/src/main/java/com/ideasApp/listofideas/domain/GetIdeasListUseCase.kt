package com.ideasApp.listofideas.domain

class GetIdeasListUseCase(
    private val itemListRepository: ItemListRepository
) {
    fun getIdeasList(): List<IdeaItem> {
        return itemListRepository.getIdeasList()
    }
}