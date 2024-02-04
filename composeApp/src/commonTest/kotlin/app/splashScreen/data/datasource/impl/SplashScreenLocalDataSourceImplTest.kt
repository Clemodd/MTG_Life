package app.splashScreen.data.datasource.impl

import com.russhwolf.settings.Settings
import domain.constante.ETAPE_INSCRIPTION
import domain.constante.MY_USER_TOKEN
import domain.constante.VERIFICATION_MAIL_ETAPE
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.every
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import specificitePlateforme.StockageEnLocalRepository
import specificitePlateforme.StringSettingConfig
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SplashScreenLocalDataSourceImplTest {

    @Mock
    val stockageRepository = mock(classOf<StockageEnLocalRepository>())

    @Mock
    val settings = mock(classOf<Settings>())

    private lateinit var stringSettingConfig: StringSettingConfig

    private lateinit var splashScreenDataSource: SplashScreenLocalDataSourceImpl

    private lateinit var token: String
    private lateinit var etape: String
    private lateinit var key: String

    @BeforeTest
    fun beforeTest() {
        token = "token123"
        etape = VERIFICATION_MAIL_ETAPE
        key = MY_USER_TOKEN
        splashScreenDataSource = SplashScreenLocalDataSourceImpl(
            stockageEnLocalRepository = stockageRepository
        )
        stringSettingConfig = StringSettingConfig(
            settings = settings,
            key = key,
            defaultValue = ""
        )
    }

    @Test
    fun shouldGetToken() = runTest {
        // Given
        every { settings.getString(key, "") }.invokes { -> token }
        every { stringSettingConfig.get() }.invokes { -> Json.encodeToString(token) }
        every { stockageRepository.getSettings() }.invokes { -> settings }

        // When
        val newToken = splashScreenDataSource.getToken()

        // Then
        assertEquals(token, newToken)
    }


    @Test
    fun shouldGetNull() = runTest {
        // Given
        token = ""
        every { settings.getString(key, "") }.invokes { -> token }
        every { stringSettingConfig.get() }.invokes { -> "" }
        every { stockageRepository.getSettings() }.invokes { -> settings }

        // When
        val newToken = splashScreenDataSource.getToken()

        // Then
        assertEquals(token, newToken)
    }

    @Test
    fun shouldGetTokenEmpty() = runTest {
        // Given
        token = ""
        every { settings.getString(key, "") }.invokes { -> token }
        every { stringSettingConfig.get() }.invokes { -> Json.encodeToString(token) }
        every { stockageRepository.getSettings() }.invokes { -> settings }

        // When
        val newToken = splashScreenDataSource.getToken()

        // Then
        assertEquals(token, newToken)
    }

    @Test
    fun shouldGetTokenNull() = runTest {
        // Given
        token = null.toString()
        every { settings.getString(key, "") }.invokes { -> token }
        every { stringSettingConfig.get() }.invokes { -> Json.encodeToString(token) }
        every { stockageRepository.getSettings() }.invokes { -> settings }

        // When
        val newToken = splashScreenDataSource.getToken()

        // Then
        assertEquals(token, newToken)
    }

    @Test
    fun shouldGetEtape() = runTest {
        key = ETAPE_INSCRIPTION
        stringSettingConfig = StringSettingConfig(
            settings = settings,
            key = key,
            defaultValue = ""
        )
        // Given
        every { settings.getString(key, "") }.invokes { -> etape }
        every { stringSettingConfig.get() }.invokes { -> Json.encodeToString(etape) }
        every { stockageRepository.getSettings() }.invokes { -> settings }

        // When
        val newEtape = splashScreenDataSource.getEtape()

        // Then
        assertEquals(etape, newEtape)
    }
}