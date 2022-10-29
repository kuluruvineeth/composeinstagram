package com.kuluruvineeth.composeinstagram.domain.use_cases.UserUseCases

import com.kuluruvineeth.composeinstagram.domain.repository.UserRepository
import javax.inject.Inject

class SetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(
        userid:String,
        name:String,
        userName:String,
        bio:String,
        websiteUrl:String
    ) = repository.setUserDetails(
        userid,
        name,
        userName,
        bio,
        websiteUrl
    )
}