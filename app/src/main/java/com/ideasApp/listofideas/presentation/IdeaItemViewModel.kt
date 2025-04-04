package com.ideasApp.listofideas.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideasApp.listofideas.domain.AddIdeaItemUseCase
import com.ideasApp.listofideas.domain.EditIdeaItemUseCase
import com.ideasApp.listofideas.domain.GetIdeaItemUseCase
import com.ideasApp.listofideas.domain.IdeaItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class IdeaItemViewModel @Inject constructor(
    private val getIdeaItemUseCase: GetIdeaItemUseCase,
    private val addIdeaItemUseCase: AddIdeaItemUseCase,
    private val editIdeaItemUseCase: EditIdeaItemUseCase,
): ViewModel() {

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputDescription = MutableLiveData<Boolean>()
    val errorInputDescription: LiveData<Boolean>
        get() = _errorInputDescription

    private val _ideaItem = MutableLiveData<IdeaItem>()
    val ideaItem: LiveData<IdeaItem>
        get() = _ideaItem

    private val _exitEnabled = MutableLiveData<Unit>()
    val exitEnabled: LiveData<Unit>
        get() = _exitEnabled

    fun getIdeaItemUseCase(id: Int) {
        viewModelScope.launch {
            _ideaItem.value = getIdeaItemUseCase.getIdeaItem(id)
        }
    }

    fun addIdeaItem(inputName: String?, inputDescription: String?) {
        viewModelScope.launch {
            val name = parseName(inputName)
            val description = parseName(inputDescription)
            val inputValid = validateInput(name , description)
            if (inputValid) {
                val ideaItem = IdeaItem(name , description , true)
                addIdeaItemUseCase.addIdeaItem(ideaItem)
                finishWork()
            }
        }
    }

    fun editIdeaItem(inputName: String?, inputDescription: String?){
        viewModelScope.launch {
            val name = parseName(inputName)
            val description = parseName(inputDescription)
            val inputValid = validateInput(name , description)

            if (inputValid) {
                _ideaItem.value?.let {
                    val item = it.copy(ideaName = name , description = description)
                    editIdeaItemUseCase.editIdeaItem(item)
                    finishWork()
                }
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun validateInput(name: String, description: String): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        else if (description.isBlank()) {
            _errorInputDescription.value = true
            result = false
        }
        return result
    }

    private fun finishWork() {
        _exitEnabled.value = Unit
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputDescription() {
        _errorInputDescription.value = false
    }
}