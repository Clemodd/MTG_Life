package fr.togethim.mtg_life

import CommonMainActivity
import MyApplicationTheme
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import app.accueil.ui.AccueilPreview
import app.accueil.ui.composable.MenuComposable
import com.arkivanov.decompose.retainedComponent
import decompose.RootNavigationComponent

@OptIn(com.arkivanov.decompose.ExperimentalDecomposeApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidAppContext.applicationContext = this
        // Passer à decompose les spécificités Android
        val root = retainedComponent { componentContext ->
            RootNavigationComponent(componentContext)
        }

        setContent {
            CommonMainActivity(root)
        }
    }
}

object AndroidAppContext {
    lateinit var applicationContext: Context
}

@LightDarkPreviews
@Composable
fun PreviewComposable() {
    // Composables à afficher
    MyApplicationTheme() {
    }
}