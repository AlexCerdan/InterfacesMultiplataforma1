package net.tubalcain.interfacesmultiplataforma

import androidx.compose.runtime.Composable


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NombreUsuario() {

    var nombre by remember {
        mutableStateOf("")
    }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Tu nombre") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Hola, $nombre")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { nombre = "" }
            ) {
                Text("Limpiar")
            }
        }
    }
}
