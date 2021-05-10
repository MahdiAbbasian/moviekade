package com.android.demomvvm.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.demomvvm.model.dao.*
import com.android.demomvvm.model.entity.*

@Database(entities = [AnimationMovie::class, NewMovie::class, Series::class, Slider::class, TopMovie::class], version = 1)
abstract class MovieKadeDB: RoomDatabase() {

    abstract fun animationDao(): AnimationDAO

    abstract fun newMovieDao(): NewMovieDAO

    abstract fun seriesDao(): SeriesDAO

    abstract fun sliderDao(): SliderDAO

    abstract fun topMovieDao(): TopMovieDAO

    companion object {
        private var INSTANCE: MovieKadeDB? = null
        fun getMovieKadeDB(context: Context): MovieKadeDB? {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MovieKadeDB::class.java,
                    "MovieKadeDB"
                ).build()
            }
            return INSTANCE
        }

        fun destroyMovieKadeDB() {
            INSTANCE = null
        }
    }

}