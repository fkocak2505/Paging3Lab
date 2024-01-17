package com.fkocak.paging3lab.core.wrapper

sealed class ResultWrapper<out T> {
    data object Loading : ResultWrapper<Nothing>()
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(val throwable: Throwable) : ResultWrapper<Nothing>()
    data object Finish : ResultWrapper<Nothing>()
}

fun <T> ResultWrapper.Success<T>.asBase() = this as ResultWrapper<T>