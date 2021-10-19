package org.wit.song.console.views

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.song.console.controllers.SongUIController
import org.wit.song.console.models.SongModel
import tornadofx.*

class ListSongScreen: View("List Placemarks") {
    val songUIController: SongUIController by inject()
    val tableContent = songUIController.songs.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", SongModel::id)
            readonlyColumn("TITLE", SongModel::title)
            readonlyColumn("DESCRIPTION", SongModel::artist)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    songUIController.closeList()
                }
            }
        }
    }
}