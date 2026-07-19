package com.example.ambulanceapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambulanceapp.R
import com.example.ambulanceapp.ui.theme.Montserrat
import com.example.ambulanceapp.ui.theme.NavyPrimary
import com.example.ambulanceapp.ui.theme.TextPrimary
import com.example.ambulanceapp.ui.theme.TextSecondary
import com.example.ambulanceapp.ui.theme.CardBg
import com.example.ambulanceapp.ui.theme.White
import com.example.ambulanceapp.ui.theme.Red
import com.example.ambulanceapp.ui.theme.RedDark
import com.example.ambulanceapp.ui.theme.RedLight
import com.example.ambulanceapp.ui.theme.OffWhite
import com.example.ambulanceapp.ui.theme.BorderColor
import com.example.ambulanceapp.ui.theme.ScreenBg

// Data
private val statusOptions = listOf("Chest Pain", "Difficulty Breathing", "Injury", "Other")

// Emergency Screen
@Composable
fun EmergencyScreen(onBack: () -> Unit = {}) {

    val selectedStatuses  = remember { mutableStateListOf("Difficulty Breathing") }
    var additionalDetails by remember { mutableStateOf("") }

    // Pulse animation for TAP FOR HELP button
    val pulseAnim = rememberInfiniteTransition(label = "pulse")
    val pulseScale by pulseAnim.animateFloat(
        initialValue  = 1f,
        targetValue   = 1.08f,
        animationSpec = infiniteRepeatable(
            animation  = tween(700, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    Box(modifier = Modifier.fillMaxSize().background(OffWhite)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ScreenBg)
                .verticalScroll(rememberScrollState())
        ) {

            // Map + Header + TAP FOR HELP
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            ) {
                // Map placeholder
                MapPlaceholderView()

                // Top bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .statusBarsPadding(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector        = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint               = TextPrimary,
                        modifier           = Modifier
                            .size(16.dp)
                            .clickable { onBack() },
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text       = "Emergency",
                        fontFamily = Montserrat,
                        fontSize   = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color      = TextPrimary
                    )
                    Spacer(Modifier.weight(1f))
                    Spacer(Modifier.size(36.dp))
                }

                // Location pin on map
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .align(Alignment.Center)
                        .offset(y = (-20).dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Red),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .border(2.dp, White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Outlined.MyLocation,
                                contentDescription = null,
                                tint     = White,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }

                // TAP FOR HELP button — sits at the bottom of the map box, overlapping
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = 50.dp) // half below map edge
                        .scale(pulseScale)
                        .shadow(12.dp, CircleShape)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(RedLight, RedDark)
                            )
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication        = null
                        ) { /* TODO: trigger emergency */ },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // Alarm / siren icon drawn on canvas
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text       = "TAP FOR\nHELP",
                            fontSize   = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color      = White,
                            textAlign  = TextAlign.Center,
                            lineHeight = 17.sp
                        )
                    }
                }
            }

            // Space consumed by the overlapping TAP FOR HELP button
            Spacer(Modifier.height(66.dp))

            // Patient Status Card
            Card(
                modifier  = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape     = RoundedCornerShape(16.dp),
                colors    = CardDefaults.cardColors(containerColor = CardBg),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {

                    // Header row
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(NavyPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Filled.MedicalServices,
                                contentDescription = null,
                                tint     = White,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                "Patient Status",
                                fontFamily = Montserrat,
                                fontSize   = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color      = TextPrimary
                            )
                            Text(
                                "Describe the urgency for the crew",
                                fontFamily = Montserrat,
                                fontSize = 12.sp,
                                color    = TextSecondary
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Status chips — wrap into rows of 2
                    val chunked = statusOptions.chunked(2)
                    chunked.forEach { rowItems ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier              = Modifier.padding(bottom = 10.dp)
                        ) {
                            rowItems.forEach { status ->
                                val isSelected = selectedStatuses.contains(status)
                                StatusChip(
                                    label      = status,
                                    isSelected = isSelected,
                                    onClick    = {
                                        if (isSelected) selectedStatuses.remove(status)
                                        else selectedStatuses.add(status)
                                    }
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(4.dp))

                    Text(
                        "Additional Details (Optional)",
                        fontFamily = Montserrat,
                        fontSize = 12.sp,
                        color    = TextSecondary
                    )
                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value         = additionalDetails,
                        onValueChange = { additionalDetails = it },
                        placeholder   = {
                            Text(
                                "Briefly describe the condition or symptoms...",
                                fontFamily = Montserrat,
                                fontSize   = 13.sp,
                                color      = TextSecondary,
                                lineHeight = 18.sp
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 90.dp),
                        shape    = RoundedCornerShape(10.dp),
                        colors   = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor    = BorderColor,
                            focusedBorderColor      = NavyPrimary,
                            unfocusedContainerColor = White,
                            focusedContainerColor   = White
                        ),
                        minLines = 3
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            // Pickup Location Card
            LocationInfoCard(
                icon        = Icons.Outlined.AddLocationAlt,
                iconBg      = Color(0xFFEFF2FF),
                iconTint    = NavyPrimary,
                topLabel    = "Pickup Location",
                bottomLabel = "Current Location\n(Detecting...)",
                trailing    = {
                    Icon(
                        Icons.Outlined.Edit,
                        contentDescription = "Edit",
                        tint     = NavyPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            )

            Spacer(Modifier.height(2.dp))

            // Destination Card
            LocationInfoCard(
                icon        = Icons.Outlined.LocalHospital,
                iconBg      = Color(0xFFFFE8E8),
                iconTint    = Red,
                topLabel    = "Destination",
                bottomLabel = "Nearest Emergency Center",
                trailing    = {
                    Text(
                        "2.4 mi",
                        fontFamily = Montserrat,
                        fontSize   = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color      = TextPrimary
                    )
                }
            )

            Spacer(Modifier.height(20.dp))

            // Confirm Button
            Button(
                onClick  = { /* TODO: confirm & request */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(56.dp),
                shape    = RoundedCornerShape(28.dp),
                colors   = ButtonDefaults.buttonColors(
                    containerColor = NavyPrimary,
                    contentColor   = White
                )
            ) {
                Icon(
                    Icons.Outlined.LocationOn,
                    contentDescription = null,
                    modifier           = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    "Confirm & Request Help",
                    fontFamily = Montserrat,
                    fontSize   = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(14.dp))

            // Disclaimer
            Text(
                text      = "By tapping confirm, your live location\nand medical profile will be shared with\nthe emergency response team\nimmediately.",
                fontFamily = Montserrat,
                fontSize  = 11.sp,
                color     = TextSecondary,
                textAlign = TextAlign.Center,
                modifier  = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            Spacer(Modifier.height(28.dp))
        }
    }
}

// Status Chip
@Composable
private fun StatusChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) NavyPrimary else White)
            .border(
                width = 1.5.dp,
                color = if (isSelected) NavyPrimary else BorderColor,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 9.dp)
    ) {
        Text(
            text       = label,
            fontFamily = Montserrat,
            fontSize   = 13.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color      = if (isSelected) White else TextPrimary
        )
    }
}

// Location Info Card
@Composable
private fun LocationInfoCard(
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    topLabel: String,
    bottomLabel: String,
    trailing: @Composable () -> Unit
) {
    Card(
        modifier  = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape     = RoundedCornerShape(14.dp),
        colors    = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier         = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(22.dp))
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(topLabel, fontFamily = Montserrat, fontSize = 12.sp, color = TextSecondary)
                Text(
                    bottomLabel,
                    fontFamily = Montserrat,
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = TextPrimary,
                    lineHeight = 20.sp
                )
            }
            trailing()
        }
    }
}

// Map Placeholder
@Composable
private fun MapPlaceholderView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8EDF3))
    ) {
        Image(
            painter = painterResource(R.drawable.maps),
            contentDescription = "Maps",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop

        )
    }
}

// Preview
@Preview(
    name           = "Emergency Screen",
    showBackground = true,
    device         = "spec:width=390dp,height=844dp,dpi=430"
)
@Composable
fun EmergencyScreenPreview() {
    MaterialTheme { EmergencyScreen() }
}