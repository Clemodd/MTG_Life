package app.splashScreen.data.repository

import app.splashScreen.data.datasource.SplashScreenLocalDataSource
import domain.constante.VERIFICATION_MAIL_ETAPE
import domain.enum.EtapeInscriptionEnum
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SplashScreenRepositoryImplTest {

    @Mock
    val splashScreenDataSource = mock(classOf<SplashScreenLocalDataSource>())

    private lateinit var splashScreenRepository: SplashScreenRepositoryImpl
    private lateinit var token: String
    private lateinit var etape: String

    @BeforeTest
    fun setUp() = runTest {
        token = "token123"
        etape = VERIFICATION_MAIL_ETAPE

        splashScreenRepository = SplashScreenRepositoryImpl(splashScreenDataSource)
    }

    @Test
    fun shouldReturnToken() = runTest {
        // Given
        coEvery { splashScreenDataSource.getToken() }.invokes { -> token }

        // When
        val newToken = splashScreenRepository.getToken()

        // Then
        assertEquals(
            token,
            newToken
        )
    }

    @Test
    fun shouldReturnEtape() = runTest {
        // Given
        coEvery { splashScreenDataSource.getEtape() }.invokes { -> VERIFICATION_MAIL_ETAPE }
        val etapeAttendue = EtapeInscriptionEnum.VERIFICATION_MAIL

        // When
        val newEtape = splashScreenRepository.getEtape()

        // Then
        assertEquals(
            etapeAttendue,
            newEtape
        )
    }
}