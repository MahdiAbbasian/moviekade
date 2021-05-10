package com.android.demomvvm.model.dao

import androidx.room.*
import com.android.demomvvm.model.local.LocalTopMovie

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