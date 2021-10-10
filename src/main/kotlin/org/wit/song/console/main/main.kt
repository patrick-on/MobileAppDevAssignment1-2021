package org.wit.song.console.main

import mu.KotlinLogging
import org.wit.song.console.models.SongModel

private val logger = KotlinLogging.logger {}

var song = SongModel()
val songs = ArrayList<SongModel>()

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
            4 -> searchSong()
            -99 -> dummyData()
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
    println(" 4. Search Songs")
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
    var aSong = SongModel()
    println("Add Song")
    println()
    print("Enter a Title : ")
    aSong.title = readLine()!!
    print("Enter an Artist : ")
    aSong.artist = readLine()!!

    if (aSong.title.isNotEmpty() && aSong.artist.isNotEmpty()) {
        aSong.id = songs.size.toLong()
        songs.add(aSong.copy())
        logger.info("Song Added : [ $aSong ]")
    }
    else
        logger.info("Song Not Added")
}

fun updateSong() {
    println("Update Song")
    println()
    listSongs()
    var searchId = getId()
    val aSong = search(searchId)
    var tempTitle : String?
    var tempArtist : String?

    if(aSong != null) {
        print("Enter a new Title for [ " + aSong.title + " ] : ")
        tempTitle = readLine()!!
        print("Enter a new Artist for [ " + aSong.artist + " ] : ")
        tempArtist = readLine()!!

        if (!tempTitle.isNullOrEmpty() && !tempArtist.isNullOrEmpty()) {
            aSong.title = tempTitle
            aSong.artist = tempArtist
            println(
                    "You updated [ " + aSong.title + " ] for title " +
                            "and [ " + aSong.artist + " ] for artist")
            logger.info("Song Updated : [ $aSong ]")
        }
        else
            logger.info("Song Not Updated")
    }
    else
        println("Song Not Updated...")
}

fun listSongs() {
    println("List All Songs")
    println()
    songs.forEach { logger.info("${it}") }
    println()
}

fun searchSong() {

    var searchId = getId()
    val aSong = search(searchId)

    if(aSong != null)
        println("Song Details [ $aSong ]")
    else
        println("Song Not Found...")
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : SongModel? {
    var foundSong: SongModel? = songs.find { s -> s.id == id }
    return foundSong
}

fun dummyData() {
    songs.add(SongModel(1, "21", "Polo G"))
    songs.add(SongModel(2, "Martin & Gina", "Polo G"))
    songs.add(SongModel(3, "2055", "Sleepy Hallows"))
}