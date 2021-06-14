package com.android.moviekade.business.usecase

import com.android.moviekade.business.data.repository.AnimationMovieRepo
import com.android.moviekade.business.domain.entity.AnimationMovie
import com.android.moviekade.presentation.database.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimationMovieUseCase @Inject constructor(private val animationMovieRepo: AnimationMovieRepo) {
    suspend fun invokeAnimationMovie(): Flow<DataState<List<AnimationMovie>>> {
        return animationMovieRepo.getAllItems()
    }
}