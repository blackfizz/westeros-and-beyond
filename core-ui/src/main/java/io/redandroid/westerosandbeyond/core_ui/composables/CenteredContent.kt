package io.redandroid.westerosandbeyond.core_ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CenteredContent(modifier: Modifier = Modifier.fillMaxSize(), compose: @Composable () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        compose()
    }
}