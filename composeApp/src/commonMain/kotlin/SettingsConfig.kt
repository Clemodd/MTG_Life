import com.russhwolf.settings.Settings

// Enregistrement en local
expect fun provideSettings(): Settings

// Récupérer la plateforme
expect fun getPlatform(): Platform
interface Platform {
    val name: String
}