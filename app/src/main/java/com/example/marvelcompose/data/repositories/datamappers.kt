package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.*
import com.example.marvelcompose.data.network.entities.*

fun ApiCharacter.asCharacter(): Character = Character(
    id = id,
    title = name,
    description = description,
    thumbnail = thumbnail.asString(),
    references = listOf(
        comics.toDomain(ReferenceList.Type.COMIC),
        events.toDomain(ReferenceList.Type.EVENT),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls = urls.map { Url(it.type, it.url) }
)

fun ApiEvent.asEvent(): Event = Event(
    id = id,
    title = title,
    description = description,
    thumbnail = thumbnail.asString(),
    references = listOf(
        comics.toDomain(ReferenceList.Type.COMIC),
        characters.toDomain(ReferenceList.Type.EVENT),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls = urls.map { Url(it.type, it.url) }
)

fun ApiComic.asComic(): Comic = Comic(
    id = id,
    title = title,
    description = description ?: "",
    thumbnail = thumbnail.asString(),
    format = format.toDomain(),
    references = listOf(
        characters.toDomain(ReferenceList.Type.CHARACTER),
        events.toDomain(ReferenceList.Type.EVENT),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls = urls.map { Url(it.type, it.url) }
)

fun ApiCreator.asCreator(): Creator = Creator(
    id = id,
    title = firstName,
    description = lastName,
    thumbnail = thumbnail.asString(),
    references = listOf(
        comics.toDomain(ReferenceList.Type.CHARACTER),
        events.toDomain(ReferenceList.Type.EVENT),
        series.toDomain(ReferenceList.Type.SERIES),
        stories.toDomain(ReferenceList.Type.STORY)
    ),
    urls = urls.map { Url(it.type, it.url) }
)

private fun String.toDomain(): Comic.Format = when (this) {
    "magazine" -> Comic.Format.MAGAZINE
    "trade paperback" -> Comic.Format.TRADE_PAPERBACK
    "hardcover" -> Comic.Format.HARDCOVER
    "digest" -> Comic.Format.DIGEST
    "graphic novel" -> Comic.Format.GRAPHIC_NOVEL
    "digital comic" -> Comic.Format.DIGITAL_COMIC
    "infinite comic" -> Comic.Format.INFINITE_COMIC
    else -> Comic.Format.COMIC
}

fun Comic.Format.toStringFormat(): String = when (this) {
    Comic.Format.COMIC -> "comic"
    Comic.Format.MAGAZINE -> "magazine"
    Comic.Format.TRADE_PAPERBACK -> "trade paperback"
    Comic.Format.HARDCOVER -> "hardcover"
    Comic.Format.DIGEST -> "digest"
    Comic.Format.GRAPHIC_NOVEL -> "graphic novel"
    Comic.Format.DIGITAL_COMIC -> "digital comic"
    Comic.Format.INFINITE_COMIC -> "infinite comic"
}

private fun ApiReferenceList.toDomain(type: ReferenceList.Type): ReferenceList {
    return ReferenceList(
        type,
        items
            ?.let { items.map { Reference(it.name) } }
            ?: emptyList()
    )
}