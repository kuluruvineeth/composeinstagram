package com.kuluruvineeth.composeinstagram.domain.model

data class Post(
    var postId: String = "",
    var postImage: String = "",
    var postDescription: String = "",
    var time: Long? = null,
    var userId: String = "",
    var userImage: String = "",
    var userName: String = ""
)
