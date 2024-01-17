package com.fkocak.paging3lab.data.datasource.remote

import com.fkocak.paging3lab.core.network.ApiService
import com.fkocak.paging3lab.data.model.ResponseDto
import javax.inject.Inject

class ApiRemoteDataSourceImpl @Inject constructor(
    private val api: ApiService
) : ApiRemoteDataSource {

    override suspend fun getData(apiKey: String, pageNumber: Int) =
        api.getData(apiKey = apiKey, pageNumber = pageNumber)

}