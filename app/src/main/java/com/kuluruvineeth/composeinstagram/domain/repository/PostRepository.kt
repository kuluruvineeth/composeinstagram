package com.kuluruvineeth.composeinstagram.domain.repository

import com.kuluruvineeth.composeinstagram.domain.model.Post
import com.kuluruvineeth.composeinstagram.util.Response
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getAllPosts(userid : String): Flow<Response<List<Post>>>

    fun uploadPost(
        postImage:String,
        postDescription: String,
        time: Long,
        userid: String,
        userName: String,
        userImage: String
    ) : Flow<Response<Boolean>>
}