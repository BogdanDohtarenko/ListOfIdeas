package com.ideasApp.listofideas.domain

data class IdeaItem(
    val id: Int,
    var ideaName: String,
    var count: Int,
    var isEnabled: Boolean

)
