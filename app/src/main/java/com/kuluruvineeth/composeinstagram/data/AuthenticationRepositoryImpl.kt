package com.kuluruvineeth.composeinstagram.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kuluruvineeth.composeinstagram.domain.model.User
import com.kuluruvineeth.composeinstagram.domain.repository.AuthenticationRepository
import com.kuluruvineeth.composeinstagram.util.Constants
import com.kuluruvineeth.composeinstagram.util.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthenticationRepository {

    var operationSuccessful: Boolean = false

    override fun isUserAuthenticatedFirebase(): Boolean {
        return auth.currentUser!=null
    }

    override fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            emit(Response.Success(operationSuccessful))
        }
        catch (e:Exception){
            emit(Response.Error(e.localizedMessage?:"An Unexcepted error"))
        }
    }

    override fun firebaseSignOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        }
        catch (e:Exception){
            emit(Response.Error(e.localizedMessage?:"An Unexcepted error"))
        }
    }

    override fun firebaseSignUp(
        email: String,
        password: String,
        userName: String
    ): Flow<Response<Boolean>> = flow{
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            if(operationSuccessful){
                val userid = auth.currentUser?.uid!!
                val obj = User(
                    userName = userName,
                    userId = userid,
                    password = password,
                    email = email
                )
                firestore.collection(Constants.COLLECTION_NAME_USERS).document(userid).set(obj).addOnSuccessListener {

                }.await()
                emit(Response.Success(operationSuccessful))
            }
            else{
                Response.Success(operationSuccessful)
            }
        }
        catch (e: Exception){
            emit(Response.Error(e.localizedMessage?:"An Unexcepted error"))
        }
    }
}