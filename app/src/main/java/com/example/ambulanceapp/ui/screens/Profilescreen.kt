package com.example.ambulanceapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ambulanceapp.ui.theme.FieldBg
import com.example.ambulanceapp.ui.theme.Montserrat
import com.example.ambulanceapp.ui.theme.NavyPrimary
import com.example.ambulanceapp.ui.theme.White
import com.example.ambulanceapp.ui.theme.TextPrimary
import com.example.ambulanceapp.ui.theme.TextSecondary
import com.example.ambulanceapp.ui.theme.Red
import com.example.ambulanceapp.ui.theme.HintGray
import com.example.ambulanceapp.ui.theme.ScreenBg

// Menu Item Model
private data class ProfileMenuItem(
    val icon: ImageVector,
    val label: String,
    val isDestructive: Boolean = false,
    val onClick: () -> Unit = {}
)

// Profile Screen
@Composable
fun ProfileScreen(
    userName: String    = "Vania Angelia",
    userEmail: String   = "vaniaangelia12@gmail.com",
    userPhotoUrl: String? = null,        // pass a real URL or drawable res in production
    onEditProfile: () -> Unit = {},
    onSettings: () -> Unit    = {},
    onLocation: () -> Unit    = {},
    onAboutUs: () -> Unit     = {},
    onLogOut: () -> Unit      = {}
) {
    val menuItems = listOf(
        ProfileMenuItem(Icons.Outlined.Settings,  "Settings",  onClick = onSettings),
        ProfileMenuItem(Icons.Outlined.LocationOn,"Location",  onClick = onLocation),
        ProfileMenuItem(Icons.Outlined.Info,      "About Us",  onClick = onAboutUs),
        ProfileMenuItem(
            icon          = Icons.AutoMirrored.Outlined.Logout,
            label         = "Log Out",
            isDestructive = true,
            onClick       = onLogOut
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBg)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .padding(horizontal = 24.dp)
    ) {

        Spacer(Modifier.height(20.dp))

        // Title
        Text(
            text       = "Profile",
            fontSize   = 20.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            color      = TextPrimary,
            modifier   = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(32.dp))

        // User Card
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier          = Modifier.fillMaxWidth()
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, FieldBg, CircleShape)
                    .background(Color(0xFFE5E7EB)),
                contentAlignment = Alignment.Center
            ) {
                if (userPhotoUrl != null) {
                    AsyncImage(
                        model              = userPhotoUrl,
                        contentDescription = "Profile photo",
                        contentScale       = ContentScale.Crop,
                        modifier           = Modifier.fillMaxSize()
                    )
                } else {
                    // Placeholder initials
                    Text(
                        text       = userName.take(1).uppercase(),
                        fontSize   = 28.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        color      = NavyPrimary
                    )
                }
            }

            Spacer(Modifier.width(20.dp))

            // Name + email + edit button
            Column {
                Text(
                    text       = userName,
                    fontSize   = 18.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color      = TextPrimary
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text     = userEmail,
                    fontFamily = Montserrat,
                    fontSize = 13.sp,
                    color    = TextSecondary
                )
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick  = onEditProfile,
                    shape    = RoundedCornerShape(24.dp),
                    colors   = ButtonDefaults.buttonColors(
                        containerColor = NavyPrimary,
                        contentColor   = White
                    ),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
                ) {
                    Text(
                        text       = "Edit Profile",
                        fontSize   = 13.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(Modifier.height(36.dp))

        // Menu Items
        menuItems.forEachIndexed { index, item ->
            MenuRow(item = item)
            // Divider between items (not after the last one)
            if (index < menuItems.lastIndex) {
                HorizontalDivider(
                    color     = HintGray,
                    thickness = 1.dp,
                    modifier  = Modifier.padding(start = 44.dp)  // indent past icon
                )
            }
        }

        Spacer(Modifier.height(32.dp))
    }
}

// Menu Row
@Composable
private fun MenuRow(item: ProfileMenuItem) {
    val iconColor  = if (item.isDestructive) Red else TextPrimary
    val labelColor = if (item.isDestructive) Red else TextPrimary

    Row(
        modifier          = Modifier
            .fillMaxWidth()
            .clickable { item.onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector        = item.icon,
            contentDescription = item.label,
            tint               = iconColor,
            modifier           = Modifier.size(22.dp)
        )

        Spacer(Modifier.width(16.dp))

        Text(
            text       = item.label,
            fontSize   = 15.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium,
            color      = labelColor,
            modifier   = Modifier.weight(1f)
        )

        Icon(
            imageVector        = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint               = TextSecondary,
            modifier           = Modifier.size(20.dp)
        )
    }
}

// Preview
@Preview(
    name           = "Profile Screen",
    showBackground = true,
    device         = "spec:width=390dp,height=844dp,dpi=430"
)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme { ProfileScreen() }
}