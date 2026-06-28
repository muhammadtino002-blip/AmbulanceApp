package com.example.ambulanceapp.ui.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.NotificationImportant
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Color tokens ───────────────────────────────────────────────────────────────
private val NavyDark    = Color(0xFF1A2847)
private val CardBg      = Color(0xFFFFFFFF)
private val ScreenBg    = Color(0xFFF5F5F5)
private val TextMain    = Color(0xFF1A1A1A)
private val TextSub     = Color(0xFF6B6B6B)
private val NavBarBg    = Color(0xFFFFFFFF)
private val NavSelected = NavyDark
private val NavUnsel    = Color(0xFFAAAAAA)

// ── Data model ─────────────────────────────────────────────────────────────────
data class OrderHistoryItem(
    val type: String,
    val driver: String,
    val dateTime: String,
    val icon: ImageVector
)

// ── Screen ─────────────────────────────────────────────────────────────────────
@Composable
fun HistoryScreen(
    onItemClick: (OrderHistoryItem) -> Unit = {},
    onHomeClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onChatClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val thisMonthItems = listOf(
        OrderHistoryItem(
            type = "Non-Emergency",
            driver = "Maulana Malik",
            dateTime = "27 June 2026 - 12:39",
            icon = Icons.Outlined.MedicalServices
        ),
        OrderHistoryItem(
            type = "Corpse",
            driver = "Supriyadi",
            dateTime = "8 June 2026 - 07:12",
            icon = Icons.Outlined.WarningAmber
        )
    )

    val mayItems = listOf(
        OrderHistoryItem(
            type = "Emergency",
            driver = "Joko Anwar",
            dateTime = "9 May 2026 - 17:02",
            icon = Icons.Outlined.NotificationImportant
        )
    )

    Scaffold(
        containerColor = ScreenBg,
        bottomBar = {
            HistoryBottomNav(
                onHomeClick = onHomeClick,
                onHistoryClick = onHistoryClick,
                onChatClick = onChatClick,
                onProfileClick = onProfileClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // ── Title ──────────────────────────────────────────────────────
            Text(
                text = "Order History",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextMain,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ── This Month section ─────────────────────────────────────────
            SectionLabel("This Month")
            Spacer(modifier = Modifier.height(12.dp))
            thisMonthItems.forEach { item ->
                HistoryCard(item = item, onClick = { onItemClick(item) })
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ── May 2026 section ───────────────────────────────────────────
            SectionLabel("May 2026")
            Spacer(modifier = Modifier.height(12.dp))
            mayItems.forEach { item ->
                HistoryCard(item = item, onClick = { onItemClick(item) })
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// ── Section label ──────────────────────────────────────────────────────────────
@Composable
private fun SectionLabel(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = TextMain
    )
}

// ── History card ───────────────────────────────────────────────────────────────
@Composable
private fun HistoryCard(
    item: OrderHistoryItem,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Icon box
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(60.dp)
                    .background(color = NavyDark, shape = RoundedCornerShape(12.dp))
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.type,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text block
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.type,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMain
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Driver: ${item.driver}",
                    fontSize = 13.sp,
                    color = TextSub
                )
                Text(
                    text = item.dateTime,
                    fontSize = 13.sp,
                    color = TextSub
                )
            }

            // Chevron
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(32.dp)
                    .background(color = NavyDark, shape = RoundedCornerShape(50))
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "Detail",
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

// ── Bottom navigation bar ──────────────────────────────────────────────────────
@Composable
private fun HistoryBottomNav(
    onHomeClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onChatClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Surface(
        color = NavBarBg,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(icon = Icons.Default.Home,          label = "Home",    tint = NavUnsel,    onClick = onHomeClick)
            NavItem(icon = Icons.Default.Schedule,      label = "History", tint = NavSelected, onClick = onHistoryClick, selected = true)
            NavItem(icon = Icons.Default.Chat,          label = "Chat",    tint = NavUnsel,    onClick = onChatClick)
            NavItem(icon = Icons.Default.AccountCircle, label = "Profile", tint = NavUnsel,    onClick = onProfileClick)
        }
    }
}

@Composable
private fun NavItem(
    icon: ImageVector,
    label: String,
    tint: Color,
    onClick: () -> Unit,
    selected: Boolean = false
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(if (selected) 30.dp else 26.dp)
        )
    }
}

// ── Preview ────────────────────────────────────────────────────────────────────
@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun HistoryScreenPreview() {
    MaterialTheme {
        HistoryScreen()
    }
}