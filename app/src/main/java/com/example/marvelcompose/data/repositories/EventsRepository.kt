package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.network.EventsService
import javax.inject.Inject

class EventsRepository@Inject constructor (private val eventsService: EventsService) : Repository<Event>() {

    suspend fun get(): Result<List<Event>> = super.get {
        eventsService
            .getEvents(0, 100)
            .data
            .results
            .map { it.asEvent() }
    }

    suspend fun find(id: Int): Result<Event> = super.find(
        id,
        findActionRemote = {
          eventsService
                .findEvent(id)
                .data
                .results
                .first()
                .asEvent()
        }
    )
}