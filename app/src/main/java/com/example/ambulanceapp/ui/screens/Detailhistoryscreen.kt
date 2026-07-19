package com.example.ambulanceapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambulanceapp.data.AmbulanceOrder
import com.example.ambulanceapp.data.OrderStatus
import com.example.ambulanceapp.data.TimelineEvent
import com.example.ambulanceapp.data.sampleOrders
import com.example.ambulanceapp.ui.theme.NavyPrimary
import com.example.ambulanceapp.ui.theme.NavyTertiary
import com.example.ambulanceapp.ui.theme.White
import com.example.ambulanceapp.ui.theme.OffWhite
import com.example.ambulanceapp.ui.theme.TextPrimary
import com.example.ambulanceapp.ui.theme.TextSecondary
import com.example.ambulanceapp.ui.theme.BorderColor
import com.example.ambulanceapp.ui.theme.Red
import com.example.ambulanceapp.ui.theme.Green
import com.example.ambulanceapp.ui.theme.Montserrat
import com.example.ambulanceapp.ui.theme.Orange
import com.example.ambulanceapp.ui.theme.ScreenBg
import com.example.ambulanceapp.ui.theme.TimelinePending


// History Detail Screen
@Composable
fun HistoryDetailScreen(
    order: AmbulanceOrder,
    onBack: () -> Unit = {}
) {
    val (statusColor, statusBg, statusLabel, statusIcon) = when (order.status) {
        OrderStatus.COMPLETED -> StatusStyle(Green,  ScreenBg,  "Completed", Icons.Filled.CheckCircle)
        OrderStatus.CANCELLED -> StatusStyle(Red,    ScreenBg,    "Cancelled", Icons.Filled.Cancel)
        OrderStatus.ONGOING   -> StatusStyle(Orange, ScreenBg, "Ongoing",   Icons.Filled.AccessTime)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBg)
    ) {
        // Top App Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(NavyPrimary)
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            IconButton(
                onClick  = onBack,
                modifier = Modifier.align(Alignment.CenterStart).size(36.dp)
            ) {
                Icon(
                    Icons.Filled.ArrowBackIos,
                    contentDescription = "Back",
                    tint     = White,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text       = "Order Detail",
                fontSize   = 17.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                color      = White,
                modifier   = Modifier.align(Alignment.Center)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            // Order Header Card
            DetailCard {
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            order.id,
                            fontSize   = 18.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.ExtraBold,
                            color      = NavyPrimary
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "${order.date}  ·  ${order.time}",
                            fontSize = 12.sp,
                            fontFamily = Montserrat,
                            color    = TextSecondary
                        )
                    }
                    StatusBadge(
                        icon  = statusIcon,
                        label = statusLabel,
                        color = statusColor,
                        bg    = statusBg
                    )
                }

                Spacer(Modifier.height(14.dp))
                HorizontalDivider(color = BorderColor)
                Spacer(Modifier.height(14.dp))

                // Service type pill
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Box(
                        modifier         = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(NavyTertiary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Filled.LocalHospital,
                            contentDescription = null,
                            tint     = White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column {
                        Text(
                            "Service Type",
                            fontSize = 11.sp,
                            fontFamily = Montserrat,
                            color = TextSecondary
                        )
                        Text(
                            order.serviceType,
                            fontSize = 14.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                // Summary stats row
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem(label = "Distance",  value = order.distanceKm)
                    VerticalDivider(Modifier.height(36.dp))
                    StatItem(label = "Duration",  value = order.durationMin)
                    VerticalDivider(Modifier.height(36.dp))
                    StatItem(label = "Companion", value = order.companion, small = true)
                }
            }

            // Patient Information
            SectionCard(title = "Patient Information", icon = Icons.Outlined.Person) {
                InfoRow("Full Name",   order.patientName)
                InfoRow("Age",         "${order.patientAge} years old")
                InfoRow("Condition",   order.condition)
                InfoRow("Complaint",   order.complaint)
                InfoRow("Phone",       order.phone)
            }

            // Location
            SectionCard(title = "Location", icon = Icons.Outlined.LocationOn) {
                InfoRow("Pickup Address", order.pickupAddress)
                InfoRow("Destination",    order.hospital)
            }

            // Driver Information
            SectionCard(title = "Driver Information", icon = Icons.Outlined.DirectionsCar) {
                InfoRow("Driver Name",   order.driverName)
                InfoRow("Driver Phone",  order.driverPhone)
                InfoRow("Vehicle Plate", order.vehiclePlate)
            }

            // Order Timeline
            SectionCard(title = "Order Timeline", icon = Icons.Outlined.Timeline) {
                order.timeline.forEachIndexed { index, event ->
                    TimelineRow(
                        event    = event,
                        isLast   = index == order.timeline.lastIndex
                    )
                }
            }

            // Re-order button (only for completed)
            if (order.status == OrderStatus.COMPLETED) {
                Button(
                    onClick  = { /* TODO: re-order */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape    = RoundedCornerShape(26.dp),
                    colors   = ButtonDefaults.buttonColors(
                        containerColor = NavyPrimary,
                        contentColor   = White
                    )
                ) {
                    Icon(Icons.Filled.Refresh, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Re-order Ambulance",
                        fontSize = 14.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Cancel button for ongoing
            if (order.status == OrderStatus.ONGOING) {
                OutlinedButton(
                    onClick  = { /* TODO: cancel */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape    = RoundedCornerShape(26.dp),
                    colors   = ButtonDefaults.outlinedButtonColors(contentColor = Red),
                    border   = androidx.compose.foundation.BorderStroke(1.5.dp, Red)
                ) {
                    Icon(Icons.Filled.Cancel, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Cancel Order",
                        fontSize = 14.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}

// Timeline Row
@Composable
private fun TimelineRow(event: TimelineEvent, isLast: Boolean) {
    Row(modifier = Modifier.fillMaxWidth()) {

        // Left: dot + vertical line
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier            = Modifier.width(32.dp)
        ) {
            Box(
                modifier         = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(if (event.isDone) NavyPrimary else TimelinePending),
                contentAlignment = Alignment.Center
            ) {
                if (event.isDone) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = null,
                        tint     = White,
                        modifier = Modifier.size(9.dp)
                    )
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(36.dp)
                        .background(if (event.isDone) NavyPrimary.copy(alpha = 0.3f) else TimelinePending)
                )
            }
        }

        Spacer(Modifier.width(12.dp))

        // Right: label + time
        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(bottom = if (isLast) 0.dp else 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.Top
        ) {
            Text(
                event.label,
                fontSize   = 13.sp,
                fontFamily = Montserrat,
                fontWeight = if (event.isDone) FontWeight.SemiBold else FontWeight.Normal,
                color      = if (event.isDone) TextPrimary else TextSecondary
            )
            Text(
                event.time,
                fontSize = 12.sp,
                fontFamily = Montserrat,
                color    = if (event.isDone) NavyPrimary else TextSecondary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// Reusable UI pieces
@Composable
private fun DetailCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), content = content)
    }
}

@Composable
private fun SectionCard(
    title: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier         = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(NavyTertiary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        icon,
                        null,
                        tint = White,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(Modifier.width(10.dp))
                Text(
                    title,
                    fontSize = 15.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
            Spacer(Modifier.height(14.dp))
            HorizontalDivider(color = BorderColor)
            Spacer(Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier              = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 13.sp, color = TextSecondary, modifier = Modifier.weight(1f))
        Spacer(Modifier.width(12.dp))
        Text(
            value,
            fontSize   = 13.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium,
            color      = TextPrimary,
            textAlign  = TextAlign.End,
            modifier   = Modifier.weight(1.5f)
        )
    }
}

@Composable
private fun StatItem(label: String, value: String, small: Boolean = false) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            value,
            fontSize   = if (small) 12.sp else 15.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            color      = NavyPrimary,
            textAlign  = TextAlign.Center
        )
        Text(label, fontSize = 11.sp, color = TextSecondary)
    }
}

@Composable
private fun StatusBadge(
    icon: ImageVector,
    label: String,
    color: Color,
    bg: Color
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            null,
            tint = color,
            modifier = Modifier.size(14.dp)
        )
        Spacer(Modifier.width(5.dp))
        Text(
            label,
            fontSize = 12.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.SemiBold,
            color = color
        )
    }
}

private data class StatusStyle(
    val color: Color,
    val bg: Color,
    val label: String,
    val icon: ImageVector
)

// Preview
@Preview(name = "History Detail", showBackground = true, device = "spec:width=390dp,height=844dp,dpi=430")
@Composable
fun HistoryDetailPreview() {
    MaterialTheme { HistoryDetailScreen(order = sampleOrders.first()) }
}