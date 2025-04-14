package ru.urfu.feature.uikit.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SimpleAppBar(
    title: String,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(
                content = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                },
                onClick = { onBackPressed.invoke() }
            )
        }
    )
}