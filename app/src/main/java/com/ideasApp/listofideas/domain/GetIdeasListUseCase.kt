package com.ideasApp.listofideas.domain

class GetIdeasListUseCase(
    private val ideaListRepository: IdeaListRepository
) {
    fun getIdeasList(): List<IdeaItem> {
        return ideaListRepository.getIdeasList()
    }
}