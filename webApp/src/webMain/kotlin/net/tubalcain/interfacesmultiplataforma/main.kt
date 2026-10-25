package net.tubalcain.interfacesmultiplataforma

import BuscadorSpotifyApp
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        BuscadorSpotifyApp()
    }
}