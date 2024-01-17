package com.fkocak.paging3lab.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fkocak.paging3lab.core.constant.Constant
import com.fkocak.paging3lab.data.datasource.remote.ApiRemoteDataSource
import com.fkocak.paging3lab.data.model.mapper.mapFromListModel
import com.fkocak.paging3lab.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

class DataPagingSource(
    private val remoteDataSource: ApiRemoteDataSource,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val data = remoteDataSource.getData(
                apiKey = Constant.API_KEY,
                pageNumber = currentPage
            )

            /**
             * Eğer BE tarafından dönen liste harici başka datalara ihtiyacınız var
             * burada bir tane callback yazıp viewModel' a kadar bu dataları da parametre
             * olarak geçebilirsiniz..
             */

            LoadResult.Page(
                data = data.results!!.mapFromListModel(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.results.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

}