package com.artemmoroz.anew.news

import com.artemmoroz.anew.news.data.NewsDto
import com.artemmoroz.anew.news.domain.News

fun NewsDto.toDomain() = News(
    title = title,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)

fun News.toDto() = NewsDto(
    title = title,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)