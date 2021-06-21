package com.android.moviekade.di

import android.content.Context
import androidx.room.Room
import com.android.moviekade.R
import com.android.moviekade.business.data.repository.*
import com.android.moviekade.business.domain.mapper.cache.*
import com.android.moviekade.business.domain.mapper.response.*
import com.android.moviekade.business.usecase.*
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val baserUrl = "https://mamtopstream.pw/"

    @Provides
    @Singleton
    fun provideMovieHouseDatabase(@ApplicationContext context: Context): MovieHouseDB =
        Room.databaseBuilder(context, MovieHouseDB::class.java, "MovieHouseDB")
            .build()

    @Provides
    @Singleton
    fun provideAnimationMovieDao(database: MovieHouseDB): AnimationDAO =
        database.animationDao()

    @Provides
    @Singleton
    fun provideNewMovieDao(database: MovieHouseDB): NewMovieDAO =
        database.newMovieDao()

   @Provides
   @Singleton
    fun provideSeriesDao(database: MovieHouseDB): SeriesDAO =
        database.seriesDao()

    @Provides
    @Singleton
    fun provideSliderDao(database: MovieHouseDB): SliderDAO =
        database.sliderDao()

    @Provides
    @Singleton
    fun provideTopMovieDao(database: MovieHouseDB): TopMovieDAO = 
        database.topMovieDao()

    @Provides
    @Singleton
    fun provideAnimationMovieRepo(
         animationDao: AnimationDAO,
         animationMovieNetwork: Api,
         animationMovieCacheMapper: AnimationMovieCacheMapper,
         animationMovieResponseMapper: AnimationMovieResponseMapper
    ): AnimationMovieRepo {
        return AnimationMovieRepo(animationDao, animationMovieNetwork, animationMovieCacheMapper, animationMovieResponseMapper)
    }

    @Provides
    @Singleton
    fun provideNewMovieRepo(
        newMovieDAO: NewMovieDAO,
        newMovieNetwork: Api,
        newMovieCacheMapper: NewMovieCacheMapper,
        newMovieResponseMapper: NewMovieResponseMapper
    ): NewMovieRepo {
        return NewMovieRepo(newMovieDAO, newMovieNetwork, newMovieCacheMapper, newMovieResponseMapper)
    }

    @Provides
    @Singleton
    fun provideSeriesRepo(
        seriesDAO: SeriesDAO,
        seriesNetwork: Api,
        seriesCacheMapper: SeriesCacheMapper,
        seriesResponseMapper: SeriesResponseMapper
    ): SeriesRepo {
        return SeriesRepo(seriesDAO, seriesNetwork, seriesCacheMapper, seriesResponseMapper)
    }

    @Provides
    @Singleton
    fun provideSliderRepo(
        sliderDAO: SliderDAO,
        sliderNetwork: Api,
        sliderCacheMapper: SliderCacheMapper,
        sliderResponseMapper: SliderResponseMapper
    ): SliderRepo {
        return SliderRepo(sliderDAO, sliderNetwork, sliderCacheMapper, sliderResponseMapper)
    }

    @Provides
    @Singleton
    fun provideTopMovieRepo(
        topMovieDAO: TopMovieDAO,
        topMovieNetwork: Api,
        topMovieCacheMapper: TopMovieCacheMapper,
        topMovieResponseMapper: TopMovieResponseMapper
    ): TopMovieRepo {
        return TopMovieRepo(topMovieDAO, topMovieNetwork, topMovieCacheMapper, topMovieResponseMapper)
    }

    @Provides
    @Singleton
    fun provideAnimationMovieUseCase(
        animationMovieRepo: AnimationMovieRepo
    ): AnimationMovieUseCase {
        return AnimationMovieUseCase(animationMovieRepo)
    }

    @Provides
    @Singleton
    fun provideNewMovieUseCase(
        newMovieRepo: NewMovieRepo
    ): NewMovieUseCase {
        return NewMovieUseCase(newMovieRepo)
    }

    @Provides
    @Singleton
    fun provideSeriesUseCase(
        seriesRepo: SeriesRepo
    ): SeriesUseCase {
        return SeriesUseCase(seriesRepo)
    }

    @Provides
    @Singleton
    fun provideSliderUseCase(
        sliderRepo: SliderRepo
    ): SliderUseCase {
        return SliderUseCase(sliderRepo)
    }

    @Provides
    @Singleton
    fun provideTopMovieUseCase(
        topMovieRepo: TopMovieRepo
    ): TopMovieUseCase {
        return TopMovieUseCase(topMovieRepo)
    }

    @Provides
    @Singleton
    fun provideGlideInstance(@ApplicationContext context: Context): RequestManager =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.drawable.ic_popcorn)
        )

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.callTimeout(40, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }


    @Provides
    @Singleton
    fun provideMovieHouseApi(okHttpClient: OkHttpClient): Api =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(baserUrl)
            .client(okHttpClient)
            .build()
            .create(Api::class.java)
}