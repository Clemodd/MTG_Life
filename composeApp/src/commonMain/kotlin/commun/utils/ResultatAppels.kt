package commun.utils

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

sealed class ResultatAppels<T> {
    data class Succes<T>(val objet: T) : ResultatAppels<T>()
    data class Erreur<T>(val status: HttpStatusCode, val message: String, val code: Int) : ResultatAppels<T>()
}

suspend inline fun <reified T> genererSuccesOuErreur(response: HttpResponse, code: Int): ResultatAppels<T> {
    return if (response.status.isSuccess())
        ResultatAppels.Succes(response.body())
    else {
        ResultatAppels.Erreur(response.status, response.body(), code)
    }
}

fun <T> isSuccesOuErreur(resultat: ResultatAppels<T>, succes: (T) -> Unit, erreur: (ResultatAppels.Erreur<T>) -> Unit) {
    when (resultat) {
        is ResultatAppels.Succes -> succes(resultat.objet)
        is ResultatAppels.Erreur -> erreur(resultat)
    }
}