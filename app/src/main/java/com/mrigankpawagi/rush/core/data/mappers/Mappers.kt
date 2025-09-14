package com.mrigankpawagi.rush.core.data.mappers

import com.mrigankpawagi.rush.core.data.database.SongEntity
import com.mrigankpawagi.rush.core.domain.backup.SongSchema
import com.mrigankpawagi.rush.core.domain.data_classes.Song

fun SongEntity.toSong(): Song {
    return Song(
        id = id,
        title = title,
        artists = artists,
        lyrics = lyrics,
        album = album,
        sourceUrl = sourceUrl,
        artUrl = artUrl,
        syncedLyrics = syncedLyrics,
        geniusLyrics = geniusLyrics,
        dateAdded = dateAdded
    )
}

fun Song.toSongEntity(): SongEntity {
    return SongEntity(
        id = id,
        title = title,
        artists = artists,
        lyrics = lyrics,
        album = album,
        sourceUrl = sourceUrl,
        artUrl = artUrl,
        syncedLyrics = syncedLyrics,
        dateAdded = dateAdded,
        geniusLyrics = geniusLyrics
    )
}

fun Song.toSongSchema(): SongSchema {
    return SongSchema(
        id = id,
        title = title,
        artists = artists,
        lyrics = lyrics,
        album = album,
        sourceUrl = sourceUrl,
        artUrl = artUrl,
        syncedLyrics = syncedLyrics,
        dateAdded = dateAdded,
        geniusLyrics = geniusLyrics
    )
}

fun SongSchema.toSong(): Song {
    return Song(
        id = id,
        title = title,
        artists = artists,
        lyrics = lyrics,
        album = album,
        sourceUrl = sourceUrl,
        artUrl = artUrl,
        syncedLyrics = syncedLyrics,
        dateAdded = dateAdded,
        geniusLyrics = geniusLyrics
    )
}