package com.ideasApp.listofideas.di

import android.app.Application
import com.ideasApp.listofideas.data.IdeaListProvider
import com.ideasApp.listofideas.presentation.IdeaItemFragment
import com.ideasApp.listofideas.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    DomainModule::class,
    ViewModelsModule::class,
    DataModule::class
])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: IdeaItemFragment)

    fun inject(provider: IdeaListProvider)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application:Application
        ): ApplicationComponent
    }

}