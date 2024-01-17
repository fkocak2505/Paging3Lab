package com.fkocak.paging3lab.core.network

import com.fkocak.paging3lab.data.model.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val SERVER_URL = "YOUR_BASE_URL"
        const val API_URL = "$SERVER_URL/YOUR_PATH_HERE/"
    }

    @GET("popular")
    suspend fun getData(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): ResponseDto

}