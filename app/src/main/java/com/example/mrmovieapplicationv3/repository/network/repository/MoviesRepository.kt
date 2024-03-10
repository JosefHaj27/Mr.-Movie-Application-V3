package com.example.mrmovieapplicationv3.repository.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.mrmovieapplicationv3.repository.network.ApiService
import com.example.mrmovieapplicationv3.repository.network.paging.MoviesPagingSource

class MoviesRepository(val apiService: ApiService) {

    fun getMovies() = Pager(
        config = getDefaultPageConfig(),
        pagingSourceFactory = { MoviesPagingSource(apiService) }
    ).liveData

//    fun letMoviesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Show>> {
//        return Pager(
//            pagingConfig = pagingConfig,
//            pagingSourceFactory = { MoviesPagingSource(apiService) }
//        ).flow
//    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 1,
            maxSize = 100,
            enablePlaceholders = false
        )
    }
}