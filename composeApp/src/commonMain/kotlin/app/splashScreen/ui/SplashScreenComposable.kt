package app.splashScreen.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import commun.theme.LocalCouleurPalette

@Composable
fun SplashScreenPreview() {
    SplashScreenComposable()
}

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
    navigateTo: (SplashScreenNavigation) -> Unit
) {
    val navigationEvent by viewModel.navigationEvent.collectAsState()

    LaunchedEffect(navigationEvent) {
        navigationEvent?.let { navigation ->
            navigateTo(navigation)
        }
        viewModel.clearNavigationEvent()
    }

    SplashScreenComposable()
}

@Composable
fun SplashScreenComposable() {
    Surface(
        color = LocalCouleurPalette.current.fondEcran,
        modifier = Modifier.fillMaxSize()
    ) {}
}