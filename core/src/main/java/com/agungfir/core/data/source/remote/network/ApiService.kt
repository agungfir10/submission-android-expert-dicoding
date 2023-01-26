package com.agungfir.core.data.source.remote.network

import com.agungfir.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {

    @GET("trending/movie/week")
    suspend fun getList(): ListMovieResponse
}