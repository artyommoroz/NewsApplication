package com.artemmoroz.anew.news.domain.useCase

import com.artemmoroz.anew.news.domain.NewsRepository
import com.artemmoroz.anew.news.domain.model.News
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetNewsUseCaseTest {

    private val newsRepository = mockk<NewsRepository>()
    private val getNewsUseCase by lazy { GetNewsUseCase(newsRepository) }

    @Test
    fun testGetNewsUseCases_Success() = runBlockingTest {
        val news = listOf<News>()
        coEvery { newsRepository.getNews(SOURCES) } returns news

        val result = getNewsUseCase.execute(SOURCES)

        assertEquals(result.getOrNull(), news)
    }

    @Test
    fun testGetNewsUseCases_Error() = runBlockingTest {
        val error = Exception("Network Exception")
        coEvery { newsRepository.getNews(SOURCES) } throws error

        val result = getNewsUseCase.execute(SOURCES)

        assertEquals(result.exceptionOrNull(), error)
    }

    companion object {
        const val SOURCES = "techcrunch"
    }
}