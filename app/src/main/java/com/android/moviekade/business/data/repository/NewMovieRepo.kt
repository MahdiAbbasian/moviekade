package com.android.moviekade.business.data.repository

import com.android.moviekade.business.domain.entity.NewMovie
import com.android.moviekade.business.domain.mapper.cache.NewMovieCacheMapper
import com.android.moviekade.business.domain.mapper.response.NewMovieResponseMapper
import com.android.moviekade.presentation.database.DataState
import com.android.moviekade.service.datasource.dao.NewMovieDAO
import com.android.moviekade.service.datasource.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewMovieRepo @Inject constructor(
    private val newMovieDAO: NewMovieDAO,
    private val newMovieNetwork: Api,
    private val newMovieCacheMapper: NewMovieCacheMapper,
    private val newMovieResponseMapper: NewMovieResponseMapper
) {

    suspend fun getAllItems(): Flow<DataState<List<NewMovie>>> = flow {
        emit(DataState.Loading)
        try {
            val networkNewMovieResponse = newMovieNetwork.getNewMovie("new_movie")
            val newMovies = newMovieResponseMapper.mapFromList(networkNewMovieResponse)
            for (newMovie in newMovies) {
                newMovieDAO.insertNewMovieObject(newMovieCacheMapper.mapTo(newMovie))
            }
            val cachedNewMovie = newMovieDAO.getAllNewMovie()
            emit(DataState.Success(newMovieCacheMapper.mapFromList(cachedNewMovie)))
        }catch (e: Exception) {
            emit(DataState.Error(e.message))
        }
    }

}