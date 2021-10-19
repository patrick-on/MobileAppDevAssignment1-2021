package org.wit.song.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.wit.placemark.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "songs.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<SongModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class SongJSONStore : SongStore {

    var songs = mutableListOf<SongModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<SongModel> {
        return songs
    }

    override fun findOne(id: Long) : SongModel? {
        var foundSong: SongModel? = songs.find { s -> s.id == id }
        return foundSong
    }

    override fun create(song: SongModel) {
        song.id = generateRandomId()
        songs.add(song)
        serialize()
    }

    override fun update(song: SongModel) {
        var foundSong = findOne(song.id!!)
        if (foundSong != null) {
            foundSong.title = song.title
            foundSong.artist = song.artist
        }
        serialize()
    }

    internal fun logAll() {
        songs.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(songs, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        songs = Gson().fromJson(jsonString, listType)
    }

    override fun delete(song: SongModel) {
        songs.remove(song)
        serialize()
    }
}