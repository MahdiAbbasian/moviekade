package com.android.demomvvm.model.dao

import androidx.room.*
import com.android.demomvvm.model.local.LocalSeries
import com.android.demomvvm.model.local.LocalSlider

interface SliderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSliderObject(slider: LocalSlider): Long

    @Query("SELECT * FROM HOME_SLIDER")
    suspend fun getAllSlider(): List<LocalSlider>

    @Delete
    suspend fun deleteSliderObject(slider: List<LocalSlider>)

    @Update
    suspend fun updateSlider(slider: List<LocalSlider>)

    @Query("DELETE FROM HOME_SLIDER")
    suspend fun clearSlider()
}