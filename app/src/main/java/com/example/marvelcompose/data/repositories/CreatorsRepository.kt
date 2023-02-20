package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Creator
import com.example.marvelcompose.data.network.ApiClient

object CreatorsRepository: Repository<Creator>() {

    suspend fun get(): List<Creator> = super.get {
        ApiClient
            .creatorsService
            .getCreators(0, 100)
            .data
            .results
            .map { it.asCreator() }
    }

    suspend fun find(id: Int): Creator = super.find(
        id,
        findActionRemote = {
            ApiClient
                .creatorsService
                .findCreator(id)
                .data
                .results
                .first()
                .asCreator()
        }
    )

}