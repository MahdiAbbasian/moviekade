package com.android.demomvvm.repository

import com.android.demomvvm.model.data.remote.response.AnimationMovieResponse
import com.android.demomvvm.model.local.LocalAnimationMovie
import javax.inject.Inject

class AnimationMovieRepository @Inject constructor (
    private val localDataSource: LocalAnimationMovie,
    private val remoteDataSource: AnimationMovieResponse
) {

}