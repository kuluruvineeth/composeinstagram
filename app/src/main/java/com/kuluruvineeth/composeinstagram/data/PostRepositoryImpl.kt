package com.kuluruvineeth.composeinstagram.data

import com.google.firebase.firestore.FirebaseFirestore
import com.kuluruvineeth.composeinstagram.domain.model.Post
import com.kuluruvineeth.composeinstagram.domain.repository.PostRepository
import com.kuluruvineeth.composeinstagram.util.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : PostRepository{

    private var operationSuccessful: Boolean = false
    override fun getAllPosts(userid: String): Flow<Response<List<Post>>> = callbackFlow{
        Response.Loading
        val snapshotListener = firebaseFirestore.collection("posts")
            .whereNotEqualTo("userId",userid)
            .addSnapshotListener { snapshot, e ->
                val response = if(snapshot!=null){
                    val postsList = snapshot.toObjects(Post::class.java)
                    Response.Success<List<Post>>(postsList)
                }
                else{
                    Response.Error(e?.message?:e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun uploadPost(
        postImage: String,
        postDescription: String,
        time: Long,
        userid: String,
        userName: String,
        userImage: String
    ): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            val postId = firebaseFirestore.collection("posts").document().id
            val post = Post(
                postImage = postImage,
                postDescription = postDescription,
                postId = postId,
                time = time,
                userName = userName,
                userImage = userImage,
                userId = userid
            )
            firebaseFirestore.collection("posts").document(postId).set(post)
                .addOnSuccessListener {
                    operationSuccessful = true
                }.await()
            if(operationSuccessful){
                emit(Response.Success(operationSuccessful))
            }
            else{
                emit(Response.Error("Post Upload Unsuccessful"))
            }
        }
        catch (e:Exception){
            emit(Response.Error(e.localizedMessage?:"An unexcepted error"))
        }

    }
}