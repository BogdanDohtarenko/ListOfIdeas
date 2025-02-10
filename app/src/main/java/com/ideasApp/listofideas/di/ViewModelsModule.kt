package com.ideasApp.listofideas.di

import androidx.lifecycle.ViewModel
import com.ideasApp.listofideas.presentation.IdeaItemViewModel
import com.ideasApp.listofideas.presentation.MainViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IdeaItemViewModel::class)
    fun bindIdeaItemViewModel(impl: IdeaItemViewModel): ViewModel
}