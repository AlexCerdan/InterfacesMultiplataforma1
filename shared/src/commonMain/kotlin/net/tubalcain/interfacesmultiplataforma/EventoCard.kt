package net.tubalcain.interfacesmultiplataforma


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EventoCard(
    evento: Evento,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tarjeta = Color(0xFF252535)
    val morado = Color(0xFFB5A2FF)

    Card(
        modifier = modifier
            .widthIn(max = 850.dp)
            .fillMaxWidth(),
        onClick = onClick,
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
                text = evento.emoji,
                fontSize = 36.sp
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = evento.nombre,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = evento.categoria,
                    color = morado
                )

                Text(
                    text = evento.fecha,
                    color = Color.LightGray
                )
            }

            Text(
                text = "›",
                color = morado,
                fontSize = 30.sp
            )
        }
    }
}
