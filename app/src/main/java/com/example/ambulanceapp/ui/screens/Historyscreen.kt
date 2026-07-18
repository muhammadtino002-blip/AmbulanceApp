package com.example.ambulanceapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.AirlineSeatFlat
import androidx.compose.material.icons.outlined.Emergency
import androidx.compose.material.icons.outlined.MedicalServices
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
import com.example.ambulanceapp.data.AmbulanceOrder
import com.example.ambulanceapp.data.sampleOrders
import com.example.ambulanceapp.ui.theme.Montserrat

// Color tokens
private val NavyDark      = Color(0xFF122850)
private val TextPrimary   = Color(0xFF111827)
private val TextSecondary = Color(0xFF6B7280)
private val CardBg        = Color(0xFFFFFFFF)

// Data model
data class OrderHistoryItem(
    val orderId: String,
    val type: String,
    val driver: String,
    val dateTime: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

// Screen
@Composable
fun HistoryScreen( onOrderClick: (AmbulanceOrder) -> Unit = {}) {

    val thisMonthItems = listOf(
        OrderHistoryItem(
            orderId  = "#AMB-0042",
            type = "Non-Emergency",
            driver = "Maulana Malik",
            dateTime = "27 June 2026 - 09:30",
            icon = Icons.Outlined.MedicalServices,
            onClick = {}
        ),
        OrderHistoryItem(
            orderId  = "#AMB-0039",
            type = "Corpse",
            driver = "Supriyadi",
            dateTime = "8 June 2026 - 11:45",
            icon = Icons.Outlined.AirlineSeatFlat,
            onClick = {}
        )
    )

    val mayItems = listOf(
        OrderHistoryItem(
            orderId  = "#AMB-0038",
            type = "Emergency",
            driver = "Joko Anwar",
            dateTime = "9 May 2026 - 22:05",
            icon = Icons.Outlined.Emergency,
            onClick = {}
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .statusBarsPadding(),
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // Title
        Text(
            text = "Order History",
            fontFamily = Montserrat,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // This Month section
        SectionLabel("This Month")
        Spacer(modifier = Modifier.height(12.dp))
        thisMonthItems.forEach { item ->
            HistoryCard(
                item = item,
                onClick = {
                    val order = sampleOrders.find { it.id == item.orderId }
                    if (order != null) onOrderClick(order)
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // May 2026 section
        SectionLabel("May 2026")
        Spacer(modifier = Modifier.height(12.dp))
        mayItems.forEach { item ->
            HistoryCard(
                item = item,
                onClick = {
                    val order = sampleOrders.find { it.id == item.orderId }
                    if (order != null) onOrderClick(order)
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

// Section label
@Composable
private fun SectionLabel(title: String) {
    Text(
        text = title,
        fontFamily = Montserrat,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = TextPrimary
    )
}

// History card
@Composable
private fun HistoryCard(
    item: OrderHistoryItem,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
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
                    fontFamily = Montserrat,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Driver: ${item.driver}",
                    fontFamily = Montserrat,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
                Text(
                    text = item.dateTime,
                    fontFamily = Montserrat,
                    fontSize = 12.sp,
                    color = TextSecondary
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

// Preview
@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun HistoryScreenPreview() {
    MaterialTheme {
        HistoryScreen()
    }
}