package org.wit.song.console.controllers

import mu.KotlinLogging
import org.wit.song.console.models.SongJSONStore
import org.wit.song.console.models.SongModel
import org.wit.song.console.views.AddSongScreen
import org.wit.song.console.views.ListSongScreen
import org.wit.song.console.views.MenuScreen
import tornadofx.*

class SongUIController : Controller(){
    val songs = SongJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Song TornadoFX UI App" }
    }
    fun add(_title : String, _artist : String){

        var aSong = SongModel(title = _title, artist = _artist)
        songs.create(aSong)
        logger.info("Song Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListSongScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        songs.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddSongScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddSongScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListSongScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
}