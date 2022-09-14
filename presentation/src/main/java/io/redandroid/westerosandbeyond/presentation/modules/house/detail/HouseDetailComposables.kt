package io.redandroid.westerosandbeyond.presentation.modules.house.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailEntry(pair: Pair<String, String>) {
    if (pair.second.isBlank()) return

    Column(
        modifier = Modifier.padding(bottom = 16.dp),
    ) {
        Text(
            text = pair.first,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = pair.second,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}