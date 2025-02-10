package com.ideasApp.listofideas.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import javax.inject.Inject


class IdeaItemFragment @Inject constructor(): Fragment() {

    private val component by lazy {
        (requireActivity().application as IdeaApplication).component
    }

    @Inject
    lateinit var ideaListViewModelFactory: ViewModelFactory

    private lateinit var onEditingItem: OnEditingItem
    private lateinit var viewModel: IdeaItemViewModel

    private lateinit var inputLayoutName: TextInputLayout
    private lateinit var editTextName: TextInputEditText
    private lateinit var inputLayoutDescription: TextInputLayout
    private lateinit var editTextDescription: TextInputEditText
    private lateinit var saveButton: Button

    private var screenMode: String = UNDEFINED_SCREEN_MODE
    private var ideaItemId: Int = IdeaItem.UNDEFINED_ID

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        Log.d("IdeaItemFragment", "onAttach")
        if (context is OnEditingItem)
            onEditingItem = context //cast to interface
        else
            throw RuntimeException(" Activity must implement OnEditingItem interface ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("IdeaItemFragment", "onCreate")
        parseParams()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("IdeaItemFragment", "onCreateView")
        return inflater.inflate(R.layout.fragment_idea_item, container, false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        Log.d("IdeaItemFragment", "onViewCreated")
        viewModel = ViewModelProvider(this, ideaListViewModelFactory)[IdeaItemViewModel::class.java]
        initViews(view)
        addTextListeners()
        launchAppropriateMode()
        addObservers()
    }

    override fun onStart() {
        Log.d("IdeaItemFragment", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("IdeaItemFragment", "onStart")
        super.onResume()
    }

    override fun onPause() {
        Log.d("IdeaItemFragment", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("IdeaItemFragment", "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("IdeaItemFragment", "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("IdeaItemFragment", "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("IdeaItemFragment", "onDetach")
        super.onDetach()
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
            onEditingItem.onEditingFinished()
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
         val args = requireArguments() //non-nullable (IF NULL WILL BE CRUSH)
         if(!args.containsKey(SCREEN_MODE)) {
             throw RuntimeException("Param screen mode is absent")
         }
         val mode = args.getString(SCREEN_MODE)
         if(mode != MODE_ADD && mode != MODE_EDIT) {
             throw RuntimeException("Unknown screen mode $mode")
         }
         screenMode = mode
         if (screenMode == MODE_EDIT) {
             if (!args.containsKey(ITEM_ID)) {
                 throw RuntimeException("Param ideaItemId is absent")
             }
             ideaItemId = args.getInt(ITEM_ID , IdeaItem.UNDEFINED_ID)
         }
     }

    private fun initViews(view: View) {
         inputLayoutName = view.findViewById(R.id.TextInputLayout_name)
         editTextName = view.findViewById(R.id.edit_text_name)
         inputLayoutDescription = view.findViewById(R.id.TextInputLayout_description)
         editTextDescription = view.findViewById(R.id.edit_text_description)
         saveButton = view.findViewById(R.id.save_button)
     }

    interface OnEditingItem {
        fun onEditingFinished()
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val ITEM_ID = "item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"

        private const val UNDEFINED_SCREEN_MODE = ""

        fun newInstanceEditItem(id: Int) : IdeaItemFragment {
            return IdeaItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(ITEM_ID, id)
                }
            }
        }

        fun newInstanceAddItem() : IdeaItemFragment {
            return IdeaItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }
    }
}