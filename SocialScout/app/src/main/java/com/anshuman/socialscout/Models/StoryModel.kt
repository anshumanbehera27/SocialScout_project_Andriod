package com.anshuman.socialscout.Models

data class StoryModel(
    val type: Int,
    var uid: String,
    var sid: String?,
    var name: String?,
    var image: Int
) {
    constructor(type: Int, uid: String, image: Int) : this(type, uid, null, null, image)
}
