package app.parametres.ui

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent

class ParametresViewModel : KoinComponent, ViewModel() {

    val navigationEvent = MutableStateFlow<ParametresNavigation?>(null)

    fun retour() {
        navigationEvent.value = ParametresNavigation.GoBack
    }

    fun clearNavigationEvent() {
        navigationEvent.value = null
    }
}