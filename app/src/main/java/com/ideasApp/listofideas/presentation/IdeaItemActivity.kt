package com.ideasApp.listofideas.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ideasApp.listofideas.R
import com.ideasApp.listofideas.domain.IdeaItem

class IdeaItemActivity: AppCompatActivity() {

    private lateinit var viewModel: IdeaItemViewModel

    private lateinit var inputLayoutName: TextInputLayout
    private lateinit var editTextName: TextInputEditText
    private lateinit var inputLayoutDescription: TextInputLayout
    private lateinit var editTextDescription: TextInputEditText
    private lateinit var saveButton: Button

    private var screenMode = UNDEFINED_SCREEN_MODE
    private var ideaItemId = IdeaItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parseIntent()

        viewModel = ViewModelProvider(this)[IdeaItemViewModel::class.java]
        setContentView(R.layout.activity_idea_item)
        initViews()
    }

    private fun parseIntent() {
        if(!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if(mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_ITEM_ID)) {
                throw RuntimeException("Param ideaItemId is absent")
            }
            ideaItemId = intent.getIntExtra(EXTRA_ITEM_ID, IdeaItem.UNDEFINED_ID)
        }
    }

    private fun initViews() {
        inputLayoutName = findViewById(R.id.TextInputLayout_name)
        editTextName = findViewById(R.id.edit_text_name)
        inputLayoutDescription = findViewById(R.id.TextInputLayout_description)
        editTextDescription = findViewById(R.id.edit_text_description)
        saveButton = findViewById(R.id.save_button)
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_ITEM_ID = "item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"

        private const val UNDEFINED_SCREEN_MODE = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, IdeaItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, ideaItemId: Int): Intent {
            val intent = Intent(context, IdeaItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_ITEM_ID, ideaItemId)
            return intent
        }
    }

}