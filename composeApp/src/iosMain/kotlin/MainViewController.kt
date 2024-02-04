import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import decompose.RootNavigationComponent

fun MainViewController() = ComposeUIViewController {

    val root = remember {
        RootNavigationComponent(
            DefaultComponentContext(LifecycleRegistry())
        )
    }

    CommonMainActivity(root)
}