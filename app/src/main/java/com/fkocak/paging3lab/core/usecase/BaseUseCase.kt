package com.fkocak.paging3lab.core.usecase

import com.fkocak.paging3lab.core.wrapper.ResultWrapper
import com.fkocak.paging3lab.core.wrapper.asBase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

abstract class BaseUseCase<in P : BaseUseCase.Params, R>(
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(params: P) = execute(params).map {
        ResultWrapper.Success(it).asBase()
    }.onStart {
        emit(ResultWrapper.Loading)
    }.catch {
        emit(ResultWrapper.Error(it))
    }.onCompletion {
        emit(ResultWrapper.Finish)
    }.flowOn(dispatcher)

    protected abstract fun execute(params: P): Flow<R>

    open class Params
}