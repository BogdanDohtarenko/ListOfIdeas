package com.ideasApp.listofideas.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.ideasApp.listofideas.domain.IdeaItem
import com.ideasApp.listofideas.domain.IdeaListRepository
import kotlin.random.Random

class IdeaListRepositoryImpl(application : Application): IdeaListRepository {

    private val ideaListDao = AppDatabase.getInstance(application).IdeaListDao()

    init {
        for (i in 0 until 100) {
            val item = IdeaItem("Name $i", "$i", Random.nextBoolean())
            addIdeaItem(item)
        }
    }

    override fun addIdeaItem(ideaItem: IdeaItem) {
        ideaListDao.addIdeaItem(IdeaListMapper.mapEntityToDbModel(ideaItem))
    }

    override fun deleteIdeaItem(ideaItem: IdeaItem) {
        ideaListDao.deleteIdeaItem(ideaItem.id)
    }

    override fun editIdeaItem(ideaItem: IdeaItem) {
        addIdeaItem(ideaItem)
    }

    override fun getIdeaItem(ideaItemId: Int): IdeaItem {
        val dbModel = ideaListDao.getIdeaItem(ideaItemId)
        return IdeaListMapper.mapDbModelToEntity(dbModel)
    }

    override fun getIdeasList(): LiveData<List<IdeaItem>> {

    }


}