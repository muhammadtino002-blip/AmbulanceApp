package com.example.ambulanceapp.ui.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import com.example.ambulanceapp.ui.theme.Montserrat
import com.example.ambulanceapp.ui.theme.TextPrimary
import com.example.ambulanceapp.ui.theme.TextSecondary
import com.example.ambulanceapp.ui.theme.CardBg
import com.example.ambulanceapp.ui.theme.MapBg
import com.example.ambulanceapp.ui.theme.ScreenBg
import com.example.ambulanceapp.ui.theme.HintGray

// Data model
data class DetailHistoryData(
    val name: String          = "Suyatno Indrawan",
    val age: String           = "59 Year Old",
    val condition: String     = "Sit",
    val tbc: String           = "No",
    val mainCondition: String = "High blood pressure and High blood sugar",
    val hospital: String      = "RSUP Dr.Sardjito",
    val companion: String     = "2 persons",
    val phoneNumber: String   = "0832 1289 4721",
    val additionalCondition: String = "-"
)

// Screen
@Composable
fun DetailHistoryScreen(
    title: String = "Non-Emergency",
    data: DetailHistoryData = DetailHistoryData(),
    onBackClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ScreenBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            // Top bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ScreenBg)
                    .statusBarsPadding()
                    .padding(horizontal = 8.dp)
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = TextPrimary,
                        modifier = Modifier
                            .size(16.dp)
                            .clickable{ (onBackClick) }
                    )
                }
                Text(
                    text = title,
                    fontFamily = Montserrat,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Detail card
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {

                    DetailRow(label = "Name",      value = data.name)
                    DetailRow(label = "Age",       value = data.age)
                    DetailRow(label = "Condition", value = data.condition)
                    DetailRow(label = "TBC",       value = data.tbc)
                    DetailRow(
                        label = "Main\nCondition",
                        value = data.mainCondition,
                        valueAlign = TextAlign.End,
                        multilineValue = true
                    )
                    DetailRow(label = "Hospital",  value = data.hospital)
                    DetailRow(label = "Companion", value = data.companion)
                    DetailRow(
                        label = "Phone\nNumber",
                        value = data.phoneNumber,
                        valueWeight = FontWeight.Bold
                    )
                    DetailRow(
                        label = "Addtional\nCondition",
                        value = data.additionalCondition,
                        showDivider = false
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Map placeholder
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MapBg),
                        contentAlignment = Alignment.Center
                    ) {
                        // Replace with AndroidView + MapView or GoogleMap composable
                        Text(
                            text = "Map View",
                            color = Color.Gray,
                            fontFamily = Montserrat,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// Single detail row with divider
@Composable
private fun DetailRow(
    label: String,
    value: String,
    valueAlign: TextAlign = TextAlign.End,
    multilineValue: Boolean = false,
    valueWeight: FontWeight = FontWeight.Normal,
    showDivider: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = if (multilineValue) Alignment.Top else Alignment.CenterVertically
    ) {
        // Label (left)
        Text(
            text = label,
            fontFamily = Montserrat,
            fontSize = 14.sp,
            color = TextSecondary,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.width(110.dp),
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        // Value (right)
        Text(
            text = value,
            fontFamily = Montserrat,
            fontSize = 14.sp,
            color = TextPrimary,
            fontWeight = valueWeight,
            textAlign = valueAlign,
            lineHeight = 20.sp,
            modifier = Modifier.widthIn(max = 200.dp)
        )
    }

    if (showDivider) {
        HorizontalDivider(color = HintGray, thickness = 0.8.dp)
    }
}

// Preview
@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun DetailHistoryScreenPreview() {
    MaterialTheme {
        DetailHistoryScreen()
    }
}