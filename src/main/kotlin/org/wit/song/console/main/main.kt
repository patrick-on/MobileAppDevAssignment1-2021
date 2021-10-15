package org.wit.song.console.main

import mu.KotlinLogging
import org.wit.song.console.models.SongMemStore
import org.wit.song.console.models.SongModel
import org.wit.song.console.views.SongView

private val logger = KotlinLogging.logger {}

val songs = SongMemStore()
val songView = SongView()

fun main(args: Array<String>) {
    logger.info { "Launching Song Console App" }
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = songView.menu()
        when(input) {
            1 -> addSong()
            2 -> updateSong()
            3 -> songView.listSongs(songs)
            4 -> searchSong()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Song Console App" }
}

fun addSong(){
    var aSong = SongModel()

    if (songView.addSongData(aSong))
        songs.create(aSong)
    else
        logger.info("Song Not Added")
}

fun updateSong() {

    songView.listSongs(songs)
    var searchId = songView.getId()
    val aSong = search(searchId)

    if(aSong != null) {
        if(songView.updateSongData(aSong)) {
            songs.update(aSong)
            songView.showSong(aSong)
            logger.info("Song Updated : [ $aSong ]")
        }
        else
            logger.info("Song Not Updated")
    }
    else
        println("Song Not Updated...")
}

fun searchSong() {
    val aSong = search(songView.getId())!!
    songView.showSong(aSong)
}


fun search(id: Long) : SongModel? {
    var foundSong = songs.findOne(id)
    return foundSong
}

fun dummyData() {
    songs.create(SongModel(1, "21", "Polo G"))
    songs.create(SongModel(2, "Martin & Gina", "Polo G"))
    songs.create(SongModel(3, "2055", "Sleepy Hallows"))
}