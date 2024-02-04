package commun.utils.mapper

import domain.constante.ACCUEIL_ETAPE
import domain.constante.CHOIX_COULEUR_ETAPE
import domain.constante.VERIFICATION_MAIL_ETAPE
import domain.enum.EtapeInscriptionEnum

fun String.toEtapeInscriptionEnum(): EtapeInscriptionEnum {
    return when (this) {
        ACCUEIL_ETAPE -> EtapeInscriptionEnum.ACCUEIL
        VERIFICATION_MAIL_ETAPE -> EtapeInscriptionEnum.VERIFICATION_MAIL
        CHOIX_COULEUR_ETAPE -> EtapeInscriptionEnum.CHOIX_COULEUR
        else -> EtapeInscriptionEnum.NONE
    }
}