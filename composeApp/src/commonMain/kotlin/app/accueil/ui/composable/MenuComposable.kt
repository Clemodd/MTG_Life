package app.accueil.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import commun.enAttentMiseAJourAndroid.monImage

@Composable
fun MenuComposable(
    modifier: Modifier,
    onClickRestart: () -> Unit,
    navigateToParametres: () -> Unit,
) {
    val lineColor = MaterialTheme.colors.onSurface
    val strokeWidth = 2.dp
    val tailleOeil = 40.dp

    var expanded by remember { mutableStateOf(false) }
    val separationHeight by animateDpAsState(if (expanded) tailleOeil else 0.dp)

    BoxWithConstraints(
        modifier = modifier.then(Modifier.zIndex(1f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {}
            ) // Pour éviter qu'on clique sur les signes en dessous.
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(tailleOeil + separationHeight),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val canvasWidth = size.width
                val centerHeight = size.height / 2
                val topLineY = centerHeight - separationHeight.toPx() / 2
                val bottomLineY = centerHeight + separationHeight.toPx() / 2

                // Ligne du haut
                drawLine(
                    color = lineColor,
                    start = Offset(x = 0f, y = topLineY),
                    end = Offset(x = canvasWidth, y = topLineY),
                    strokeWidth = strokeWidth.toPx()
                )

                // Line du bas
                drawLine(
                    color = lineColor,
                    start = Offset(x = 0f, y = bottomLineY),
                    end = Offset(x = canvasWidth, y = bottomLineY),
                    strokeWidth = strokeWidth.toPx()
                )
            }

            // Fond
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(tailleOeil - 2.dp)
            ) {
                AnimatedVisibility(
                    visible = expanded,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    monImage(
                        image = "fond.png",
                        taille = 1000,
                        modifier = Modifier
                            .fillMaxSize()
                            .clipToBounds(),
                        isToCrop = true
                    )
                }
            }

            // Oeil
            if (!expanded) {
                monImage(
                    image = "icon/oeil/icon_oeil_ouvert.png",
                    taille = 50,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { expanded = true }
                        )
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(tailleOeil - 2.dp)
                ) {
                    monImage(
                        image = "fond.png",
                        taille = 1000,
                        modifier = Modifier
                            .fillMaxSize()
                            .clipToBounds(),
                        isToCrop = true
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        monImage(
                            image = "icon/restart.png",
                            taille = 40,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = { onClickRestart() }
                                )
                        )
                        monImage(
                            image = "icon/oeil/icon_oeil_blanc.png",
                            taille = 40,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = { expanded = false }
                                )
                        )
                        monImage(
                            image = "icon/parametres.png",
                            taille = 40,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = { navigateToParametres() }
                                )
                        )
                    }
                }
            }
        }
    }
}