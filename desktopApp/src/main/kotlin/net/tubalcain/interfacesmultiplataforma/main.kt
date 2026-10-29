package net.tubalcain.interfacesmultiplataforma

import BuscadorSpotifyApp
import SpotifyCloneApp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "InterfacesMultiplataforma",
    ) {
       GamingHubApp()
    }
}