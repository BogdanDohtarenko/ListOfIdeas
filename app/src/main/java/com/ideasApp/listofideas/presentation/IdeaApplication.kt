package com.ideasApp.listofideas.presentation

import android.app.Application
import com.ideasApp.listofideas.di.DaggerApplicationComponent

class IdeaApplication: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}