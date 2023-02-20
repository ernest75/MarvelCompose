package com.example.marvelcompose.data.network

import com.example.marvelcompose.data.network.entities.ApiCreator
import com.example.marvelcompose.data.network.entities.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CreatorsService {
    @GET("/v1/public/creators")
    suspend fun getCreators(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<ApiCreator>

    @GET("/v1/public/comics/{creatorId}")
    suspend fun findCreator(
        @Path("comicId") comicId: Int,
    ): ApiResponse<ApiCreator>
}