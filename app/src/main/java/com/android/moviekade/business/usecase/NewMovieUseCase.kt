package com.android.moviekade.business.usecase

import com.android.moviekade.business.data.repository.NewMovieRepo
import com.android.moviekade.business.domain.entity.NewMovie
import com.android.moviekade.presentation.database.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewMovieUseCase @Inject constructor(private val newMovieRepo: NewMovieRepo) {
    suspend fun invokeNewMovie(): Flow<DataState<List<NewMovie>>> {
        return newMovieRepo.getAllItems()
    }
}