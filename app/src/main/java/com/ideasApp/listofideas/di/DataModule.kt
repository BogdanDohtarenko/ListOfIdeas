package com.ideasApp.listofideas.di

import android.app.Application
import com.ideasApp.listofideas.data.AppDatabase
import com.ideasApp.listofideas.data.IdeaListDao
import com.ideasApp.listofideas.data.IdeaListRepositoryImpl
import com.ideasApp.listofideas.domain.IdeaListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    companion object {
        @Provides
        fun provideIdeaListDao(application: Application):IdeaListDao {
            return AppDatabase.getInstance(application).IdeaListDao()
        }
    }
}