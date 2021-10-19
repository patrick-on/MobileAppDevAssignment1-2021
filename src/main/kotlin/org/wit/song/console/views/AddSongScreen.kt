package org.wit.song.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.song.console.controllers.SongUIController
import tornadofx.*

class AddSongScreen: View("Add Song") {
    val model = ViewModel()
    val _title = model.bind { SimpleStringProperty() }
    val _artist = model.bind { SimpleStringProperty() }
    val songUIController: SongUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Title") {
                textfield(_title).required()
            }
            field("Description") {
                textarea(_artist).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        songUIController.add(_title.toString(),_artist.toString())

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        songUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _title.value = ""
        _artist.value = ""
        model.clearDecorators()
    }
}