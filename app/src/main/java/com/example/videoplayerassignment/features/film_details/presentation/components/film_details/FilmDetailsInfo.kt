package com.example.videoplayerassignment.features.film_details.presentation.components.film_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.videoplayerassignment.R
import com.example.videoplayerassignment.features.film_details.domain.model.FilmDetails
import com.example.videoplayerassignment.features.film_details.presentation.components.FilmDetailsUtils.joinByCommas

@Composable
fun FilmDetailsInfo(
    filmDetails: FilmDetails,
    modifier: Modifier = Modifier
) {
    var expandedModeState by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = filmDetails.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight(900),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = filmDetails.description,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight(500),
            maxLines = getMaxLines(expandedModeState),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .clickable(
                    onClick = { expandedModeState = !expandedModeState },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                )
        )

        Spacer(modifier = Modifier.height(24.dp))

        DisplayInfo(
            label = stringResource(R.string.countries_label),
            value = filmDetails.countries.joinByCommas(),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        DisplayInfo(
            label = stringResource(R.string.genres_label),
            value = filmDetails.genres.joinByCommas(),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        DisplayInfo(
            label = stringResource(R.string.year_label),
            value = filmDetails.year,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
private fun DisplayInfo(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) { append(label) }
            append(value)
        },
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight(500),
        maxLines = 1,
        modifier = modifier
    )
}

private fun getMaxLines(expandedMode: Boolean): Int {
    return if (expandedMode) {
        Int.MAX_VALUE
    } else {
        3
    }
}

@Preview
@Composable
private fun FilmDetailsInfoPreview() {
    FilmDetailsInfo(
        FilmDetails(
            id = 1,
            name = "Битлджус Битлджус",
            year = "2024",
            description = "После смерти отца Лидия со своей дочерью Астрид и мачехой Делией " +
                    "возвращаются в старый дом в городке Уинтер-Ривер. Когда Астрид обнаруживает " +
                    "на чердаке тот самый макет города, Лидии приходится рассказать ей о Битлджусе " +
                    "— озорном и крайне неприятном призраке, чье имя ни в коем случае нельзя " +
                    "называть три раза. Но любопытство девочки берет верх — она открывает портал " +
                    "в загробную жизнь. Битлджус начинает снова терроризировать всех живых.",
            countries = listOf("США"),
            genres = listOf("фэнтези", "комедия"),
            videos = emptyList()
        )
    )
}