package app.splashScreen.ui

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    val navigationEvent = MutableStateFlow<SplashScreenNavigation?>(null)

    init {
        chargerApplication()
    }

    private fun chargerApplication() {
        viewModelScope.launch {
            navigationEvent.value = SplashScreenNavigation.GoToAccueil
        }
    }

    fun clearNavigationEvent() {
        navigationEvent.value = null
    }
}