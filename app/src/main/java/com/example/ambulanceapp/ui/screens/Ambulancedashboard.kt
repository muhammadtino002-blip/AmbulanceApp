package com.example.ambulanceapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambulanceapp.ui.theme.Montserrat

// Design Tokens
private val NavyPrimary   = Color(0xFF1A3A6B)
private val NavyDark      = Color(0xFF122850)
private val NavyLight     = Color(0xFF2B4E8C)
private val White         = Color(0xFFFFFFFF)
private val Black         = Color(0xFF000000)
private val OffWhite      = Color(0xFFF5F5F5)
private val TextPrimary   = Color(0xFF111827)
private val TextSecondary = Color(0xFF6B7280)
private val CardBg        = Color(0xFFFFFFFF)
private val IconBg        = Color(0xFFF3F4F6)
private val OverlayDark   = Color(0x99000000)

// Data Models
data class ServiceCategory(
    val icon: ImageVector,
    val label: String,
    val onClick: () -> Unit = {}
)

data class NewsItem(
    val title: String,
    val source: String
)

// ROOT SCREEN
@Composable
fun AmbulanceDashboardScreen(
    onNavigateToNonEmergency: () -> Unit = {}
) {
    var selectedNavItem by remember { mutableIntStateOf(0) }

    // Build category list here so we can inject the callback
    val serviceCategories = listOf(
        ServiceCategory(Icons.Outlined.Emergency,         "Emergency"),
        ServiceCategory(Icons.Outlined.MedicalServices, "Non-\nEmergency",
            onClick = onNavigateToNonEmergency),
        ServiceCategory(Icons.Outlined.AirlineSeatFlat,     "Corpse"),
        ServiceCategory(Icons.Outlined.CalendarMonth,   "Schedule")
    )

    val latestNews = listOf(
        NewsItem(
            "Bantu Palestina, BAZNAS Bersama Dubes RI di Mesir Salurkan 5 Unit Ambulans dari Tempo Scan",
            "Baznas"
        ),
        NewsItem(
            "Pecah Ban, Ambulans Jenazah Tabrak Truk di Jembrana Bali",
            "CNN Indonesia"
        ),
        NewsItem(
            "Kemenkes Tambah 200 Unit Ambulans untuk Puskesmas Terpencil",
            "Kompas"
        )
    )

    Scaffold(
        containerColor = OffWhite,
        bottomBar = { BottomNavBar(selectedNavItem) { selectedNavItem = it } }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            HeroHeader(onTapHere = onNavigateToNonEmergency)
            Spacer(Modifier.height(24.dp))
            SectionHeader("Service Category", onViewAll = {})
            Spacer(Modifier.height(12.dp))
            ServiceCategoryGrid(serviceCategories)
            Spacer(Modifier.height(28.dp))
            SectionHeader("Latest News", onViewAll = {})
            Spacer(Modifier.height(12.dp))
            LatestNewsRow(latestNews)
            Spacer(Modifier.height(24.dp))
        }
    }
}

// HERO HEADER
@Composable
private fun HeroHeader(onTapHere: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
            .background(Brush.verticalGradient(colors = listOf(NavyDark, NavyPrimary, NavyLight)))
            .padding(horizontal = 20.dp)
            .padding(top = 48.dp, bottom = 28.dp)
    ) {
        Column {
            // Top bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.LocationOn,
                        null,
                        tint = White,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(
                            "Your Location",
                            color = White.copy(alpha = 0.7f),
                            fontFamily = Montserrat,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            "Ngaglik, Sleman",
                            color = White,
                            fontFamily = Montserrat,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(White)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.Notifications, "Notifications",
                        tint = Black,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Search bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .background(White)
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Search,
                    "Search",
                    tint = TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text("Search", color = TextSecondary, fontSize = 14.sp)
            }

            Spacer(Modifier.height(16.dp))

            // CTA Banner
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "NEED\nAMBULANCE?",
                        color = White,
                        fontFamily = Montserrat,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 25.sp
                    )
                    Text(
                        "For emergency situations",
                        color = White.copy(alpha = 0.75f),
                        fontFamily = Montserrat,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    // TAP HERE navigates to Non-Emergency
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(White)
                            .clickable { onTapHere() }
                            .padding(horizontal = 18.dp, vertical = 8.dp)
                    ) {
                        Text(
                            "TAP HERE!",
                            color = NavyPrimary,
                            fontFamily = Montserrat,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(width = 150.dp, height = 90.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(com.example.ambulanceapp.R.drawable.ambulans),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

// Section Header
@Composable
private fun SectionHeader(title: String, onViewAll: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            fontFamily = Montserrat,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary

        )
        Row(
            modifier = Modifier.clickable { onViewAll() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "View All",
                fontFamily = Montserrat,
                fontSize = 12.sp,
                color = TextSecondary
            )
            Spacer(Modifier.width(2.dp))
            Icon(
                Icons.Default.ChevronRight,
                null,
                tint = TextSecondary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

// Service Category Grid
@Composable
private fun ServiceCategoryGrid(categories: List<ServiceCategory>) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        categories.chunked(2).forEach { rowItems ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                rowItems.forEach { category ->
                    ServiceCategoryCard(category = category, modifier = Modifier.weight(1f))
                }
                if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun ServiceCategoryCard(category: ServiceCategory, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(72.dp)
            .clickable { category.onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(IconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    category.icon,
                    null,
                    tint = TextPrimary,
                    modifier = Modifier.size(25.dp)
                )
            }
            Spacer(Modifier.width(10.dp))
            Text(
                category.label,
                fontFamily = Montserrat,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.ChevronRight,
                null,
                tint = TextSecondary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

// Latest News Row
@Composable
private fun LatestNewsRow(newsList: List<NewsItem>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(newsList) { news -> NewsCard(news) }
    }
}

@Composable
private fun NewsCard(news: NewsItem) {
    Box(
        modifier = Modifier
            .width(230.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Brush.linearGradient(colors = listOf(NavyDark, NavyLight)))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth().fillMaxHeight(0.55f).align(Alignment.BottomCenter)
                .background(Brush.verticalGradient(colors = listOf(Color.Transparent, OverlayDark)))
        )
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomStart).padding(14.dp)
        ) {
            Text(
                news.title,
                color = White,
                fontFamily = Montserrat,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 17.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                news.source,
                color = White.copy(alpha = 0.70f),
                fontFamily = Montserrat,
                fontSize = 10.sp
            )
        }
    }
}

// Bottom Nav
private data class NavItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
)

private val navItems = listOf(
    NavItem(
        Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        "Home"
    ),
    NavItem(
        Icons.Filled.Schedule,
        unselectedIcon = Icons.Outlined.Schedule,
        "History"
    ),
    NavItem(
        Icons.Filled.Chat,
        unselectedIcon = Icons.Outlined.Chat,
        "Messages"
    ),
    NavItem(
        Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        "Profile"
    )
)

@Composable
private fun BottomNavBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar(containerColor = White, tonalElevation = 0.dp) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick  = { onItemSelected(index) },
                icon     = {
                    val isSelected = selectedIndex == index
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        item.label,
                        modifier = Modifier.size(35.dp)
                    ) },
                colors   = NavigationBarItemDefaults.colors(
                    selectedIconColor   = NavyPrimary,
                    unselectedIconColor = TextSecondary,
                    indicatorColor      = Color.Transparent
                ),
                alwaysShowLabel = false
            )
        }
    }
}

// Preview
@Preview(name = "Dashboard", showBackground = true, device = "spec:width=390dp,height=844dp,dpi=430")
@Composable
fun AmbulanceDashboardPreview() {
    MaterialTheme { AmbulanceDashboardScreen() }
}