package com.artemmoroz.anew.news.domain

import com.artemmoroz.anew.news.domain.model.News

interface NewsRepository {

    suspend fun getNews(sources: String): List<News>
}