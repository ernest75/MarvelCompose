package com.example.marvelcompose.data.network

//import com.example.marvelcompose.BuildConfig
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.Response
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.create
//import java.util.*
//
//const val API_ENDPOINT = "https://gateway.marvel.com/"
//
//object ApiClient {
//
//    private val loggingInterceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//
//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor)
//        .addInterceptor(QueryInterceptor())
//        .build()
//
//    private val restAdapter = Retrofit.Builder()
//        .baseUrl(API_ENDPOINT)
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(okHttpClient)
//        .build()
//
//    val charactersService: CharactersService = restAdapter.create(CharactersService::class.java)
//    val comicsService: ComicsService = restAdapter.create(ComicsService::class.java)
//    val eventsService: EventsService = restAdapter.create(EventsService::class.java)
//    val creatorsService: CreatorsService = restAdapter.create(CreatorsService::class.java)
//}