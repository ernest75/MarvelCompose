package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Reference
import com.example.marvelcompose.data.network.entities.ApiCharacter
import com.example.marvelcompose.data.network.ApiClient
import com.example.marvelcompose.data.network.entities.asString

object CharactersRepository {

    suspend fun getCharacters(): List<Character> {
        val result = ApiClient.charactersService.getCharacters(0, 100)

        return result.data.results.map {
           it.toCharacter()
        }
    }

    suspend fun finCharacter(characterId: Int): Character {
        val result = ApiClient.charactersService.findCharacter(characterId)
        return result.data.results.first().toCharacter()

    }
}

fun ApiCharacter.toCharacter(): Character {
    val comics = comics.items.map { Reference(it.name) }
    val events = events.items.map { Reference(it.name) }
    val stories = stories.items.map { Reference(it.name) }
    val series = series.items.map { Reference(it.name) }
    return Character(
        id = id,
        name = name,
        description = description,
        thumbnail = thumbnail.asString(),
        comics = comics,
        events = events,
        stories = stories,
        series = series
    )
}