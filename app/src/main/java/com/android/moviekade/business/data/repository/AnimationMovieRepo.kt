package com.android.moviekade.business.data.repository

import com.android.moviekade.service.datasource.dao.AnimationDAO
import com.android.moviekade.presentation.database.DataState
import com.android.moviekade.business.domain.entity.AnimationMovie
import com.android.moviekade.business.domain.mapper.cache.AnimationMovieCacheMapper
import com.android.moviekade.business.domain.mapper.response.AnimationMovieResponseMapper
import com.android.moviekade.service.datasource.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class AnimationMovieRepo @Inject constructor(
    private val animationDao: AnimationDAO,
    private val animationMovieNetwork: Api,
    private val animationMovieCacheMapper: AnimationMovieCacheMapper,
    private val animationMovieResponseMapper: AnimationMovieResponseMapper
    ) {

     suspend fun getAllItems(): Flow<DataState<List<AnimationMovie>>> = flow {
         emit(DataState.Loading)
         try {
             val networkAnimationMovieResponse = animationMovieNetwork.getAnimationMovie("animation_movie")
             val animationMovies = animationMovieResponseMapper.mapFromList(networkAnimationMovieResponse)
             for (animationMovie in animationMovies) {
                 animationDao.insertAnimationObject(animationMovieCacheMapper.mapTo(animationMovie))
             }
             val cachedAnimationMovie = animationDao.getAllAnimation()
             emit(DataState.Success(animationMovieCacheMapper.mapFromList(cachedAnimationMovie)))
         }catch (e: Exception) {
             emit(DataState.Error(e.message))
         }
     }
}