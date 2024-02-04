package fr.togethim.mtg_life

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "small font",
    group = "font scales",
    fontScale = 0.5f
)
@Preview(
    name = "large font",
    group = "font scales",
    fontScale = 1.5f
)
annotation class FontScalePreviews

@Preview(
    name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true
)
@Preview(
    name = "Dark Mode",showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
annotation class LightDarkPreviews