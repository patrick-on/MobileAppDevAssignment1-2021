package org.wit.music.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var title = ""
var artist = ""

fun main(args: Array<String>){
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
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Song")
    println(" 2. Update Song")
    println(" 3. List All Songs")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
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
    title = readLine()!!
    print("Enter an Artist : ")
    artist = readLine()!!
    println("You entered [ $title ] for title and [ $artist ] for artist.")
}

fun updateSong() {
    println("Update Song")
    println()
    print("Enter a new Title for [ $title ] : ")
    title = readLine()!!
    print("Enter a new Artist for [ $artist ] : ")
    artist = readLine()!!
    println("You updated [ $title ] for title and [ $artist ] for artist")
}


fun listSongs() {
    println("You Chose List All Songs")
}