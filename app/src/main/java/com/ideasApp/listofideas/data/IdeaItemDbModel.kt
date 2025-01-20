package com.ideasApp.listofideas.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ideasApp.listofideas.domain.IdeaItem.Companion.UNDEFINED_ID

@Entity(tableName = "IdeaItems")
data class IdeaItem(
    @PrimaryKey(autoGenerate = true)
    var ideaName: String ,
    var description: String ,
    var isEnabled: Boolean ,
    var id: Int = UNDEFINED_ID
)