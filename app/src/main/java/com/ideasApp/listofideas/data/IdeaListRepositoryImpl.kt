package com.ideasApp.listofideas.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.ideasApp.listofideas.domain.IdeaItem
import com.ideasApp.listofideas.domain.IdeaListRepository
import javax.inject.Inject
import kotlin.random.Random

class IdeaListRepositoryImpl @Inject constructor(application : Application): IdeaListRepository {

    private val ideaListDao = AppDatabase.getInstance(application).IdeaListDao()

    override suspend fun addIdeaItem(ideaItem: IdeaItem) {
        ideaListDao.addIdeaItem(IdeaListMapper.mapEntityToDbModel(ideaItem))
    }

    override suspend fun deleteIdeaItem(ideaItem: IdeaItem) {
        ideaListDao.deleteIdeaItem(ideaItem.id)
    }

    override suspend fun editIdeaItem(ideaItem: IdeaItem) {
        addIdeaItem(ideaItem)
    }

    override suspend fun getIdeaItem(ideaItemId: Int): IdeaItem {
        val dbModel = ideaListDao.getIdeaItem(ideaItemId)
        return IdeaListMapper.mapDbModelToEntity(dbModel)
    }

    override fun getIdeasList(): LiveData<List<IdeaItem>> = MediatorLiveData<List<IdeaItem>>().apply {
        addSource(ideaListDao.getIdeaList()) {
            value = IdeaListMapper.mapListDbModelToListEntity(it)
        }
    }


}