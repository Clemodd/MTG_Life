package commun.composable.popinalertdialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PopinAlertDialogPreview() {
    PopinAlertDialogComposable("logo", "tttsetse", "", "") {}
}
@Composable
fun PopinAlertDialogComposable(
    titre: String,
    description: String,
    confirmationTexte: String,
    annulerTexte: String,
    fermerAlertDialog: () -> Unit,
) {
    Surface {
        AlertDialog(
            title = { Text(titre) },
            text = {
                Column(
                    modifier = Modifier
                        .aspectRatio(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(description)
                    //ChargementMotionComposable()
                }
            },
            onDismissRequest = {},
            shape = shapes.medium,
            confirmButton = {
                Surface {
                if (confirmationTexte.isNotEmpty()) {
                    TextButton(onClick = fermerAlertDialog) {
                        Text(text = confirmationTexte)
                    }
                }
                }
            },
            dismissButton = {
                TextButton(onClick = fermerAlertDialog) {
                    Text(text = annulerTexte)
                }
            },
            modifier = Modifier
                .defaultMinSize(300.dp)
                .border(0.dp, Color.Transparent, RoundedCornerShape(0))
        )
    }
}