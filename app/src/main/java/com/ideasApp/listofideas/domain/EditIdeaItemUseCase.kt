package com.ideasApp.listofideas.domain

import javax.inject.Inject

class EditIdeaItemUseCase @Inject constructor(
    private val ideaListRepository: IdeaListRepository
) {
    suspend fun editIdeaItem(ideaItem: IdeaItem) {
        ideaListRepository.editIdeaItem(ideaItem)
    }
}