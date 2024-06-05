package com.example.composeexampleapp.views

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmationDialog(
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Confirmation") },
        text = { Text(text = message, fontSize = 20.sp) },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(text = "Yes",fontSize = 20.sp)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancel",fontSize = 20.sp)
            }
        }
    )
}