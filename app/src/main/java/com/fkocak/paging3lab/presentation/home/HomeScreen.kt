package com.fkocak.paging3lab.presentation.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.fkocak.paging3lab.core.ui.component.ErrorState
import com.fkocak.paging3lab.core.ui.component.Loading
import com.fkocak.paging3lab.core.ui.component.NextPageLoading
import com.fkocak.paging3lab.domain.model.Movie

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val moviePagingItems: LazyPagingItems<Movie> = viewModel.dataState.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        items(moviePagingItems.itemCount) { index ->
            ItemOfList(
                item = moviePagingItems[index]!!,
                onClick = {
                    // TODO when clicking item..
                }
            )
        }
        moviePagingItems.apply {
            handleLoadState(loadState.refresh, moviePagingItems::retry, isRefresh = true)
            handleLoadState(loadState.append, moviePagingItems::retry, isRefresh = false)
        }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
    }
}

@Composable
fun ItemOfList(
    item: Movie,
    onClick: () -> Unit
) {

}

private fun LazyListScope.handleLoadState(
    loadState: LoadState,
    retry: () -> Unit,
    isRefresh: Boolean
) {
    when (loadState) {
        is LoadState.Loading -> {
            if (isRefresh) {
                item { Loading(modifier = Modifier.fillParentMaxSize()) }
            } else {
                item { NextPageLoading(modifier = Modifier) }
            }
        }

        is LoadState.Error -> {
            item {
                ErrorState(
                    modifier = if (isRefresh) Modifier.fillParentMaxSize() else Modifier,
                    message = loadState.error.localizedMessage ?: "Unknown error",
                    onClickRetry = { retry() }
                )
            }
        }

        else -> Unit
    }
}