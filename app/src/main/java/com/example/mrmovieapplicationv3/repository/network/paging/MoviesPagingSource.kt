package com.example.mrmovieapplicationv3.repository.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mrmovieapplicationv3.model.data.Show
import com.example.mrmovieapplicationv3.repository.network.ApiService
import retrofit2.HttpException
import java.io.IOException

// This class will call the API and get the result.
class MoviesPagingSource(private val apiService: ApiService) : PagingSource<Int, Show>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
//        The LoadParams object keeps information related to the load operation.
        return try {
            val page = params.key ?: 1
            val response = apiService.getShows(page)
            val prevKey = if (page == 1) null else page - 1 // null indicate the first page
//            val nextKey =
//                if (response.isEmpty()) null else page + 1 // null indicate we have reach the last page.

            return LoadResult.Page(
                data = emptyList(),
                prevKey = prevKey,
                nextKey = null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }

    //     The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}