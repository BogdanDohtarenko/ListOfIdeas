package com.ideasApp.listofideas.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ideasApp.listofideas.R

class IdeaItemActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle? , persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState , persistentState)
        setContentView(R.layout.activity_idea_item)
    }
}