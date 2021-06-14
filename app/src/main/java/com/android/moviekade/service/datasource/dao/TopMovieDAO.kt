package com.android.moviekade.service.datasource.dao

import androidx.room.*
import com.android.moviekade.business.data.cache.LocalTopMovie

@Dao
interface TopMovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopMovieObject(topMovie: LocalTopMovie): Long

    @Query("SELECT * FROM CAT_TOP_MOVIE")
    suspend fun getAllTopMovie(): List<LocalTopMovie>

    @Delete
    suspend fun deleteSliderObject(topMovie: List<LocalTopMovie>)

    @Update
    suspend fun updateTopMovie(topMovie: List<LocalTopMovie>)

    @Query("DELETE FROM CAT_TOP_MOVIE")
    suspend fun clearTopMovie()
}