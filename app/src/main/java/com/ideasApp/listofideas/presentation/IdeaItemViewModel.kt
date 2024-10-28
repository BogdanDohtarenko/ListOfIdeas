package com.ideasApp.listofideas.presentation

import androidx.lifecycle.ViewModel
import com.ideasApp.listofideas.data.IdeaListRepositoryImpl
import com.ideasApp.listofideas.domain.AddIdeaItemUseCase
import com.ideasApp.listofideas.domain.EditIdeaItemUseCase
import com.ideasApp.listofideas.domain.GetIdeaItemUseCase
import com.ideasApp.listofideas.domain.IdeaItem

class IdeaItemViewModel: ViewModel() {

    private var repository = IdeaListRepositoryImpl

    private val getIdeaItemUseCase = GetIdeaItemUseCase(repository)
    private val addIdeaItemUseCase = AddIdeaItemUseCase(repository)
    private val editIdeaItemUseCase = EditIdeaItemUseCase(repository)

    fun getIdeaItemUseCase(id: Int) {
        val item = getIdeaItemUseCase.getIdeaItem(id)
    }

    fun addIdeaItem(inputName: String?, inputDescription: String?) {
        val name = parseName(inputName)
        val description = parseName(inputDescription)
        val inputValid = validateInput(name, description)
        if (inputValid) {
            val ideaItem = IdeaItem(name , description , true)
            addIdeaItemUseCase.addIdeaItem(ideaItem)
        }
    }

    fun editIdeaItem(inputName: String?, inputDescription: String?){
        val name = parseName(inputName)
        val description = parseName(inputDescription)
        val inputValid = validateInput(name, description)
        if (inputValid) {
            val ideaItem = IdeaItem(name, description, true)
            editIdeaItemUseCase.editIdeaItem(ideaItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }


    private fun validateInput(name: String, description: String): Boolean {
        var result = true
        if (name.isBlank()) {
            // TODO show error input name
            result = false
        }
        else if (description.isBlank()) {
            // TODO show error description name
            result = false
        }
        return result
    }
}