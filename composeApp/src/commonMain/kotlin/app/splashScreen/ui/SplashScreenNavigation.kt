package app.splashScreen.ui

import decompose.RootNavigationComponent

sealed interface SplashScreenNavigation {
    data object GoToAccueil : SplashScreenNavigation
}

fun convertSplashScreenNavigationToConfig(navigation: SplashScreenNavigation): RootNavigationComponent.Configuration {
    return when (navigation) {
        is SplashScreenNavigation.GoToAccueil -> RootNavigationComponent.Configuration.Accueil
    }
}