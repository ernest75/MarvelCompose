package com.example.marvelcompose.data.network.entities

data class ApiComics(
    val available: Int,
    val collectionURI: String,
    val items: List<Comic>,
    val returned: Int
)