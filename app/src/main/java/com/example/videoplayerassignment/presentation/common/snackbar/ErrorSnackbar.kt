package com.example.videoplayerassignment.presentation.common.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorSnackbar(
    message: String,
    modifier: Modifier = Modifier
) {
    Snackbar(
        actionOnNewLine = true,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 12.dp)
    ) {
        Text(
            text = message
        )
    }
}

@Preview
@Composable
private fun ErrorSnackbarPreview() {
    ErrorSnackbar(message = "lol")
}