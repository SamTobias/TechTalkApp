package br.com.samueltobias.techtalkapp.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.samueltobias.techtalkapp.ui.theme.PantoneYellow

@Composable
fun Chip(text: String) {
    Surface(
        modifier = Modifier
            .padding(all = 5.dp)
            .defaultMinSize(minWidth = 30.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = PantoneYellow
        ),
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = text,
                color = PantoneYellow,
                fontSize = 12.sp,
                modifier = Modifier.padding(3.dp),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview
@Composable
fun ChipPreview() {
    Chip(text = "Chip")
}