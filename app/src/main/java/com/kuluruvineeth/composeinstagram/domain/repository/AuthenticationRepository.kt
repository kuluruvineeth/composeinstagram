package com.kuluruvineeth.composeinstagram.domain.repository

import com.kuluruvineeth.composeinstagram.util.Response
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    fun isUserAuthenticatedFirebase() : Boolean

    fun getFirebaseAuthState(): Flow<Boolean>

    fun firebaseSignIn(email: String,password: String) : Flow<Response<Boolean>>

    fun firebaseSignOut(): Flow<Response<Boolean>>

    fun firebaseSignUp(email: String,password: String,userName: String): Flow<Response<Boolean>>
}