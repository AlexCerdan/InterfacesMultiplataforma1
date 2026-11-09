package net.tubalcain.interfacesmultiplataforma.estudianteApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EstudianteApp() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Yellow)
        ) {
            Column(
                modifier = Modifier.padding(20.dp).padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text (
                        text = "DAM2",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ){
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        Text(
                            text = "Mi espacio DAM2",
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Text(
                            text = "Panel del estudiante",
                            fontSize = 17.sp,
                            color = Color.Gray
                        )
                    }


                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ){
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ){
                        Column (
                            modifier = Modifier.padding(18.dp)
                        ){
                            Text(
                                text = "4",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )

                            Text(
                                text = "Asignaturas",
                                color = Color.Gray
                            )

                        }
                    }

                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ){
                        Column (
                            modifier = Modifier.padding(18.dp)
                        ){
                            Text(
                                text = "3",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Green
                            )

                            Text(
                                text = "Tareas",
                                color = Color.Gray
                            )

                        }
                    }
                }

                Text(
                    text = "Últimas novedades",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Card(
                    modifier = Modifier.fillMaxWidth(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(
                            modifier = Modifier.size(52.dp)
                                .background(
                                    Color(0xFFE3EDFF),
                                    RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "\u25A4",
                                fontSize = 28.sp,
                                color = Color.Blue
                            )
                        }

                        Spacer(Modifier.width(12.dp))

                        Column{
                            Text(
                                text = "Desarrollo de Interfaces",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                            Text(
                                text = "Nueva practica de Compose",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

