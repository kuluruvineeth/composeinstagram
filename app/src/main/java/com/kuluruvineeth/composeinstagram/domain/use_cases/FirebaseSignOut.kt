package com.kuluruvineeth.composeinstagram.domain.use_cases

import com.kuluruvineeth.composeinstagram.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignOut @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() = repository.firebaseSignOut()
}