package org.wit.song.console.views

import org.wit.song.console.models.SongMemStore
import org.wit.song.console.models.SongModel

class SongView {

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

    fun listSongs(songs : SongMemStore) {
        println("List All Songs")
        println()
        songs.logAll()
        println()
    }

    fun showSong(song : SongModel) {
        if(song != null)
            println("Song Details [ $song ]")
        else
            println("Song Not Found...")
    }

    fun addSongData(song : SongModel) : Boolean {

        println()
        print("Enter a Title : ")
        song.title = readLine()!!
        print("Enter an Artist : ")
        song.artist = readLine()!!

        return song.title.isNotEmpty() && song.artist.isNotEmpty()
    }

    fun updateSongData(song : SongModel) : Boolean {

        var tempTitle: String?
        var tempArtist: String?

        if (song != null) {
            print("Enter a new Title for [ " + song.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Artist for [ " + song.artist + " ] : ")
            tempArtist = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempArtist.isNullOrEmpty()) {
                song.title = tempTitle
                song.artist = tempArtist
                return true
            }
        }
        return false
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
}