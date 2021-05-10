package com.android.demomvvm.model.dao

import androidx.room.*
import com.android.demomvvm.model.local.LocalNewMovie

interface NewMovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewMovieObject(newMovie: LocalNewMovie): Long

    @Query("SELECT * FROM CAT_NEW_MOVIE")
    suspend fun getAllNewMovie(): List<LocalNewMovie>

    @Delete
    suspend fun deleteNewMovieObject(newMovie: List<LocalNewMovie>)

    @Update
    suspend fun updateNewMovie(newMovie: List<LocalNewMovie>)

    @Query("DELETE FROM CAT_NEW_MOVIE")
    suspend fun clearNewMovie()
}