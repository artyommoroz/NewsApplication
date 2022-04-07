package com.artemmoroz.anew.news.data

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    private val status: String,
    @SerializedName("totalResults")
    private val totalResults: Int,
    @SerializedName("articles")
    private val newsList: List<NewsDto>
)