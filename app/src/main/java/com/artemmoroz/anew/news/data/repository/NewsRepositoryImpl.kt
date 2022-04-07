package com.artemmoroz.anew.news.data.repository

import com.artemmoroz.anew.core.network.ApiService
import com.artemmoroz.anew.news.domain.model.News
import com.artemmoroz.anew.news.domain.NewsRepository
import com.artemmoroz.anew.news.data.mapper.toDomain

class NewsRepositoryImpl(
    private val apiService: ApiService
) : NewsRepository {

    override suspend fun getNews(sources: String): List<News> {
        return apiService.getNews(sources).newsList.map { it.toDomain() }
    }
}