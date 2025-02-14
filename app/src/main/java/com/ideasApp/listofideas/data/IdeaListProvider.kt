package com.ideasApp.listofideas.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.ideasApp.listofideas.domain.IdeaItem
import com.ideasApp.listofideas.presentation.IdeaApplication
import javax.inject.Inject

class IdeaListProvider: ContentProvider() {

    @Inject
    lateinit var ideaListDao: IdeaListDao
    @Inject
    lateinit var mapper: IdeaListMapper

    private val component by lazy {
        (context as IdeaApplication).component
    }

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("com.ideasApp.listofideas", "IdeaItems", IDEA_LIST_QUERY)
        addURI("com.ideasApp.listofideas", "IdeaItems/#", IDEA_ITEM_BY_ID_QUERY)
    }

    override fun onCreate():Boolean {
        component.inject(this)
        return true
    }

    override fun query(
        uri:Uri,
        projection:Array<out String>?,
        selection:String?,
        selectionArgs:Array<out String>?,
        sortOrder:String?
    ):Cursor? {
        val code = uriMatcher.match(uri)
        return when(code) {
            IDEA_LIST_QUERY -> {
                Log.d("IdeaListProvider", "IdeaListQuery")
                ideaListDao.getIdeaListCursor()
            }
            else -> null
        }
    }

    override fun getType(uri:Uri):String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri:Uri,values:ContentValues?):Uri? {
        when(uriMatcher.match(uri)) {
            IDEA_LIST_QUERY-> {
                if(values == null) return null
                val ideaName = values.getAsString("ideaName")
                val description = values.getAsString("description")
                val isEnabled = values.getAsBoolean("isEnabled")
                val id = values.getAsInteger("id")
                val ideaItem = IdeaItem(ideaName, description, isEnabled, id)
                val dbModel = mapper.mapEntityToDbModel(ideaItem)
                ideaListDao.addIdeaItemSync(dbModel)
            }
        }
        return null
    }

    override fun delete(uri:Uri,selection:String?,selectionArgs:Array<out String>?):Int {
        when(uriMatcher.match(uri)) {
            IDEA_LIST_QUERY-> {
                val id = selectionArgs?.get(0)?.toInt() ?: -1
                return ideaListDao.deleteIdeaItemSync(id)
            }
        }
        return 0
    }

    override fun update(uri:Uri,values:ContentValues?,selection:String?,selectionArgs:Array<out String>?):Int {
        TODO("Not yet implemented")
    }

    companion object {
        const val IDEA_LIST_QUERY = 100
        const val IDEA_ITEM_BY_ID_QUERY = 101
    }
}