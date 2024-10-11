package com.ideasApp.listofideas.domain

data class IdeaItem(

    var ideaName: String,
    var count: Int,
    var isEnabled: Boolean,
    var id: Int = UNDEFINED_ID

) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
