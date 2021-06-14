package com.android.moviekade.service.datasource.dao

import androidx.room.*
import com.android.moviekade.business.data.cache.LocalAnimationMovie

@Dao
interface AnimationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimationObject(animationMovie: LocalAnimationMovie) : Long

    @Query("SELECT * FROM CAT_ANIMATION_MOVIE")
    suspend fun getAllAnimation(): List<LocalAnimationMovie>

    @Delete
    suspend fun deleteAnimationObject(animationMovieObject: List<LocalAnimationMovie>)

    @Update
    suspend fun updateAnimationMovie(animationMovie: List<LocalAnimationMovie>)

    @Query("DELETE FROM CAT_ANIMATION_MOVIE")
    suspend fun clearAnimationMovie()
}