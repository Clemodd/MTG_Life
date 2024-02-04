package commun.enAttentMiseAJourAndroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun monImage(image: String, taille: Int = 200, description: String = "Description à ajouter", modifier: Modifier = Modifier, isToCrop: Boolean = false) {
    if (LocalInspectionMode.current) {
        Box(
            modifier = Modifier
                .size(taille.dp)
                .clip(CircleShape)
                .background(Color.Blue)
        )
    } else {
        Image(
            painterResource(image),
            description,
            modifier = Modifier
                .size(taille.dp)
                .then(modifier),
            contentScale = if (isToCrop) ContentScale.Crop else ContentScale.Fit
        )
    }
}