package app.accueil.ui

import app.accueil.domain.AccueilRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AccueilViewModel : KoinComponent, ViewModel() {

    private val accueilRepository: AccueilRepository by inject()

    val navigationEvent = MutableStateFlow<AccueilNavigation?>(null)

    private var _score = MutableStateFlow(arrayOf(20, 20, 20, 20, 20, 20))
    val score = _score.asStateFlow()

    var choixaleatoire1 = MutableStateFlow("")
    var choixaleatoire2 = MutableStateFlow("")

    init {
        recupererScore()

        val fond = listOf(
            "mana/foret.png",
            "mana/mort.png",
            "mana/eau.png",
            "mana/montagne.png",
            "mana/plaine.png"
        )

        choixaleatoire1.value = fond.random()
        choixaleatoire2.value = fond.filter { it != choixaleatoire1.value }.random()
    }

    private fun recupererScore() {
        _score.value = accueilRepository.recupererScore()
    }

    fun enregistrerScore(resultat: Int, joueur: Int) {
        val scoreActuel = _score.value[joueur]
        val score = scoreActuel + resultat
        accueilRepository.enregistrerScoreByJoueur(score, joueur)
        recupererScore()
    }

    fun restart() {
        accueilRepository.restart()
        recupererScore()
    }

    fun navigateToParametres() {
        navigationEvent.value = AccueilNavigation.GoToParametres
    }

    fun clearNavigationEvent() {
        navigationEvent.value = null
    }
}