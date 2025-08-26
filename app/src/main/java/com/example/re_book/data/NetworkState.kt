package com.example.re_book.data

sealed class NetworkState<out T>(data:T?=null) {
     object Loading : NetworkState<Nothing>()
   data  class Success<out T>(val dataValue: T) : NetworkState<T>(dataValue)
     data class Error<T>(val message: String? = null) : NetworkState<T>()
}


//  Screen States

data class GetAllBookScreenState(
     val isLoading : Boolean = false,
     val model : List<Model> = emptyList(),
     val error : String?=null
)

