package commun.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class Couleurs(
    val fondEcranDark: Color = Color(0xff32264b),
    val fondEcranLight: Color = Color(0xffffc7a7),
    val fondEncadreDark: Color = Color(0xff2f2d41),
    val fondEncadreLight: Color = Color(0xff32264b),
    val fondInputDark: Color = Color(0xff32264b),
    val fondInputLight: Color = Color(0xffeeeeee),
    val fondSecondaireDark: Color = Color(0xfffe978e), // Dans gestionFinance, pour extra, la date et les options
    val fondSecondaireLight: Color = Color(0xff2f2d41), // Dans gestionFinance, pour extra, la date et les options
    val fondPopinErreur: Color = Color(0xfffe978e),
    val texteLight: Color = Color(0xff32264b0),
    val texteDark: Color = Color(0xffeeeeee),
    val textePopinErreur: Color = Color(0xff32264b),
    val texteTitreInput: Color = Color(0xffeeeeee),
    val texteBouton: Color = Color(0xffeeeeee),
)

data class MesCouleurs(
    val fondEcran: Color = Color.Unspecified,
    val fondEcranCentre: Color = Color.Unspecified,
    val fondInput: Color = Color.Unspecified,
    val fondSecondaire: Color = Color.Unspecified,
    val texte: Color = Color.Unspecified,
)

val LocalColors = compositionLocalOf { Couleurs() }
val LocalCouleurPalette = compositionLocalOf { MesCouleurs() }

val couleurs: Couleurs
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

val mesCouleurs: MesCouleurs
    @Composable
    @ReadOnlyComposable
    get() = LocalCouleurPalette.current