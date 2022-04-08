package com.artemmoroz.anew.core.di

import com.artemmoroz.anew.core.network.ApiService
import com.artemmoroz.anew.core.network.AuthInterceptor
import com.artemmoroz.anew.news.data.repository.NewsRepositoryImpl
import com.artemmoroz.anew.news.domain.NewsRepository
import com.artemmoroz.anew.news.domain.useCase.GetNewsUseCase
import com.artemmoroz.anew.news.presentation.NewsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsapi.org"

private val apiModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get())
            .build()
    }

    single {
        GsonConverterFactory.create()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}

private val repositoryModule = module {
    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }
}

private val useCaseModule = module {
    single {
        GetNewsUseCase(get())
    }
}

private val viewModelModule = module {
    viewModel {
        NewsViewModel(get())
    }
}

val newsApplicationModules = listOf(
    apiModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)

