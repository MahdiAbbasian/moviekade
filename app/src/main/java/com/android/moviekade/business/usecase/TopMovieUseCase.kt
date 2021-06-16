package com.android.moviekade.business.usecase

import com.android.moviekade.business.data.repository.TopMovieRepo
import com.android.moviekade.business.domain.entity.TopMovie
import com.android.moviekade.presentation.database.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopMovieUseCase @Inject constructor(private val topMovieRepo: TopMovieRepo) {
    suspend fun invokeTopMovie(): Flow<DataState<List<TopMovie>>> {
        return topMovieRepo.getAllItems()
    }
}