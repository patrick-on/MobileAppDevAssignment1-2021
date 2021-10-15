package org.wit.song.console.models

interface SongStore {
    fun findAll(): List<SongModel>
    fun findOne(id: Long): SongModel?
    fun create(song: SongModel)
    fun update(song: SongModel)
}