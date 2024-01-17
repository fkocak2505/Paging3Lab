package com.fkocak.paging3lab.domain.repository

import androidx.paging.PagingData
import com.fkocak.paging3lab.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    fun getDataFromApi(page: Int, pageSize: Int): Flow<PagingData<Movie>>

}