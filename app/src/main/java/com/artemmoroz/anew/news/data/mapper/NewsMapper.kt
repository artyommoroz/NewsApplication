package com.artemmoroz.anew.news.data.mapper

import com.artemmoroz.anew.news.data.model.NewsDto
import com.artemmoroz.anew.news.domain.model.News

fun NewsDto.toDomain() = News(
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)

fun News.toDto() = NewsDto(
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)