import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults
import platform.UIKit.UIDevice
import kotlin.native.Platform

actual fun provideSettings(): Settings {
    val userDefaults = NSUserDefaults(suiteName = "fr.togethim.mtg_life")
    return NSUserDefaultsSettings(userDefaults)
}

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}
actual fun getPlatform(): Platform = IOSPlatform()