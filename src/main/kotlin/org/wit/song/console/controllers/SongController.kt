package org.wit.song.console.controllers

import mu.KotlinLogging
import org.wit.song.console.models.SongJSONStore
import org.wit.song.console.models.SongMemStore
import org.wit.song.console.models.SongModel
import org.wit.song.console.views.SongView

class SongController {

    val songs = SongJSONStore()
    val songView = SongView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Song Console App" }
        println("Song Kotlin App Version 3.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Song Console App" }
    }

    fun menu() :Int { return songView.menu() }

    fun add(){
        var aSong = SongModel()

        if (songView.addSongData(aSong))
            songs.create(aSong)
        else
            logger.info("Song Not Added")
    }

    fun list() {
        songView.listSongs(songs)
    }

    fun update() {

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

    fun search() {
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

    fun delete() {
        songView.listSongs(songs)
        var searchId = songView.getId()
        val aSong = search(searchId)

        if(aSong != null) {
            songs.delete(aSong)
            println("Song Deleted...")
            songView.listSongs(songs)
        }
        else
            println("Song Not Deleted...")
    }
}