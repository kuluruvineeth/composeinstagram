package com.kuluruvineeth.composeinstagram.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.kuluruvineeth.composeinstagram.data.AuthenticationRepositoryImpl
import com.kuluruvineeth.composeinstagram.domain.repository.AuthenticationRepository
import com.kuluruvineeth.composeinstagram.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InstagramModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth: FirebaseAuth,firestore: FirebaseFirestore):AuthenticationRepository{
        return AuthenticationRepositoryImpl(auth,firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepository)=AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository),
        firebaseAuthState = FirebaseAuthState(repository),
        firebaseSignIn = FirebaseSignIn(repository),
        firebaseSignOut = FirebaseSignOut(repository),
        firebaseSignUp = FirebaseSignUp(repository)
    )
}