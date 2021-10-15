package org.wit.song.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class SongMemStore : SongStore {

    val songs = ArrayList<SongModel>()

    override fun findAll(): List<SongModel> {
        return songs
    }

    override fun findOne(id: Long) : SongModel? {
        var foundSong: SongModel? = songs.find { s -> s.id == id }
        return foundSong
    }

    override fun create(song: SongModel) {
        song.id = getId()
        songs.add(song)
        logAll()
    }

    override fun update(song: SongModel) {
        var foundSong = findOne(song.id!!)
        if (foundSong != null) {
            foundSong.title = song.title
            foundSong.artist = song.artist
        }
    }

    internal fun logAll() {
        songs.forEach { logger.info("${it}") }
    }
}