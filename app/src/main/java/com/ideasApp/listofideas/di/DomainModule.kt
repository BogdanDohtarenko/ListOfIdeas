package com.ideasApp.listofideas.di

import com.ideasApp.listofideas.data.IdeaListRepositoryImpl
import com.ideasApp.listofideas.domain.IdeaListRepository
import dagger.Binds
import dagger.MapKey
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindRepository(impl: IdeaListRepositoryImpl): IdeaListRepository
}