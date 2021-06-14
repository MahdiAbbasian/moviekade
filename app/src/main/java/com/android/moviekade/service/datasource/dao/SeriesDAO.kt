package com.android.moviekade.service.datasource.dao

import androidx.room.*
import com.android.moviekade.business.data.cache.LocalSeries

@Dao
interface SeriesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeriesObject(series: LocalSeries): Long

    @Query("SELECT * FROM SERIES")
    suspend fun getAllSeries(): List<LocalSeries>

    @Delete
    suspend fun deleteSeriesObject(series: List<LocalSeries>)

    @Update
    suspend fun updateSeries(series: List<LocalSeries>)

    @Query("DELETE FROM SERIES")
    suspend fun clearSeries()
}