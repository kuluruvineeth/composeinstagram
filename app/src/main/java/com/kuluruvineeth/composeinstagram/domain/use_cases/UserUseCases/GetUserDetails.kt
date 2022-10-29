package com.kuluruvineeth.composeinstagram.domain.use_cases.UserUseCases

import com.kuluruvineeth.composeinstagram.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userid:String) = repository.getUserDetails(userid)
}