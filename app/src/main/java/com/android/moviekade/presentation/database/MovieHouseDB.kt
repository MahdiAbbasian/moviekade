package com.android.moviekade.presentation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.moviekade.business.data.cache.*
import com.android.moviekade.service.datasource.dao.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Database(entities = [LocalAnimationMovie::class,
                     LocalNewMovie::class,
                     LocalSeries::class,
                     LocalSlider::class,
                     LocalTopMovie::class],
                     version = 1, exportSchema = false)

abstract class MovieHouseDB: RoomDatabase() {

    abstract fun animationDao(): AnimationDAO

    abstract fun newMovieDao(): NewMovieDAO

    abstract fun seriesDao(): SeriesDAO

    abstract fun sliderDao(): SliderDAO

    abstract fun topMovieDao(): TopMovieDAO
}