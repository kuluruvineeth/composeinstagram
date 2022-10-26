package com.kuluruvineeth.composeinstagram.domain.model

data class User(
    var name: String = "",
    var userName: String = "",
    var userId: String = "",
    var email: String = "",
    var password: String = "",
    var imageUrl: String = "",
    var following: List<String> = emptyList(),
    val followers: List<String> = emptyList(),
    val totalPosts: String = "",
    val bio: String = "",
    val url: String = ""
)
