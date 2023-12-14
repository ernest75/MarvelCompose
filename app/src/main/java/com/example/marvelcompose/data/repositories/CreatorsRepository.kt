package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Creator
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.network.CreatorsService
import javax.inject.Inject

class CreatorsRepository @Inject constructor(private val service: CreatorsService) : Repository<Creator>() {

    suspend fun get(): Result<List<Creator>> = super.get {
        service
            .getCreators(0, 100)
            .data
            .results
            .map { it.asCreator() }
    }

    suspend fun find(id: Int): Result<Creator> = super.find(
        id,
        findActionRemote = {
            service
                .findCreator(id)
                .data
                .results
                .first()
                .asCreator()
        }
    )

}