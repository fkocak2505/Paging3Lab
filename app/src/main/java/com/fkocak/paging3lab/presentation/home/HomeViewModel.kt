package com.fkocak.paging3lab.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.fkocak.paging3lab.core.flowextension.onSuccess
import com.fkocak.paging3lab.domain.model.Movie
import com.fkocak.paging3lab.domain.usecase.GetApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getApiUseCase: GetApiUseCase
) : ViewModel() {

    private val _dataState: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(value = PagingData.empty())
    val dataState: MutableStateFlow<PagingData<Movie>> get() = _dataState

    val page = 1 // BE' den gelecek olan page değeri
    val pagesize = 10  // BE' den gelecek olan her page' de kaç adet data göstereceğiniz data değeri

    init {
        getData()
    }

    private fun getData(){
        getApiUseCase(
            GetApiUseCase.Params(id = "", page = page, pageSize = pagesize)
        ).onSuccess {
            _dataState.value = it
        }.launchIn(viewModelScope)
    }

}