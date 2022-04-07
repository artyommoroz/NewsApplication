package com.artemmoroz.anew.news.domain

data class News(
    val title: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)