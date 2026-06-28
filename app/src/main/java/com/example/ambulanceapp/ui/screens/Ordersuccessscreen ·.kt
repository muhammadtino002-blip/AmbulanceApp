package com.example.ambulanceapp.ui.screens



import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
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

// ── Color tokens ───────────────────────────────────────────────────────────────
private val NavyDark = Color(0xFF1A2847)
private val TextMain = Color(0xFF1A1A1A)
private val TextSub  = Color(0xFF5A5A5A)

// ── Screen ─────────────────────────────────────────────────────────────────────
@Composable
fun OrderSuccessScreen(
    onBackToDashboardClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ── Center content (vertically centered slightly above mid) ─────
            Spacer(modifier = Modifier.weight(0.45f))

            // ── Checkmark circle ───────────────────────────────────────────
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(96.dp)
                    .border(
                        width = 3.dp,
                        color = NavyDark,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = "Order confirmed",
                    tint = NavyDark,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── Headline ───────────────────────────────────────────────────
            Text(
                text = "Order Successfully\nConfirmed",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextMain,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── Body copy ──────────────────────────────────────────────────
            Text(
                text = "Your order confirmation has been shipped. " +
                        "We will notify you once the fleet begins moving " +
                        "to the pickup location.",
                fontSize = 14.sp,
                color = TextSub,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.weight(0.55f))

            // ── Back To Dashboard button ───────────────────────────────────
            Button(
                onClick = onBackToDashboardClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = NavyDark)
            ) {
                Text(
                    text = "Back To Dashboard",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// ── Preview ────────────────────────────────────────────────────────────────────
@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OrderSuccessScreenPreview() {
    MaterialTheme {
        OrderSuccessScreen()
    }
}