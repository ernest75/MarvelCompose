package com.example.marvelcompose.data.network.entities

data class ApiSeries(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiComic>,
    val returned: Int
)