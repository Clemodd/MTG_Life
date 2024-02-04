package app.accueil.domain

interface AccueilRepository {
    fun recupererScore(): Array<Int>
    fun enregistrerScoreByJoueur(score: Int, joueur: Int)
    fun restart()
}