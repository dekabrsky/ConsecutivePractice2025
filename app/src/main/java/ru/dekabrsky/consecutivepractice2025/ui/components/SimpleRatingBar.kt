package ru.dekabrsky.consecutivepractice2025.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.dekabrsky.consecutivepractice2025.R

@Composable
fun RatingBar(
    rating: Float,
    maxRating: Int = 5,
    onRatingChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        for (i in 1..maxRating) {
            IconButton(onClick = { onRatingChanged(i.toFloat()) }) {
                Icon(
                    painterResource(id = if (i <= rating) R.drawable.star else R.drawable.star_border),
                    null
                )
            }
        }
    }
}