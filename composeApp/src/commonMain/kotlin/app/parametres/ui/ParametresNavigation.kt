package app.parametres.ui

import decompose.RootNavigationComponent

sealed interface ParametresNavigation {
    data object GoBack : ParametresNavigation
}

fun convertParametresNavigationToConfig(navigation: ParametresNavigation): RootNavigationComponent.Configuration {
    return when (navigation) {
        is ParametresNavigation.GoBack -> RootNavigationComponent.Configuration.GoBack
    }
}