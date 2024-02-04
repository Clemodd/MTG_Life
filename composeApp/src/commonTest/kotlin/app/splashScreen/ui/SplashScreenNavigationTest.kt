package app.splashScreen.ui

import decompose.RootNavigationComponent
import kotlin.test.Test
import kotlin.test.assertEquals

class SplashScreenNavigationTest {

    @Test
    fun shouldConvertAccueilNavigationToAccueilConfig() {
        // Given
        val splashScreenNavigation = SplashScreenNavigation.GoToAccueil

        // When
        val rootNavigation = convertSplashScreenNavigationToConfig(splashScreenNavigation)

        // Then
        assertEquals(
            RootNavigationComponent.Configuration.Accueil,
            rootNavigation
        )
    }

    @Test
    fun shouldConvertCreationConnectionCompteNavigationToSplashScreenConfig() {
        // Given
        val splashScreenNavigation = SplashScreenNavigation.GoToCreationConnectionCompte

        // When
        val rootNavigation = convertSplashScreenNavigationToConfig(splashScreenNavigation)

        // Then
        assertEquals(
            RootNavigationComponent.Configuration.CreationConnectionCompte,
            rootNavigation
        )
    }

    @Test
    fun shouldConvertVerificationMailNavigationToVerificationMailConfig() {
        // Given
        val splashScreenNavigation = SplashScreenNavigation.GoToVerificationMail

        // When
        val rootNavigation = convertSplashScreenNavigationToConfig(splashScreenNavigation)

        // Then
        assertEquals(
            RootNavigationComponent.Configuration.VerificationMail,
            rootNavigation
        )
    }

    @Test
    fun shouldConvertChoixCouleurNavigationToChoixCouleurConfig() {
        // Given
        val splashScreenNavigation = SplashScreenNavigation.GoToChoixCouleur

        // When
        val rootNavigation = convertSplashScreenNavigationToConfig(splashScreenNavigation)

        // Then
        assertEquals(
            RootNavigationComponent.Configuration.ChoixCouleur,
            rootNavigation
        )
    }
}