package com.ideasApp.listofideas.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ideasApp.listofideas.R

class IdeaItemActivity: AppCompatActivity() {

    private lateinit var inputLayoutName: TextInputLayout
    private lateinit var editTextName: TextInputEditText
    private lateinit var inputLayoutDescription: TextInputLayout
    private lateinit var editTextDescription: TextInputEditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idea_item)
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE) //nullable method
        Log.d("IdeaItemActivity", mode.toString())
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