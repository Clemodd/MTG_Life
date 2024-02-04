package app.accueil.data.datasource

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import specificitePlateforme.StockageEnLocalRepository
import specificitePlateforme.StringSettingConfig

interface AccueilLocalDataSource {
    fun recupererScore(): Array<Int>
    fun enregistrerScoreByJoueur(newScore: Int, joueur: Int)
    fun restart()
}

class AccueilLocalDataSourceImpl(
    stockageEnLocalRepository: StockageEnLocalRepository,
) : AccueilLocalDataSource {

    private val settings = stockageEnLocalRepository.getSettings()
    private val defaultValue = Json.encodeToString(listOf(20, 20, 20, 20, 20, 20))
    private val keyStockageLocalScore = StringSettingConfig(settings, "Scores", defaultValue)

    override fun recupererScore(): Array<Int> {
        val score = keyStockageLocalScore.get()

        val scoreList: List<Int> = try {
            Json.decodeFromString(score)
        } catch (e: Exception) {
            listOf(20, 20, 20, 20, 20, 20)
        }
        return scoreList.toTypedArray()
    }

    override fun enregistrerScoreByJoueur(newScore: Int, joueur: Int) {
        val score = recupererScore().toMutableList()
        score[joueur] = newScore
        keyStockageLocalScore.set(Json.encodeToString(score))
    }

    override fun restart() {
        keyStockageLocalScore.set(defaultValue)
    }
}