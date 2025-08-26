package com.example.re_book.domain

import com.example.re_book.data.Model
import com.example.re_book.data.NetworkState
import kotlinx.coroutines.flow.Flow

interface Repo {
    suspend fun getAllBooks(): Flow<NetworkState<List<Model>>>
}