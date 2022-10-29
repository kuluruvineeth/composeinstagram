package com.kuluruvineeth.composeinstagram.data

import com.google.firebase.firestore.FirebaseFirestore
import com.kuluruvineeth.composeinstagram.domain.model.User
import com.kuluruvineeth.composeinstagram.domain.repository.UserRepository
import com.kuluruvineeth.composeinstagram.util.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository{

    private var operationSuccessful = false
    override fun getUserDetails(userid: String): Flow<Response<User>> = callbackFlow {
        Response.Loading
        val snapShotListener = firebaseFirestore.collection("users")
            .document(userid)
            .addSnapshotListener{snapshot,error ->
                val response = if(snapshot!=null){
                    val userInfo = snapshot.toObject(User::class.java)
                    Response.Success<User>(userInfo!!)
                }else{
                    Response.Error(error?.message?:error.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose{
            snapShotListener.remove()
        }
    }

    override fun setUserDetails(
        userid: String,
        name: String,
        userName: String,
        bio: String,
        websiteUrl: String
    ) :Flow<Response<Boolean>> = flow{
        operationSuccessful = false
        try {
            val userObj = mutableMapOf<String,String>()
            userObj["name"] = name
            userObj["userName"] = userName
            userObj["bio"] = bio
            userObj["websiteurl"] = websiteUrl
            firebaseFirestore.collection("users").document(userid).update(userObj as Map<String,Any>)
                .addOnSuccessListener {
                    operationSuccessful=true
                }.await()
            if(operationSuccessful){
                emit(Response.Success(operationSuccessful))
            }
            else{
                emit(Response.Error("Edit Does not succeed"))
            }

        }
        catch (e:Exception){
            Response.Error(e.localizedMessage?:"An Unexcepted error")
        }
    }
}