package com.artemmoroz.anew.news.presentation.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.artemmoroz.anew.CoroutinesTestRule
import com.artemmoroz.anew.news.domain.model.News
import com.artemmoroz.anew.news.domain.useCase.GetNewsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getNewsUseCase = mockk<GetNewsUseCase>()
    private val newsViewModel by lazy { NewsViewModel(getNewsUseCase) }

    @Test
    fun testNewsViewModel_Success() = runBlockingTest {
        val news = listOf<News>()
        coEvery { getNewsUseCase.execute(SOURCES) } returns Result.success(news)

        newsViewModel.loadNews()

        assertEquals(newsViewModel.newsList.value, news)
    }

    @Test
    fun testNewsViewModel_Error() = runBlockingTest {
        val error = Exception("Network Exception")
        coEvery { getNewsUseCase.execute(SOURCES) } returns Result.failure(error)

        newsViewModel.loadNews()

        assertEquals(newsViewModel.showErrorMessage.value, "An error occurred")
    }

    companion object {
        const val SOURCES = "techcrunch"
    }
}