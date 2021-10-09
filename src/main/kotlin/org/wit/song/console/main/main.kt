package org.wit.song.console.main

import mu.KotlinLogging
import org.wit.song.console.models.SongModel

private val logger = KotlinLogging.logger {}

var song = SongModel()

fun main(args: Array<String>) {
    logger.info { "Launching Music Console App" }
    println("Music Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addSong()
            2 -> updateSong()
            3 -> listSongs()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Music Console App" }
}

fun menu() : Int {
    var option : Int
    var input: String?

    println("MAIN MENU")
    println(" 1. Add Song")
    println(" 2. Update Song")
    println(" 3. List All Songs")
    println("-1. Exit")
    println()
    print("Enter Option : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addSong(){
    println("Add Song")
    println()
    print("Enter a Title : ")
    song.title = readLine()!!
    print("Enter an Artist : ")
    song.artist = readLine()!!
    println("You entered [ " + song.title + " ] for title " +
            "and [ " + song.artist + " ] for artist")
}

fun updateSong() {
    println("Update Song")
    println()
    print("Enter a new Title for [ " + song.title + " ] : ")
    song.title = readLine()!!
    print("Enter a new Artist for [ " + song.artist + " ] : ")
    song.artist = readLine()!!
    println("You updated [ " + song.title + " ] for title " +
            "and [ " + song.artist + " ] for artist")
}

fun listSongs() {
    println("You Chose List All Songs")
}