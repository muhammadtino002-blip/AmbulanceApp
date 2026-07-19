package com.example.ambulanceapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ambulanceapp.R
import com.example.ambulanceapp.ui.theme.ScreenBg
import com.example.ambulanceapp.ui.theme.White
import kotlinx.coroutines.delay

// Splash Screen
@Composable
fun SplashScreen(
    onSplashComplete: () -> Unit = {}
) {
    // Fade-in animation for the whole logo group
    val alphaAnim = remember { Animatable(0f) }
    // Subtle scale pop
    val scaleAnim = remember { Animatable(0.82f) }
    // Continuous slow rotation on the star (very subtle, 0 → 360 over 8s)
    val infiniteTransition = rememberInfiniteTransition(label = "star_rotate")

    LaunchedEffect(Unit) {
        // Entrance animation
        alphaAnim.animateTo(
            targetValue   = 1f,
            animationSpec = tween(durationMillis = 900, easing = EaseOutCubic)
        )
        scaleAnim.animateTo(
            targetValue   = 1f,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        )
        // Hold on screen then navigate away
        delay(2500)
        onSplashComplete()
    }

    Box(
        modifier          = Modifier
            .fillMaxSize()
            .background(White),
        contentAlignment  = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = "loading_screen",
            modifier = Modifier.size(150.dp)
        )
    }

}


// Preview
@Preview(
    name           = "Splash Screen",
    showBackground = true,
    device         = "spec:width=390dp,height=844dp,dpi=430"
)
@Composable
fun SplashScreenPreview() {
    MaterialTheme {
        SplashScreen()
    }
}