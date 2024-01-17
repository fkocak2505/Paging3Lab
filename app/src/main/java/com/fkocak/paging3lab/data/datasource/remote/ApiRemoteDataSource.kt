package com.fkocak.paging3lab.data.datasource.remote

import com.fkocak.paging3lab.data.model.ResponseDto

interface ApiRemoteDataSource {

    suspend fun getData(
        apiKey: String,
        pageNumber: Int
    ): ResponseDto

}