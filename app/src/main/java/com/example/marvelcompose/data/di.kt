package com.example.marvelcompose.data

import com.example.marvelcompose.data.network.CharactersService
import com.example.marvelcompose.data.network.ComicsService
import com.example.marvelcompose.data.network.CreatorsService
import com.example.marvelcompose.data.network.EventsService
import com.example.marvelcompose.data.network.QueryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @ApiEndPoint
    fun provideApiEndPoint(): String = "https://gateway.marvel.com/"


    @Provides
    fun providesLoginInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, queryInterceptor: QueryInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(queryInterceptor)
            .build()

    @Provides
    fun provideRestAdapter(@ApiEndPoint apiEndPoint: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(apiEndPoint)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideCharactersService(restAdapter: Retrofit) : CharactersService = restAdapter.create()

    @Provides
    fun provideComicsService(restAdapter: Retrofit) : ComicsService = restAdapter.create()

    @Provides
    fun provideEventsService(restAdapter: Retrofit) : EventsService = restAdapter.create()

    @Provides
    fun provideCreatorsService(restAdapter: Retrofit) : CreatorsService = restAdapter.create()

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndPoint