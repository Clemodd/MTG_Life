package app.accueil.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import commun.enAttentMiseAJourAndroid.monImage

@Composable
fun CompteurComposable(
    fond: String,
    hauteur: Dp,
    largeur: Dp,
    sens: String,
    scoreDeBase: Int,
    modificationScore: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(hauteur)
            .graphicsLayer {
                rotationZ = when (sens) {
                    "haut" -> 180f
                    "bas" -> 0f
                    else -> 0f
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            monImage(
                image = fond,
                taille = largeur.value.toInt(),
                modifier = Modifier
                    .blur(40.dp)
                    .fillMaxSize(),
                isToCrop = true
            )
        }
        Text(
            text = scoreDeBase.toString(),
            fontSize = 70.sp,
            modifier = Modifier.blur(4.dp),
            color = Color.White,
        )
        Text(
            text = scoreDeBase.toString(),
            fontSize = 70.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .zIndex(1f)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            BoutonSigneComposable(
                signe = "-",
                onClick = { modificationScore(-1) },
                largeur = largeur
            )
            BoutonSigneComposable(
                signe = "+",
                onClick = { modificationScore(1) },
                largeur = largeur
            )
        }
    }
}
