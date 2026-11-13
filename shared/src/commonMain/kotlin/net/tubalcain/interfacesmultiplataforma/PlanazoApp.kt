package net.tubalcain.interfacesmultiplataforma

import BotonLimpiarFiltros
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

data class Evento(
    val nombre: String,
    val categoria: String,
    val fecha: String,
    val emoji: String,
    val descripcion: String
)

@Composable
fun PlanazoApp() {
    val fondo = Color(0xFF101018)
    val morado = Color(0xFFB5A2FF)

    val eventos = remember {
        listOf(
            Evento(
                "Festival Urbano",
                "Música",
                "Sábado · 20:00",
                "🎤",
                "Festival de música urbana."
            ),
            Evento(
                "Torneo EA FC",
                "Gaming",
                "Viernes · 17:00",
                "🎮",
                "Competición de fútbol virtual."
            ),
            Evento(
                "Noche de Streamers",
                "Creadores",
                "Viernes · 19:30",
                "📺",
                "Encuentro de creadores de contenido."
            ),
            Evento(
                "Batalla de DJs",
                "Música",
                "Sábado · 22:00",
                "🎧",
                "Sesión de música y DJs."
            ),
            Evento(
                "Torneo Minecraft",
                "Gaming",
                "Domingo · 18:00",
                "🏗️",
                "Retos de construcción por equipos."
            )
        )
    }

    // Estados de búsqueda, filtros, navegación y formulario.
    var busqueda by remember { mutableStateOf("") }
    var categoriaElegida by remember { mutableStateOf("Todos") }
    var mostrarDescripciones by remember { mutableStateOf(false) }
    var pantalla by remember { mutableStateOf("lista") }
    var eventoSeleccionado by remember { mutableStateOf<Evento?>(null) }

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var inscrito by remember { mutableStateOf(false) }

    val eventosFiltrados = eventos.filter { evento ->
        evento.nombre.contains(busqueda, ignoreCase = true) &&
                (categoriaElegida == "Todos" || evento.categoria == categoriaElegida)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Abre el detalle y reinicia los datos del formulario para el nuevo evento.
    fun abrirDetalle(evento: Evento) {
        eventoSeleccionado = evento
        pantalla = "detalle"
        nombre = ""
        correo = ""
        inscrito = false
    }

    MaterialTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            containerColor = fondo,
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(fondo)
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🎟️ PLANAZO",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "DAM2", color = morado)
                }
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (pantalla == "lista") {
                    // CabeceraSeccion se reutiliza en la pantalla de lista.
                    item {
                        CabeceraSeccion(
                            titulo = "¿Qué plan tienes?",
                            subtitulo = "Descubre eventos que no te puedes perder",
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth()
                        )
                    }

                    // EventoDestacado aparece encima de los resultados.
                    item {
                        val destacado = eventos.first()
                        EventoDestacado(
                            evento = destacado,
                            onClick = { abrirDetalle(destacado) },
                            modifier = Modifier.widthIn(max = 850.dp)
                        )
                    }

                    item {
                        BuscadorEventos(
                            texto = busqueda,
                            onTextoChange = { nuevoTexto -> busqueda = nuevoTexto },
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth()
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("Todos", "Música", "Gaming", "Creadores").forEach { categoria ->
                                CategoriaChip(
                                    categoria = categoria,
                                    seleccionada = categoriaElegida == categoria,
                                    onClick = { categoriaElegida = categoria }
                                )
                            }
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            BotonLimpiarFiltros(
                                onClick = {
                                    busqueda = ""
                                    categoriaElegida = "Todos"
                                }
                            )

                            Button(
                                onClick = {
                                    mostrarDescripciones = !mostrarDescripciones
                                }
                            ) {
                                Text(
                                    if (mostrarDescripciones) {
                                        "Ocultar descripciones"
                                    } else {
                                        "Mostrar descripciones"
                                    }
                                )
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Eventos encontrados: ${eventosFiltrados.size}",
                            color = Color.LightGray,
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth()
                        )
                    }

                    if (eventosFiltrados.isEmpty()) {
                        // Sustituye al Text anterior por el componente EstadoVacio.
                        item {
                            EstadoVacio(
                                titulo = "No hay eventos",
                                mensaje = "Prueba con otra búsqueda o cambia la categoría.",
                                emoji = "🎟️",
                                modifier = Modifier.widthIn(max = 850.dp)
                            )
                        }
                    } else {
                        items(eventosFiltrados) { evento ->
                            EventoCard(
                                evento = evento,
                                mostrarDescripcion = mostrarDescripciones,
                                onClick = { abrirDetalle(evento) }
                            )
                        }
                    }
                } else {
                    item {
                        Column(
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            TextButton(onClick = { pantalla = "lista" }) {
                                Text("← Volver")
                            }

                            eventoSeleccionado?.let { evento ->
                                // CabeceraSeccion se reutiliza también en el detalle.
                                CabeceraSeccion(
                                    titulo = evento.nombre,
                                    subtitulo = "Información e inscripción al evento"
                                )

                                Text(text = evento.emoji, fontSize = 60.sp)

                                Text(
                                    text = evento.fecha,
                                    color = morado,
                                    fontSize = 18.sp
                                )

                                Text(
                                    text = evento.descripcion,
                                    color = Color.LightGray
                                )

                                HorizontalDivider()

                                Text(
                                    text = "Apúntate al evento",
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OutlinedTextField(
                                    value = nombre,
                                    onValueChange = { nombre = it },
                                    label = { Text("Nombre") },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = Color.White,
                                        focusedLabelColor = morado,
                                        unfocusedLabelColor = Color.LightGray,
                                        focusedBorderColor = morado,
                                        unfocusedBorderColor = Color.Gray
                                    )
                                )

                                OutlinedTextField(
                                    value = correo,
                                    onValueChange = { correo = it },
                                    label = { Text("Correo electrónico") },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = Color.White,
                                        focusedLabelColor = morado,
                                        unfocusedLabelColor = Color.LightGray,
                                        focusedBorderColor = morado,
                                        unfocusedBorderColor = Color.Gray
                                    )
                                )

                                Button(
                                    onClick = {
                                        scope.launch {
                                            when {
                                                nombre.isBlank() -> {
                                                    snackbarHostState.showSnackbar(
                                                        "Introduce tu nombre"
                                                    )
                                                }

                                                correo.isBlank() -> {
                                                    snackbarHostState.showSnackbar(
                                                        "Introduce tu correo electrónico"
                                                    )
                                                }

                                                !correo.contains("@") -> {
                                                    snackbarHostState.showSnackbar(
                                                        "Introduce un correo electrónico válido"
                                                    )
                                                }

                                                else -> {
                                                    inscrito = true
                                                    snackbarHostState.showSnackbar(
                                                        "¡Inscripción realizada correctamente!"
                                                    )
                                                }
                                            }
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Apuntarme")
                                }

                                if (inscrito) {
                                    Text(
                                        text = "¡Inscripción simulada completada!",
                                        color = morado,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

