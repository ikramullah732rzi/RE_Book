package com.example.re_book.domain.useCases

import com.example.re_book.data.Model
import com.example.re_book.data.NetworkState
import com.example.re_book.domain.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(private val repo: Repo){
    suspend fun getAllBooks(): Flow<NetworkState<List<Model>>>{
        return repo.getAllBooks()
    }
}