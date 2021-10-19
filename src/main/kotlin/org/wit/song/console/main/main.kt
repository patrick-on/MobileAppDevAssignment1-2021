package org.wit.song.console.main


import org.wit.song.console.controllers.SongController
import tornadofx.launch

val controller = SongController()

fun main(args: Array<String>) {
    //controller.start()
    launch<MainApp>(args)
}