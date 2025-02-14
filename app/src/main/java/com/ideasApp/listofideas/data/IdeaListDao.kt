package com.ideasApp.listofideas.data

import android.database.Cursor
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

    @Query("SELECT * FROM IdeaItems")
    fun getIdeaListCursor(): Cursor

    @Query("SELECT * FROM IdeaItems WHERE id = :ideaItemId LIMIT 1")
    suspend fun getIdeaItem(ideaItemId : Int): IdeaItemDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIdeaItem(ideaItemDbModel : IdeaItemDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addIdeaItemSync(ideaItemDbModel : IdeaItemDbModel)

    @Query("DELETE FROM IdeaItems WHERE id = :ideaItemId")
    suspend fun deleteIdeaItem(ideaItemId : Int)
}