package com.kuluruvineeth.composeinstagram.domain.use_cases.PostsUseCases

import com.kuluruvineeth.composeinstagram.domain.repository.PostRepository
import javax.inject.Inject

class GetAllPosts @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(userid:String)=repository.getAllPosts(userid = userid)
}