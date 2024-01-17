package com.fkocak.paging3lab.domain.usecase

import androidx.paging.PagingData
import com.fkocak.paging3lab.core.network.annotation.IoDispatcher
import com.fkocak.paging3lab.core.usecase.BaseUseCase
import com.fkocak.paging3lab.domain.model.Movie
import com.fkocak.paging3lab.domain.repository.ApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetApiUseCase @Inject constructor(
    private val repository: ApiRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : BaseUseCase<GetApiUseCase.Params, PagingData<Movie>>(dispatcher = dispatcher) {
    override fun execute(params: Params) =
        repository.getDataFromApi(page = params.page, pageSize = params.pageSize)

    data class Params(
        val id: String,
        val page: Int,
        val pageSize: Int
    ) : BaseUseCase.Params()

}