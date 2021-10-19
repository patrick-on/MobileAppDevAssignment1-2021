package org.wit.song.console.views
import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.song.console.controllers.SongUIController
import tornadofx.*

class MenuScreen: View("Song Main Menu") {
    val songUIController: SongUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Song") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        songUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Songs") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        songUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }
}

