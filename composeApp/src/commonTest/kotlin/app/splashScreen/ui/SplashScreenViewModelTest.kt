package app.splashScreen.ui

import app.splashScreen.domain.SplashScreenRepository
import domain.enum.EtapeInscriptionEnum
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import setTestDispatcher
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


class SplashScreenViewModelTest : KoinTest {

    private lateinit var viewModel: SplashScreenViewModel

    @Mock
    val splashScreenRepository = mock(classOf<SplashScreenRepository>())

    @BeforeTest
    fun setUp() = runTest {
        startKoin {
            modules(module {
                single<SplashScreenRepository> { splashScreenRepository }
            })
        }

        coEvery { splashScreenRepository.getToken() }.invokes { -> "token123" }
        coEvery { splashScreenRepository.getEtape() }.invokes { -> EtapeInscriptionEnum.ACCUEIL }

        // Dispatcher de test qui éxécute les coroutines de manière synchrone
        setTestDispatcher()

        viewModel = SplashScreenViewModel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
    }

    @Test
    fun shouldRedirectToCreationConnectionCompteIfTokenIsEmpty() = runTest {
        // Given
        coEvery { splashScreenRepository.getToken() }.invokes { -> "" }

        // When
        val viewModel = SplashScreenViewModel()

        // Then
        assertEquals(
            SplashScreenNavigation.GoToCreationConnectionCompte,
            viewModel.navigationEvent.value
        )
    }

    @Test
    fun shouldRedirectToVerificationMailIfTokenPresentAndEtapeIsVerificationMail() = runTest {
        // Given
        coEvery { splashScreenRepository.getEtape() }.invokes { -> EtapeInscriptionEnum.VERIFICATION_MAIL }

        // When
        val viewModel = SplashScreenViewModel()

        // Then
        assertEquals(
            SplashScreenNavigation.GoToVerificationMail,
            viewModel.navigationEvent.value
        )
    }

    @Test
    fun shouldRedirectToChoixCouleurIfTokenPresentAndEtapeIsChoixCouleur() = runTest {
        // Given
        coEvery { splashScreenRepository.getEtape() }.invokes { -> EtapeInscriptionEnum.CHOIX_COULEUR }

        // When
        val viewModel = SplashScreenViewModel()

        // Then
        assertEquals(
            SplashScreenNavigation.GoToChoixCouleur,
            viewModel.navigationEvent.value
        )
    }

    @Test
    fun shouldRedirectToAccueilIfTokenPresentAndEtapeIsAccueil() = runTest {
        // Given
        coEvery { splashScreenRepository.getEtape() }.invokes { -> EtapeInscriptionEnum.ACCUEIL }

        // When
        val viewModel = SplashScreenViewModel()

        // Then
        assertEquals(
            SplashScreenNavigation.GoToAccueil,
            viewModel.navigationEvent.value
        )
    }

    @Test
    fun shouldRedirectToCreationConnectionCompteIfTokenPresentAndEtapeIsNone() = runTest {
        // Given
        coEvery { splashScreenRepository.getEtape() }.invokes { -> EtapeInscriptionEnum.NONE }

        // When
        val viewModel = SplashScreenViewModel()

        // Then
        assertEquals(
            SplashScreenNavigation.GoToCreationConnectionCompte,
            viewModel.navigationEvent.value
        )
    }

    @Test
    fun shouldClearNavigationEvent() {
        // When
        viewModel.clearNavigationEvent()

        // Then
        assertEquals(
            null,
            viewModel.navigationEvent.value
        )
    }
}