package net.tubalcain.interfacesmultiplataforma


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PlanazoAppTarea() {

    val fondo = Color(0xFF101018)
    val tarjeta = Color(0xFF252535)
    val morado = Color(0xFFB5A2FF)

    // LISTA DE EVENTOS
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
            ),

            // RETO 1: NUEVO EVENTO MUSICAL
            Evento(
                "Concierto de Quevedo",
                "Música",
                "Sábado · 21:00",
                "🎤",
                "Concierto de Quevedo con sus mejores canciones."
            ),

            // RETO 2: NUEVA CATEGORÍA DEPORTES
            Evento(
                "Torneo de Baloncesto",
                "Deportes",
                "Domingo · 17:00",
                "🏀",
                "Competición de baloncesto entre diferentes equipos."
            )
        )
    }

    // ESTADOS
    var busqueda by remember { mutableStateOf("") }
    var categoriaElegida by remember {
        mutableStateOf("Todos")
    }

    // NAVEGACIÓN
    var pantalla by remember { mutableStateOf("lista") }
    var eventoSeleccionado by remember {
        mutableStateOf<Evento?>(null)
    }

    // FORMULARIO
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var inscrito by remember { mutableStateOf(false) }

    // RETO 4: FILTRADO COMBINADO
    val eventosFiltrados = eventos.filter { evento ->

        val coincideBusqueda = evento.nombre.contains(
            busqueda,
            ignoreCase = true
        )

        val coincideCategoria =
            categoriaElegida == "Todos" ||
                    evento.categoria == categoriaElegida

        coincideBusqueda && coincideCategoria
    }

    MaterialTheme {

        Scaffold(
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
                        "🎟️ PLANAZO",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "DAM2",
                        color = morado
                    )
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

                    // TÍTULO
                    item {
                        Column(
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                "¿Qué plan tienes?",
                                color = Color.White,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                "Descubre eventos que no te puedes perder",
                                color = Color.LightGray
                            )
                        }
                    }

                    // RETO 3: BUSCADOR PERSONALIZADO
                    item {
                        OutlinedTextField(
                            value = busqueda,
                            onValueChange = {
                                busqueda = it
                            },
                            label = {
                                Text("¿Qué evento buscas?")
                            },
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedLabelColor = morado,
                                unfocusedLabelColor = Color.LightGray,
                                focusedBorderColor = morado,
                                unfocusedBorderColor = Color.Gray,
                                cursorColor = morado
                            )
                        )
                    }

                    // RETO 2: FILTROS POR CATEGORÍA
                    item {
                        Row(
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(
                                "Todos",
                                "Música",
                                "Gaming",
                                "Creadores",
                                "Deportes"
                            ).forEach { categoria ->

                                FilterChip(
                                    selected = categoriaElegida == categoria,
                                    onClick = {
                                        categoriaElegida = categoria
                                    },
                                    label = {
                                        Text(categoria)
                                    }
                                )
                            }
                        }
                    }

                    // LISTA DE EVENTOS
                    items(eventosFiltrados) { evento ->

                        Card(
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth(),
                            onClick = {
                                eventoSeleccionado = evento
                                pantalla = "detalle"

                                nombre = ""
                                correo = ""
                                inscrito = false
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = tarjeta
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {

                                Text(
                                    evento.emoji,
                                    fontSize = 36.sp
                                )

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        evento.nombre,
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        evento.categoria,
                                        color = morado
                                    )

                                    Text(
                                        evento.fecha,
                                        color = Color.LightGray
                                    )
                                }

                                Text(
                                    "›",
                                    color = morado,
                                    fontSize = 30.sp
                                )
                            }
                        }
                    }

                    // RETO 5: BÚSQUEDA SIN RESULTADOS
                    if (eventosFiltrados.isEmpty()) {
                        item {
                            Text(
                                "No se encontraron eventos",
                                color = Color.LightGray
                            )
                        }
                    }

                } else {

                    // PANTALLA DE DETALLE
                    item {

                        Column(
                            modifier = Modifier
                                .widthIn(max = 850.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            TextButton(
                                onClick = {
                                    pantalla = "lista"
                                }
                            ) {
                                Text("← Volver")
                            }

                            eventoSeleccionado?.let { evento ->

                                Text(
                                    evento.emoji,
                                    fontSize = 60.sp
                                )

                                Text(
                                    evento.nombre,
                                    color = Color.White,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    evento.fecha,
                                    color = morado,
                                    fontSize = 18.sp
                                )

                                Text(
                                    evento.descripcion,
                                    color = Color.LightGray
                                )

                                HorizontalDivider()

                                Text(
                                    "Apúntate al evento",
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OutlinedTextField(
                                    value = nombre,
                                    onValueChange = {
                                        nombre = it
                                    },
                                    label = {
                                        Text("Nombre")
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )

                                OutlinedTextField(
                                    value = correo,
                                    onValueChange = {
                                        correo = it
                                    },
                                    label = {
                                        Text("Correo electrónico")
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )

                                Button(
                                    onClick = {
                                        inscrito = true
                                    },
                                    enabled = nombre.isNotBlank() &&
                                            correo.contains("@")
                                ) {
                                    Text("Apuntarme")
                                }

                                if (inscrito) {
                                    Text(
                                        "¡Inscripción simulada completada!",
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
