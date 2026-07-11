package com.example.ambulanceapp.ui.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ===== Warna sesuai desain =====
val DarkNavy = Color(0xFF0C1F3F)
val EmergencyRed = Color(0xFFD32F2F)
val LightGray = Color(0xFFF5F5F5)
val BorderGray = Color(0xFFE0E0E0)
val SelectedBlue = Color(0xFFD6E4FF)

@Composable
fun EmergencyScreen(onOrderSubmitted: () -> Unit, onBack: () -> Boolean) {
    Scaffold(
        topBar = { EmergencyTopBar() },
        bottomBar = { EmergencyBottomBar() },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // ===== Peta (placeholder) =====
            MapSection()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                PatientStatusCard()

                Spacer(modifier = Modifier.height(16.dp))
                LocationCard(
                    icon = Icons.Default.MyLocation,
                    title = "Pickup Location",
                    subtitle = "Current Location\n(Detecting...)",
                    trailingIcon = Icons.Default.Edit
                )

                Spacer(modifier = Modifier.height(12.dp))
                LocationCard(
                    icon = Icons.Default.LocalHospital,
                    title = "Destination",
                    subtitle = "Nearest Emergency Center",
                    trailingText = "2.4 mi"
                )

                Spacer(modifier = Modifier.height(16.dp))
                ConfirmButton()

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "By tapping confirm, your live location and medical profile will be shared with the emergency response team immediately.",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// ===== Top App Bar =====
@Composable
fun EmergencyTopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = DarkNavy
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Emergency",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = DarkNavy
        )
    }
}

// ===== Map Section with SOS Button =====
@Composable
fun MapSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp),
        contentAlignment = Alignment.Center
    ) {
        // Placeholder untuk peta (gunakan Image/MapView asli di implementasi nyata)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xFFB3D9F2))
        )

        // Tombol SOS bulat, sedikit menjorok ke bawah peta
        Box(
            modifier = Modifier
                .offset(y = 60.dp)
                .size(120.dp)
                .clip(CircleShape)
                .background(EmergencyRed),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.NotificationsActive,
                    contentDescription = "SOS",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "TAP FOR\nHELP",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// ===== Patient Status Card =====
@Composable
fun PatientStatusCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-40).dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(DarkNavy),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Assignment,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Patient Status",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = DarkNavy
                    )
                    Text(
                        text = "Describe the urgency for the crew",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Baris pilihan pertama
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StatusChip(text = "Chest Pain", selected = false)
                StatusChip(text = "Difficulty Breathing", selected = true)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Baris pilihan kedua
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StatusChip(text = "Injury", selected = false)
                StatusChip(text = "Other", selected = false)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Additional Details (Optional)",
                fontSize = 13.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Briefly describe the condition or symptoms...",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

@Composable
fun StatusChip(text: String, selected: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(if (selected) SelectedBlue else Color.White)
            .border(
                width = 1.dp,
                color = if (selected) DarkNavy else BorderGray,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (selected) DarkNavy else Color.DarkGray,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

// ===== Location Card (Pickup / Destination) =====
@Composable
fun LocationCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    trailingIcon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    trailingText: String? = null
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = EmergencyRed,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Text(
                    text = subtitle,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )
            }
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = DarkNavy,
                    modifier = Modifier.size(20.dp)
                )
            }
            if (trailingText != null) {
                Text(
                    text = trailingText,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

// ===== Confirm Button =====
@Composable
fun ConfirmButton() {
    Button(
        onClick = { /* tanpa logika */ },
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = DarkNavy),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Wifi,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Confirm & Request Help",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// ===== Bottom Navigation Bar =====
@Composable
fun EmergencyBottomBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.History, contentDescription = "History") },
            label = { Text("History") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Chat, contentDescription = "Messages") },
            label = { Text("Messages") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmergencyScreenPreview() {
    MaterialTheme {
        EmergencyScreen()
    }
}