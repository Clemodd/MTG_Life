package app.accueil.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.accueil.ui.composable.CompteurComposable
import app.accueil.ui.composable.MenuComposable
import app.splashScreen.ui.SplashScreenNavigation
import commun.theme.LocalCouleurPalette

@Composable
fun AccueilPreview() {
    AccueilComposable(
        scoreDeBase = arrayOf(20, 20, 20, 20, 20, 20),
        enregistrerScore = { _, _ -> },
        restart = { },
        navigateToParametres = { },
        choixaleatoire1 = "mana/foret.png",
        choixaleatoire2 = "mana/mort.png",
    )
}

@Composable
fun Accueil(
    viewModel: AccueilViewModel,
    navigateTo: (AccueilNavigation) -> Unit
) {
    val score by viewModel.score.collectAsState()
    val navigationEvent by viewModel.navigationEvent.collectAsState()
    val choixaleatoire1 by viewModel.choixaleatoire1.collectAsState()
    val choixaleatoire2 by viewModel.choixaleatoire2.collectAsState()


    LaunchedEffect(navigationEvent) {
        navigationEvent?.let { navigation ->
            navigateTo(navigation)
        }
        viewModel.clearNavigationEvent()
    }

    AccueilComposable(
        scoreDeBase = score,
        enregistrerScore = { resultat, joueur ->
            viewModel.enregistrerScore(resultat, joueur)
        },
        restart = {
            viewModel.restart()
        },
        navigateToParametres = {
            viewModel.navigateToParametres()
        },
        choixaleatoire1 = choixaleatoire1,
        choixaleatoire2 = choixaleatoire2
    )
}

@Composable
fun AccueilComposable(
    scoreDeBase: Array<Int>,
    enregistrerScore: (Int, Int) -> Unit,
    restart: () -> Unit,
    navigateToParametres: () -> Unit,
    choixaleatoire1: String,
    choixaleatoire2: String
) {

    Surface(
        color = LocalCouleurPalette.current.fondEcran,
        modifier = Modifier.fillMaxSize()
    ) {
        BoxWithConstraints {
            val hauteur = maxHeight
            val largeur = maxWidth

            Box {
                MenuComposable(
                    modifier = Modifier.align(Alignment.Center),
                    onClickRestart = {
                        restart()
                    },
                    navigateToParametres = {
                        navigateToParametres()
                    }
                )
                Column {
                    CompteurComposable(
                        fond = choixaleatoire1,
                        hauteur = hauteur / 2,
                        largeur = largeur,
                        sens = "haut",
                        scoreDeBase = scoreDeBase[0],
                        modificationScore = { resultat ->
                            enregistrerScore(resultat, 0)
                        },
                    )
                    CompteurComposable(
                        fond = choixaleatoire2,
                        hauteur = hauteur / 2,
                        largeur = largeur,
                        sens = "bas",
                        scoreDeBase = scoreDeBase[1],
                        modificationScore = { resultat ->
                            enregistrerScore(resultat, 1)
                        },
                    )
                }
            }
        }
    }
}