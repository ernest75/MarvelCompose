package com.example.marvelcompose.data.network.entities

data class ApiCreator(
    val id: Int,
    val fullName: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val comics: ApiReferenceList,
    val events: ApiReferenceList,
    val modified: String,
    val series: ApiReferenceList,
    val stories: ApiReferenceList,
    val suffix: String,
    val thumbnail: ApiThumbnail,
    val urls: List<ApiUrl>,
    val resourceURI: String
)