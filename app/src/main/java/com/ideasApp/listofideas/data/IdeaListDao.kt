package com.ideasApp.listofideas.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ideasApp.listofideas.data.AppDatabase.Companion.DATABASE_NAME

@Dao
interface IdeaListDao {
    @Query("SELECT * FROM IdeaItems")
    fun getIdeaList(): LiveData<List<IdeaItemDbModel>>

    @Query("SELECT * FROM IdeaItems WHERE id = :ideaItemId LIMIT 1")
    fun getIdeaItem(ideaItemId : Int): IdeaItemDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addIdeaItem(ideaItemDbModel : IdeaItemDbModel)

    @Query("DELETE FROM IdeaItems WHERE id = :ideaItemId")
    fun deleteIdeaItem(ideaItemId : Int)
}