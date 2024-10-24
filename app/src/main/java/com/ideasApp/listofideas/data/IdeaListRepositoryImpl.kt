package com.ideasApp.listofideas.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ideasApp.listofideas.domain.IdeaItem
import com.ideasApp.listofideas.domain.IdeaListRepository
import kotlin.random.Random

object IdeaListRepositoryImpl: IdeaListRepository {

    private val ideaList = sortedSetOf<IdeaItem>({p0, p1 -> p0.id.compareTo(p1.id) })
    private val idealistLiveData = MutableLiveData<List<IdeaItem>>()
    private var autoIncrementId = 0

    init {
        for (i in 0 until 100) {
            val item = IdeaItem("Name $i", i, Random.nextBoolean())
            addIdeaItem(item)
        }
    }

    private fun updateList() {
        idealistLiveData.value = ideaList.toList()
    }

    override fun addIdeaItem(ideaItem: IdeaItem) {
        if(ideaItem.id == IdeaItem.UNDEFINED_ID)
            ideaItem.id = autoIncrementId++

        ideaList.add(ideaItem)
        updateList()
    }

    override fun deleteIdeaItem(ideaItem: IdeaItem) {
        ideaList.remove(ideaItem)
        updateList()
    }

    override fun editIdeaItem(ideaItem: IdeaItem) {
        val oldItem = getIdeaItem(ideaItem.id)
        ideaList.remove(oldItem)
        addIdeaItem(ideaItem)
    }

    override fun getIdeaItem(ideaItemId: Int): IdeaItem {
        return ideaList.find {
            it.id == ideaItemId
        } ?: throw RuntimeException("EElement with id $ideaItemId not found")
    }

    override fun getIdeasList(): LiveData<List<IdeaItem>> {
        updateList()
        return idealistLiveData
    }


}