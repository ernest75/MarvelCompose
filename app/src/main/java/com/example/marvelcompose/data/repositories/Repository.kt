package com.example.marvelcompose.data.repositories

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.marvelcompose.data.entities.*
import com.example.marvelcompose.data.entities.Result


abstract class Repository<T : MarvelItem> {

    private var cache: List<T> = emptyList()

    internal suspend fun get(getAction: suspend () -> List<T>): Result<List<T>> =
        tryCall {
            if (cache.isEmpty()) {
                cache = getAction()
            }
            cache
        }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T
    ): Result<T> = tryCall {
        val character = cache.find { it.id == id }
        character ?: findActionRemote()
    }

}
