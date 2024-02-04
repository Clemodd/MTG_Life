package app.accueil.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoutonSigneComposable(
    signe: String,
    onClick: () -> Unit,
    largeur: Dp,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(largeur / 2)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onClick() }
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = signe,
            fontSize = if (signe == "-") 65.sp else 55.sp,
            modifier = Modifier.blur(4.dp),
            color = Color.White,
        )
        Text(
            text = signe,
            fontSize = if (signe == "-") 60.sp else 50.sp
        )
    }
}