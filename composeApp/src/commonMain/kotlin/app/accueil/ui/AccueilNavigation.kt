package app.accueil.ui

import app.splashScreen.ui.SplashScreenNavigation
import decompose.RootNavigationComponent

sealed interface AccueilNavigation {
    data object GoToParametres : AccueilNavigation
}

fun convertAccueilNavigationToConfig(navigation: AccueilNavigation): RootNavigationComponent.Configuration {
    return when (navigation) {
        is AccueilNavigation.GoToParametres -> RootNavigationComponent.Configuration.Parametres
    }
}