package net.tubalcain.interfacesmultiplataforma

import BuscadorSpotifyApp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "InterfacesMultiplataforma",
    ) {
       BuscadorSpotifyApp()
    }
}