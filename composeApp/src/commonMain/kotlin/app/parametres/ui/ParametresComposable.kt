package app.parametres.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import commun.enAttentMiseAJourAndroid.monImage
import commun.theme.LocalCouleurPalette

@Composable
fun ParametresPreview() {
    ParametresComposable(
        retour = { }
    )
}

@Composable
fun Parametres(
    viewModel: ParametresViewModel,
    navigateTo: (ParametresNavigation) -> Unit
) {
    val navigationEvent by viewModel.navigationEvent.collectAsState()

    LaunchedEffect(navigationEvent) {
        navigationEvent?.let { navigation ->
            navigateTo(navigation)
        }
        viewModel.clearNavigationEvent()
    }

    ParametresComposable(
        retour = {
            viewModel.retour()
        }
    )
}

@Composable
fun ParametresComposable(
    retour: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Surface(
        color = LocalCouleurPalette.current.fondEcran,
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        BoxWithConstraints {
            Box(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                monImage(
                    image = "icon/flèche_retour.png",
                    description = "Flèche retour",
                    taille = 60,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { retour() }
                        )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


                Text(
                    color = LocalCouleurPalette.current.texte,
                    modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
                    text = "Version 1.0.0"
                )

                Text(
                    text = "Mentions légales",
                    fontSize = 22.sp,
                    color = LocalCouleurPalette.current.texte,
                    modifier = Modifier.padding(10.dp),
                )

                Text(
                    text = "Informations légales",
                    fontSize = 18.sp,
                    color = LocalCouleurPalette.current.texte,
                    modifier = Modifier.padding(10.dp),
                )

                Text(
                    color = LocalCouleurPalette.current.texte,
                    modifier = Modifier.padding(10.dp).padding(top = 10.dp),
                    text = "\"MTG Life\" est une application fan, non-officielle, qui est permise grâce à la Fan Content Policiy de Wizard, consultable à l'adresse " +
                            ": https://company.wizards.com/en/legal/fancontentpolicy. Cette application n'est pas produite, approuvée, soutenue ou affiliée à Wizards."
                )

                Text(
                    color = LocalCouleurPalette.current.texte,
                    modifier = Modifier.padding(10.dp).padding(top = 10.dp),
                    text = "En utilisant cette application, vous acceptez sans réserve les présentes modalités. \n" +
                            "Aussi, conformément à l’article n°6 de la Loi n°2004-575 du 21 Juin 2004 pour la confiance dans l’économie numérique," +
                            " le responsable de la présente application est Clément Jumeline.\n\n" +
                            "Pour toute question ou préoccupation concernant les conditions d'utilisation de l'application, veuillez me contacter à weaverly.togethim@gmail.com \n\n" +
                            "Concernant la propriété intellectuelle, tous les contenus présents dans l'application \"MTG Life\", y compris mais de façon non exhaustive," +
                            " les graphismes, images, textes, animations, logos, icônes et leur mise en page, sont de ma propriété exclusive, à l'exception des éléments appartenant " +
                            "à des tiers partenaires ou auteurs comme \"Wizards\". " +
                            "\n\n Toute reproduction, distribution, modification, adaptation, retransmission ou publication," +
                            " même partielle, de ces différents éléments, est formellement interdite sans l'autorisation écrite préalable de Clément Jumeline. " +
                            "Une telle action sans autorisation constitue une violation susceptible d'entraîner des poursuites judiciaires et des sanctions sous les articles L.335-2" +
                            " et suivants du Code de la propriété intellectuelle.\n"
                )

                Text(
                    text = "Données personnelles",
                    fontSize = 18.sp,
                    color = LocalCouleurPalette.current.texte,
                    modifier = Modifier.padding(10.dp),
                )

                Text(
                    color = LocalCouleurPalette.current.texte,
                    modifier = Modifier.padding(10.dp).padding(top = 10.dp, bottom = 20.dp),
                    text = "L'application \"MTG Life\" respecte la vie privée de ses utilisateurs et s'engage à protéger les données personnelles collectées. Cette application ne recueille ni ne stocke aucune donnée personnelle des utilisateurs, à l'exception du score du jeu qui est enregistré localement sur le dispositif de l'utilisateur à des fins de fonctionnement du jeu. Aucune information n'est transmise ni stockée en dehors de l'appareil de l'utilisateur.\n" +
                            "\n" +
                            "Les scores stockés localement ne sont associés à aucun identifiant personnel ou à toute autre information permettant d'identifier l'utilisateur. L'utilisateur a la possibilité de réinitialiser ou de supprimer ces scores à tout moment en vidant le cache du jeu ou en le désinstallant.\n" +
                            "\n" +
                            "Pour toute question ou préoccupation concernant la gestion de vos données personnelles par l'application, veuillez nous contacter à weaverly.togethim@gmail.com.",
                )

            }
        }
    }
}