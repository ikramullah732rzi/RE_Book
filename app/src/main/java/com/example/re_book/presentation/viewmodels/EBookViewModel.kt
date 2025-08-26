package com.example.re_book.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.re_book.data.GetAllBookScreenState
import com.example.re_book.data.NetworkState
import com.example.re_book.domain.useCases.GetAllBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EBookViewModel @Inject constructor(private val getAllBooksUseCase: GetAllBooksUseCase) :
    ViewModel() {

    private val _getAllBookState = MutableStateFlow(GetAllBookScreenState())
    val getAllBookState = _getAllBookState.asStateFlow()


    fun getAllBook() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllBooksUseCase.getAllBooks().collect() {
                when (it) {
                    NetworkState.Loading -> {
                        _getAllBookState.value = _getAllBookState.value.copy(
                            isLoading = true,
                            model = emptyList(),
                            error = null
                        )
                    }

                    is NetworkState.Success -> {
                        _getAllBookState.value = _getAllBookState.value.copy(
                            isLoading = false,
                            model = it.dataValue,
                            error = null
                        )
                    }

                    is NetworkState.Error<*> -> {
                        _getAllBookState.value = _getAllBookState.value.copy(
                            isLoading = false,
                            model = emptyList(),
                            error = it.message
                        )
                    }
                }
            }
        }
    }
}

