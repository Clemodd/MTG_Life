import android.content.Context
import android.os.Build
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import fr.togethim.mtg_life.AndroidAppContext

actual fun provideSettings(): Settings {
    val context = AndroidAppContext.applicationContext
    val sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    return SharedPreferencesSettings(sharedPreferences)
}

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()