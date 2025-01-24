package com.ideasApp.listofideas.data

import com.ideasApp.listofideas.domain.IdeaItem

object IdeaListMapper {
    fun mapEntityToDbModel(ideaItem : IdeaItem): IdeaItemDbModel {
        return IdeaItemDbModel(
            id = ideaItem.id,
            ideaName = ideaItem.ideaName,
            description = ideaItem.description,
            isEnabled = ideaItem.isEnabled,
        )
    }
    fun mapDbModelToEntity(ideaItemDbModel : IdeaItemDbModel): IdeaItem {
        return IdeaItem(
            id = ideaItemDbModel.id,
            ideaName = ideaItemDbModel.ideaName,
            description = ideaItemDbModel.description,
            isEnabled = ideaItemDbModel.isEnabled,
        )
    }
    fun mapListDbModelToListEntity(list: List<IdeaItemDbModel>) =  list.map {
        mapDbModelToEntity(it)
    }
}