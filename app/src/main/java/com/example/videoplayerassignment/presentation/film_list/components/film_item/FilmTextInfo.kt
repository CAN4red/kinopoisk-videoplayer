package com.example.videoplayerassignment.presentation.film_list.components.film_item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun FilmTextInfo(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(8.dp)) {
        FilmTitleText(text = title)
        Spacer(modifier = Modifier.height(4.dp))
        FilmSubtitleText(subtitle = subtitle)
    }
}

@Composable
private fun FilmTitleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
private fun FilmSubtitleText(subtitle: String) {
    Text(
        text = subtitle,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = LocalContentColor.current.copy(alpha = 0.6f),
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
