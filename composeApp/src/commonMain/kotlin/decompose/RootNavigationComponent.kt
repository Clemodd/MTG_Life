package decompose

import app.accueil.ui.AccueilViewModel
import app.parametres.ui.ParametresViewModel
import app.splashScreen.ui.SplashScreenViewModel
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceCurrent
import kotlinx.serialization.Serializable

class RootNavigationComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val router = StackNavigation<Configuration>()

    val childStack = childStack(
        source = router,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.SplashScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        configuration: Configuration,
        contexte: ComponentContext,
    ): Child =
        when (configuration) {
            is Configuration.Accueil -> Child.Accueil(AccueilViewModel())
            is Configuration.SplashScreen -> Child.SplashScreen(SplashScreenViewModel())
            is Configuration.Parametres -> Child.Parametres(ParametresViewModel())
            is Configuration.GoBack -> Child.GoBack
        }

    fun goBack() {
        router.pop()
    }
    fun navigateTo(configuration: Configuration) {
        router.replaceCurrent(configuration)
    }
    @OptIn(ExperimentalDecomposeApi::class)
    fun keepHistoriqueAndNavigateTo(configuration: Configuration) {
        router.pushNew(configuration)
    }

    sealed class Child {
        data class Accueil(val viewModel: AccueilViewModel) : Child()
        data class SplashScreen(val viewModel: SplashScreenViewModel) : Child()
        data class Parametres(val viewModel: ParametresViewModel) : Child()
        data object GoBack : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object SplashScreen : Configuration()

        @Serializable
        data object Accueil : Configuration()

        @Serializable
        data object Parametres : Configuration()

        @Serializable
        data object GoBack : Configuration()
    }
}