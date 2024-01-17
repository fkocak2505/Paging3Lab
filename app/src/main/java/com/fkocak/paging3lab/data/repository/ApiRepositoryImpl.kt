package com.fkocak.paging3lab.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.fkocak.paging3lab.data.datasource.remote.ApiRemoteDataSource
import com.fkocak.paging3lab.data.repository.paging.DataPagingSource
import com.fkocak.paging3lab.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val remoteDataSource: ApiRemoteDataSource
) : ApiRepository {

    override fun getDataFromApi(page: Int, pageSize: Int) = Pager(
        config = PagingConfig(pageSize = pageSize, prefetchDistance = 2),
        pagingSourceFactory = { DataPagingSource(remoteDataSource) }
    ).flow

}