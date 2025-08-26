package com.example.re_book.domain

import com.example.re_book.data.RepoImp
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Singleton
    @Provides
    fun getRepoImpl(firebaseDatabase: FirebaseDatabase):Repo{
        return RepoImp(firebaseDatabase)
    }
}