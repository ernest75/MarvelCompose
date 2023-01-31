package com.example.marvelcompose.data.network.entities

data class ApiCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ApiThumbnail,
    val comics: ApiComics,
    val events: ApiEvents,
    val modified: String,
    val resourceURI: String,
    val series: ApiSeries,
    val stories: ApiStories,
    val urls: List<ApiUrl>
)