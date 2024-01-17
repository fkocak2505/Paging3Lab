package com.fkocak.paging3lab.core.flowextension

import com.fkocak.paging3lab.core.wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

fun <T> Flow<ResultWrapper<T>>.onSuccess(action: suspend (T) -> Unit) = onEach {
    if (it is ResultWrapper.Success) {
        action(it.data)
    }
}