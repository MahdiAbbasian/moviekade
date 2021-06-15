package com.android.moviekade.di

import android.content.Context
import androidx.room.Room
import com.android.moviekade.R
import com.android.moviekade.business.data.repository.AnimationMovieRepo
import com.android.moviekade.business.data.repository.NewMovieRepo
import com.android.moviekade.business.data.repository.SeriesRepo
import com.android.moviekade.business.domain.mapper.cache.AnimationMovieCacheMapper
import com.android.moviekade.business.domain.mapper.cache.NewMovieCacheMapper
import com.android.moviekade.business.domain.mapper.cache.SeriesCacheMapper
import com.android.moviekade.business.domain.mapper.response.AnimationMovieResponseMapper
import com.android.moviekade.business.domain.mapper.response.NewMovieResponseMapper
import com.android.moviekade.business.domain.mapper.response.SeriesResponseMapper
import com.android.moviekade.business.usecase.AnimationMovieUseCase
import com.android.moviekade.business.usecase.NewMovieUseCase
import com.android.moviekade.business.usecase.SeriesUseCase
import com.android.moviekade.service.datasource.dao.*
import com.android.moviekade.presentation.database.MovieHouseDB
import com.android.moviekade.service.datasource.network.Api
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val baserUrl = "https://mamtopstream.pw/"

    @Singleton
    @Provides
    fun provideMovieHouseDatabase(@ApplicationContext context: Context): MovieHouseDB =
        Room.databaseBuilder(context, MovieHouseDB::class.java, "MovieHouseDB")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideAnimationMovieDao(database: MovieHouseDB): AnimationDAO =
        database.animationDao()

    @Singleton
    @Provides
    fun provideNewMovieDao(database: MovieHouseDB): NewMovieDAO =
        database.newMovieDao()

   @Singleton
   @Provides
    fun provideSeriesDao(database: MovieHouseDB): SeriesDAO =
        database.seriesDao()

    @Singleton
    @Provides
    fun provideSliderDao(database: MovieHouseDB): SliderDAO =
        database.sliderDao()
    
    @Singleton
    @Provides
    fun provideTopMovieDao(database: MovieHouseDB): TopMovieDAO = 
        database.topMovieDao()

    @Singleton
    @Provides
    fun provideAnimationMovieRepo(
         animationDao: AnimationDAO,
         animationMovieNetwork: Api,
         animationMovieCacheMapper: AnimationMovieCacheMapper,
         animationMovieResponseMapper: AnimationMovieResponseMapper
    ): AnimationMovieRepo {
        return AnimationMovieRepo(animationDao, animationMovieNetwork, animationMovieCacheMapper, animationMovieResponseMapper)
    }

    @Singleton
    @Provides
    fun provideNewMovieRepo(
        newMovieDAO: NewMovieDAO,
        newMovieNetwork: Api,
        newMovieCacheMapper: NewMovieCacheMapper,
        newMovieResponseMapper: NewMovieResponseMapper
    ): NewMovieRepo {
        return NewMovieRepo(newMovieDAO, newMovieNetwork, newMovieCacheMapper, newMovieResponseMapper)
    }

    @Singleton
    @Provides
    fun provideSeriesRepo(
        seriesDAO: SeriesDAO,
        seriesNetwork: Api,
        seriesCacheMapper: SeriesCacheMapper,
        seriesResponseMapper: SeriesResponseMapper
    ): SeriesRepo {
        return SeriesRepo(seriesDAO, seriesNetwork, seriesCacheMapper, seriesResponseMapper)
    }

    @Singleton
    @Provides
    fun provideAnimationMovieUseCase(
        animationMovieRepo: AnimationMovieRepo
    ): AnimationMovieUseCase {
        return AnimationMovieUseCase(animationMovieRepo)
    }

    @Singleton
    @Provides
    fun provideNewMovieUseCase(
        newMovieRepo: NewMovieRepo
    ): NewMovieUseCase {
        return NewMovieUseCase(newMovieRepo)
    }

    @Singleton
    @Provides
    fun provideSeriesUseCase(
        seriesRepo: SeriesRepo
    ): SeriesUseCase {
        return SeriesUseCase(seriesRepo)
    }
    
    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext context: Context): RequestManager =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.drawable.ic_popcorn)
        )

    @Singleton
    @Provides
    fun provideMovieHouseApi(): Api =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(baserUrl)
            .build()
            .create(Api::class.java)
}