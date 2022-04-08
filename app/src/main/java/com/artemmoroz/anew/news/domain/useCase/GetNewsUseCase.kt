package com.artemmoroz.anew.news.domain.useCase

import com.artemmoroz.anew.news.domain.NewsRepository
import com.artemmoroz.anew.news.domain.model.News
import java.lang.Exception

class GetNewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(sources: String): Result<List<News>> {
        return try {
            val news = newsRepository.getNews(sources)
            Result.success(news)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}