package com.artemmoroz.anew.news.presentation

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

    private fun loadNews() {
        viewModelScope.launch {
            getNewsUseCase.execute("techcrunch")
                .onSuccess { newsList.postValue(it) }
                .onFailure { showErrorMessage.postValue("An error occurred") }
        }
    }
}