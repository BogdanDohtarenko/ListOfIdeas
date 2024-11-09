package com.ideasApp.listofideas.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ideasApp.listofideas.R
import com.ideasApp.listofideas.domain.IdeaItem

class IdeaItemFragment(
    private val screenMode: String,
    private val ideaItemId: Int = IdeaItem.UNDEFINED_ID
): Fragment() {

    private lateinit var viewModel: IdeaItemViewModel

    private lateinit var inputLayoutName: TextInputLayout
    private lateinit var editTextName: TextInputEditText
    private lateinit var inputLayoutDescription: TextInputLayout
    private lateinit var editTextDescription: TextInputEditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_idea_item, container, false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        parseParams()
        viewModel = ViewModelProvider(this)[IdeaItemViewModel::class.java]
        initViews(view)
        addTextListeners()
        launchAppropriateMode()
        addObservers()
    }


    private fun launchAppropriateMode() {
        when(screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
     }

    private fun addTextListeners() {
        editTextName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence? ,
                start: Int ,
                count: Int ,
                after: Int
            ) {

            }
            override fun onTextChanged(s: CharSequence? , start: Int , before: Int , count: Int) {
                 viewModel.resetErrorInputName()
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })
        editTextDescription.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(
                s: CharSequence? ,
                start: Int ,
                count: Int ,
                after: Int
            ) {

            }
            override fun onTextChanged(s: CharSequence? , start: Int , before: Int , count: Int) {
                 viewModel.resetErrorInputDescription()
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun addObservers() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                 getString(R.string.error_input_text)
            } else {
                null
            }
            inputLayoutName.error = message
        }
        viewModel.errorInputDescription.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_description)
            } else {
                null
            }
            inputLayoutDescription.error = message
        }
        viewModel.exitEnabled.observe(viewLifecycleOwner) {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

     private fun launchEditMode() {
         viewModel.getIdeaItemUseCase(ideaItemId)
         viewModel.ideaItem.observe(viewLifecycleOwner) {
             editTextName.setText(it.ideaName)
             editTextDescription.setText(it.description)
         }
         saveButton.setOnClickListener {
             viewModel.editIdeaItem(editTextName.text?.toString(), editTextDescription.text?.toString())
         }
     }

     private fun launchAddMode() {
         saveButton.setOnClickListener {
             viewModel.addIdeaItem(editTextName.text?.toString(), editTextDescription.text?.toString())
         }
     }

     private fun parseParams() {
         if(screenMode != MODE_ADD && screenMode != MODE_EDIT) {
             throw RuntimeException("Unknown screen mode $screenMode")
         }
         if (screenMode == MODE_EDIT && ideaItemId == IdeaItem.UNDEFINED_ID) {
             throw RuntimeException("Param ideaItemId is absent")
         }
     }

     private fun initViews(view: View) {
         inputLayoutName = view.findViewById(R.id.TextInputLayout_name)
         editTextName = view.findViewById(R.id.edit_text_name)
         inputLayoutDescription = view.findViewById(R.id.TextInputLayout_description)
         editTextDescription = view.findViewById(R.id.edit_text_description)
         saveButton = view.findViewById(R.id.save_button)
     }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_ITEM_ID = "item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"

        private const val UNDEFINED_SCREEN_MODE = ""

        fun newInstanceEditItem(id: Int) : IdeaItemFragment {
            return IdeaItemFragment(MODE_EDIT, id)
        }

        fun newInstanceAddItem() : IdeaItemFragment {
            return IdeaItemFragment(MODE_ADD)
        }


        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, IdeaItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context , ideaItemId: Int): Intent {
            val intent = Intent(context, IdeaItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_ITEM_ID, ideaItemId)
            return intent
        }
    }
}