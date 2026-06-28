package com.example.ambulanceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambulanceapp.ui.theme.Montserrat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

// Design Tokens
private val NavyPrimary     = Color(0xFF1A3A6B)
private val White           = Color(0xFFFFFFFF)
private val OffWhite        = Color(0xFFF7F7F7)
private val TextPrimary     = Color(0xFF111827)
private val TextHint        = Color(0xFF9CA3AF)
private val TextSecondary   = Color(0xFF6B7280)
private val BorderColor     = Color(0xFFE5E7EB)
private val SelectedDateBg  = NavyPrimary
private val UnselectedDateBg = Color(0xFFEEEEEE)

// Data
private val conditionOptions = listOf("Stable", "Critical", "Unknown")
private val tbOptions        = listOf("Yes", "No", "Unknown")

// Root Screen
@Composable
fun NonEmergencyScreen(
    onBack: () -> Unit = {},
    onOrderSubmitted: () -> Unit ,
) {
    // Form state
    var name              by remember { mutableStateOf("") }
    var age               by remember { mutableStateOf("") }
    var condition         by remember { mutableStateOf("") }
    var conditionExpanded by remember { mutableStateOf(false) }
    var complaint         by remember { mutableStateOf("") }
    var isTb              by remember { mutableStateOf("") }
    var tbExpanded        by remember { mutableStateOf(false) }
    var hospital          by remember { mutableStateOf("") }
    var companion         by remember { mutableStateOf("") }
    var phone             by remember { mutableStateOf("") }
    var additionalInfo    by remember { mutableStateOf("") }

    // Calendar state — week of Jan 2025 starting Sun 1
    val weekDates = remember {
        val base = LocalDate.of(2025, 1, 1)
        val sunday = base.minusDays(base.dayOfWeek.value.toLong() % 7)
        (0..6).map { sunday.plusDays(it.toLong()) }
    }
    var selectedDate by remember { mutableStateOf(LocalDate.of(2025, 1, 3)) }
    var monthLabel   by remember { mutableStateOf("January 2025") }

    Box(modifier = Modifier.fillMaxSize().background(OffWhite)) {

        Column(modifier = Modifier.fillMaxSize()) {

            // Map Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                // Map placeholder drawn with Canvas-style boxes
                MapPlaceholderView()

                // Top bar overlay
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
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
                        text       = "Non-Emergency",
                        fontFamily = Montserrat,
                        fontSize   = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color      = TextPrimary
                    )
                    Spacer(Modifier.weight(1f))
                    Spacer(Modifier.size(36.dp))
                }

                // Add Location button
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(White)
                        .clickable { }
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text       = "Add Location",
                        fontFamily = Montserrat,
                        fontSize   = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color      = TextPrimary
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        imageVector        = Icons.Filled.MyLocation,
                        contentDescription = null,
                        tint               = NavyPrimary,
                        modifier           = Modifier.size(16.dp)
                    )
                }
            }

            // Scrollable Form Sheet
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(White)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(top = 16.dp, bottom = 32.dp)
            ) {
                // Drag handle
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(BorderColor)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(Modifier.height(18.dp))

                // Order Date
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Text(
                        text       = "Order Date",
                        fontFamily = Montserrat,
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color      = TextPrimary
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Prev month",
                            tint               = TextSecondary,
                            modifier           = Modifier.size(20.dp).clickable { }
                        )
                        Text(
                            text     = monthLabel,
                            fontSize = 12.sp,
                            color    = TextSecondary
                        )
                        Icon(
                            Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Next month",
                            tint               = TextSecondary,
                            modifier           = Modifier.size(20.dp).clickable { }
                        )
                    }
                }

                Spacer(Modifier.height(14.dp))

                // Week date picker
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    weekDates.forEach { date ->
                        val isSelected = date == selectedDate
                        val dayAbbr = date.dayOfWeek
                            .getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                            .take(3)

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier            = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .clickable { selectedDate = date }
                                .padding(horizontal = 2.dp)
                        ) {
                            Text(
                                text     = dayAbbr,
                                fontFamily = Montserrat,
                                fontSize = 10.sp,
                                color    = TextSecondary,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier         = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isSelected) SelectedDateBg
                                        else UnselectedDateBg
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text       = date.dayOfMonth.toString(),
                                    fontFamily = Montserrat,
                                    fontSize   = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color      = if (isSelected) White else TextPrimary,
                                    textAlign  = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(28.dp))

                // Patient Data
                Text(
                    text       = "Patient Data",
                    fontFamily = Montserrat,
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color      = TextPrimary
                )

                Spacer(Modifier.height(14.dp))

                FormTextField(
                    value         = name,
                    onValueChange = { name = it },
                    placeholder   = "Name"
                )
                Spacer(Modifier.height(10.dp))

                FormTextField(
                    value         = age,
                    onValueChange = { age = it },
                    placeholder   = "Age"
                )
                Spacer(Modifier.height(10.dp))

                // Condition dropdown
                FormDropdown(
                    value       = condition,
                    placeholder = "Condition",
                    expanded    = conditionExpanded,
                    options     = conditionOptions,
                    onToggle    = { conditionExpanded = !conditionExpanded },
                    onSelect    = { condition = it; conditionExpanded = false }
                )
                Spacer(Modifier.height(10.dp))

                FormTextField(
                    value         = complaint,
                    onValueChange = { complaint = it },
                    placeholder   = "Main Complaint/Symptom"
                )
                Spacer(Modifier.height(10.dp))

                // TB dropdown
                FormDropdown(
                    value       = isTb,
                    placeholder = "Is the complaint tuberculosis?",
                    expanded    = tbExpanded,
                    options     = tbOptions,
                    onToggle    = { tbExpanded = !tbExpanded },
                    onSelect    = { isTb = it; tbExpanded = false }
                )
                Spacer(Modifier.height(10.dp))

                FormTextField(
                    value         = hospital,
                    onValueChange = { hospital = it },
                    placeholder   = "Destination Hospital"
                )
                Spacer(Modifier.height(10.dp))

                FormTextField(
                    value         = companion,
                    onValueChange = { companion = it },
                    placeholder   = "Companion"
                )
                Spacer(Modifier.height(10.dp))

                FormTextField(
                    value         = phone,
                    onValueChange = { phone = it },
                    placeholder   = "Phone Number"
                )
                Spacer(Modifier.height(10.dp))

                // Multi-line additional info
                OutlinedTextField(
                    value         = additionalInfo,
                    onValueChange = { additionalInfo = it },
                    placeholder   = {
                        Text(
                            text     = "Additional Information (more detailed condition, first aid measures already taken, etc.)",
                            color    = TextHint,
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    },
                    modifier      = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 100.dp),
                    shape         = RoundedCornerShape(10.dp),
                    colors        = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = BorderColor,
                        focusedBorderColor   = NavyPrimary,
                        unfocusedContainerColor = White,
                        focusedContainerColor   = White
                    ),
                    minLines      = 4
                )

                Spacer(Modifier.height(28.dp))

                // CTA Button
                Button(
                    onClick  = { onOrderSubmitted() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape    = RoundedCornerShape(28.dp),
                    colors   = ButtonDefaults.buttonColors(
                        containerColor = NavyPrimary
                    )
                ) {
                    Text(
                        text       = "Order Ambulance",
                        fontFamily = Montserrat,
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color      = White
                    )
                }
            }
        }
    }
}

// Reusable Form Text Field
@Composable
private fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    OutlinedTextField(
        value         = value,
        onValueChange = onValueChange,
        placeholder   = {
            Text(
                text = placeholder,
                color = TextHint,
                fontSize = 12.sp
            )
        },
        modifier      = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape         = RoundedCornerShape(10.dp),
        singleLine    = true,
        colors        = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor    = BorderColor,
            focusedBorderColor      = NavyPrimary,
            unfocusedContainerColor = White,
            focusedContainerColor   = White,
            cursorColor             = NavyPrimary
        )
    )
}

// Reusable Dropdown Field
@Composable
private fun FormDropdown(
    value: String,
    placeholder: String,
    expanded: Boolean,
    options: List<String>,
    onToggle: () -> Unit,
    onSelect: (String) -> Unit
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, if (expanded) NavyPrimary else BorderColor, RoundedCornerShape(10.dp))
                .background(White)
                .clickable { onToggle() }
                .padding(horizontal = 16.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text     = value.ifEmpty { placeholder },
                color    = if (value.isEmpty()) TextHint else TextPrimary,
                fontFamily = Montserrat,
                fontSize = 12.sp
            )
            Icon(
                imageVector        = Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint               = TextSecondary,
                modifier           = Modifier.size(20.dp)
            )
        }

        DropdownMenu(
            expanded         = expanded,
            onDismissRequest = { onToggle() },
            modifier         = Modifier.background(White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text    = {
                        Text(
                            option,
                            fontSize = 14.sp,
                            color = TextPrimary
                        ) },
                    onClick = { onSelect(option) }
                )
            }
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
            painter = painterResource(com.example.ambulanceapp.R.drawable.maps),
            contentDescription = "maps",
            modifier = Modifier.fillMaxSize()
        )
    }
}

// Preview
@Preview(
    name            = "Non-Emergency Screen",
    showBackground  = true,
    device          = "spec:width=390dp,height=844dp,dpi=430"
)
@Composable
fun NonEmergencyScreenPreview() {
    MaterialTheme {
        NonEmergencyScreen(
            onBack = { },
            onOrderSubmitted = { }
        )
    }
}