package com.kuluruvineeth.composeinstagram.domain.use_cases.AuthenticationUseCases

import com.kuluruvineeth.composeinstagram.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseAuthState @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() = repository.getFirebaseAuthState()
}