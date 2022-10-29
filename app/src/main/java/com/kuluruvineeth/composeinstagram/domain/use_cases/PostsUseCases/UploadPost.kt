package com.kuluruvineeth.composeinstagram.domain.use_cases.PostsUseCases

import com.kuluruvineeth.composeinstagram.domain.repository.PostRepository
import javax.inject.Inject

class UploadPost @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(
        userid : String,
        userName:String,
        userImage:String,
        postImage:String,
        postDescription:String,
        time:Long
    ) = repository.uploadPost(
        userid = userid,
        userName = userName,
        userImage = userImage,
        postImage = postImage,
        postDescription = postDescription,
        time = time
    )
}