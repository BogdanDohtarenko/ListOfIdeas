package com.ideasApp.listofideas.domain

import javax.inject.Inject

class AddIdeaItemUseCase @Inject constructor(
    private val ideaListRepository: IdeaListRepository
) {
    suspend fun addIdeaItem(ideaItem: IdeaItem) {
        ideaListRepository.addIdeaItem(ideaItem)
    }
}