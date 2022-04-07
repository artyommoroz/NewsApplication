package com.artemmoroz.anew.core.network

import com.artemmoroz.anew.news.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("sources") sources: String
    ): NewsResponse
}