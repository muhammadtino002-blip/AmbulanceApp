package com.example.ambulanceapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ambulanceapp.ui.theme.Montserrat

// Design Tokens
private val White       = Color(0xFFFFFFFF)
private val TextPrimary = Color(0xFF111111)
private val NavyPrimary = Color(0xFF1A3A6B)

// Public API
/**
 * Drop-in popup that appears when a page is still being optimized.
 *
 * Usage:
 *   var showDialog by remember { mutableStateOf(true) }
 *   if (showDialog) {
 *       UnderOptimizationDialog(onDismiss = { showDialog = false })
 *   }
 *
 * @param onDismiss called when the user taps "OK" or outside the dialog.
 * @param dismissible whether tapping outside dismisses the dialog (default true).
 */

@Composable
fun UnderOptimizationDialog(
    onDismiss: () -> Unit,
    dismissible: Boolean = true
) {
    Dialog(
        onDismissRequest = { if (dismissible) onDismiss() },
        properties       = DialogProperties(
            dismissOnBackPress      = dismissible,
            dismissOnClickOutside   = dismissible,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier         = Modifier
                .fillMaxWidth(0.85f)
                .background(White, RoundedCornerShape(20.dp))
                .padding(horizontal = 28.dp, vertical = 36.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                // Warning icon
                Icon(
                    imageVector = Icons.Outlined.Warning,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(100.dp)
                )

                Spacer(Modifier.height(24.dp))

                // Message
                Text(
                    text       = "Halaman ini\nmasih dalam\npengoptimalan",
                    fontSize   = 20.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color      = TextPrimary,
                    textAlign  = TextAlign.Center,
                    lineHeight = 28.sp
                )

                Spacer(Modifier.height(28.dp))

                // OK button
                Button(
                    onClick  = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape    = RoundedCornerShape(24.dp),
                    colors   = ButtonDefaults.buttonColors(
                        containerColor = NavyPrimary,
                        contentColor   = White
                    )
                ) {
                    Text(
                        text       = "OK",
                        fontSize   = 15.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

// Preview
@Preview(showBackground = true, backgroundColor = 0xFF888888)
@Composable
private fun UnderOptimizationDialogPreview() {
    MaterialTheme {
        UnderOptimizationDialog(onDismiss = {})
    }
}