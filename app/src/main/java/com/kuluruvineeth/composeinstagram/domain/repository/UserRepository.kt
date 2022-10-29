package com.kuluruvineeth.composeinstagram.domain.repository

import com.kuluruvineeth.composeinstagram.domain.model.User
import com.kuluruvineeth.composeinstagram.util.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserDetails(userid:String): Flow<Response<User>>
    fun setUserDetails(
        userid:String,
        name:String,
        userName:String,
        bio:String,
        websiteUrl:String
    ):Flow<Response<Boolean>>
}