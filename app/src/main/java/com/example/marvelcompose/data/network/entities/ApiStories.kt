package com.example.marvelcompose.data.network.entities

data class ApiStories(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiStory>,
    val returned: Int
)