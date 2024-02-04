import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import decompose.RootNavigationComponent

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "MTG_Life") {
        val rootComponent = remember {
            RootNavigationComponent(
                DefaultComponentContext(LifecycleRegistry())
            )
        }

        // Utilisation du RootComponent pour rendre l'UI
        CommonMainActivity(rootComponent)
    }
}