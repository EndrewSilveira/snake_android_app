package com.endrew.snake.ui.screen.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DirectionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    onClicked: () -> Unit,
) {
    Button(
        modifier = modifier
            .size(64.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClicked
    ) {
        Icon(icon, contentDescription)
    }
}