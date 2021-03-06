package com.artemmoroz.anew.news.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemmoroz.anew.news.domain.model.News
import com.artemmoroz.anew.news.domain.useCase.GetNewsUseCase
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    val newsList = MutableLiveData<List<News>>()
    val showErrorMessage = MutableLiveData<String>()

    init {
        loadNews()
    }

    fun loadNews() {
        viewModelScope.launch {
            // Sources parameter is required to work with NewsAPI
            getNewsUseCase.execute("techcrunch")
                .onSuccess { newsList.postValue(it) }
                .onFailure { showErrorMessage.postValue("An error occurred") }
        }
    }
}