package commun.utils.mapper

import domain.constante.ACCUEIL_ETAPE
import domain.constante.CHOIX_COULEUR_ETAPE
import domain.constante.VERIFICATION_MAIL_ETAPE
import domain.enum.EtapeInscriptionEnum
import kotlin.test.Test
import kotlin.test.assertEquals

class StringToEtapeInscriptionEnumMapperTest {

    private lateinit var stringAConvertir: String

    @Test
    fun shouldConvertStringToAccueil() {
        // Given
        stringAConvertir = ACCUEIL_ETAPE

        // When
        val stringConvertiEnEnum = stringAConvertir.toEtapeInscriptionEnum()

        // Then
        assertEquals(
            EtapeInscriptionEnum.ACCUEIL,
            stringConvertiEnEnum
        )
    }

    @Test
    fun shouldConvertStringToChoixCouleur() {
        // Given
        stringAConvertir = CHOIX_COULEUR_ETAPE

        // When
        val stringConvertiEnEnum = stringAConvertir.toEtapeInscriptionEnum()

        // Then
        assertEquals(
            EtapeInscriptionEnum.CHOIX_COULEUR,
            stringConvertiEnEnum
        )
    }

    @Test
    fun shouldConvertStringToVerificationMail() {
        // Given
        stringAConvertir = VERIFICATION_MAIL_ETAPE

        // When
        val stringConvertiEnEnum = stringAConvertir.toEtapeInscriptionEnum()

        // Then
        assertEquals(
            EtapeInscriptionEnum.VERIFICATION_MAIL,
            stringConvertiEnEnum
        )
    }

    @Test
    fun shouldConvertStringToNone() {
        // Given
        stringAConvertir = "XXXX"

        // When
        val stringConvertiEnEnum = stringAConvertir.toEtapeInscriptionEnum()

        // Then
        assertEquals(
            EtapeInscriptionEnum.NONE,
            stringConvertiEnEnum
        )
    }
}