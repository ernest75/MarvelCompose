package com.example.marvelcompose.data.network.entities

data class ApiEvents(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiComic>,
    val returned: Int
)