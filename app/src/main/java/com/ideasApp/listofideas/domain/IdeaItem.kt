package com.ideasApp.listofideas.domain

data class IdeaItem(

    var ideaName: String ,
    var description: String ,
    var isEnabled: Boolean ,
    var id: Int = UNDEFINED_ID

) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
