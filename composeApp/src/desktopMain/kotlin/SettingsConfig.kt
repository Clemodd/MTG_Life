import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

actual fun provideSettings(): Settings {
    val preferences = Preferences.userRoot()
    return PreferencesSettings(preferences)
}

class JVMPlatform: Platform {
    override val name: String = "Desktop ${System.getProperty("java.version")}"
}
actual fun getPlatform(): Platform = JVMPlatform()