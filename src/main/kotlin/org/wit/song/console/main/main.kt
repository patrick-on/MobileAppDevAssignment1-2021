package org.wit.song.console.main


import org.wit.song.console.controllers.SongController

val controller = SongController()

fun main(args: Array<String>) {
    controller.start()
}