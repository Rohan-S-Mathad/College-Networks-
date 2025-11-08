package com.example.myapplication2.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication2.ui.theme.*
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

// Event data class
data class AcademicEvent(
    val date: LocalDate,
    val title: String,
    val type: EventType,
    val description: String,
    val organizedBy: String = ""
)

enum class EventType(val color: Color, val label: String) {
    FEST(Color(0xFF00FF7F), "Fest/College Event"),
    EXAM(Color(0xFF1E90FF), "Exams/Tests"),
    HOLIDAY(Color(0xFFFF4C4C), "Holiday/Vacation"),
    WORKSHOP(Color(0xFFA020F0), "Workshop/Seminar"),
    DEADLINE(Color(0xFFFFA500), "Submission/Deadline")
}

@Composable
fun AcademicCalendarScreen(onBack: () -> Unit) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var showContent by remember { mutableStateOf(false) }

    // Sample events
    val events = remember { getSampleCalendarEvents() }

    val eventsForMonth = events.filter { event ->
        event.date.month == currentMonth.month && event.date.year == currentMonth.year
    }

    // Entry animation
    LaunchedEffect(Unit) {
        delay(100)
        showContent = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Pure black background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        )

        // Animated gradient waves
        AnimatedGradientWaves()

        // Floating particles
        FloatingPurpleParticles()

        // Main content with slide-up animation
        AnimatedVisibility(
            visible = showContent,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            ) + fadeIn(tween(400))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Top Bar
                CalendarTopBar(onBack = onBack)

                Spacer(modifier = Modifier.height(16.dp))

                // Month Navigation
                MonthNavigation(
                    currentMonth = currentMonth,
                    onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
                    onNextMonth = { currentMonth = currentMonth.plusMonths(1) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Calendar Grid
                CalendarGrid(
                    currentMonth = currentMonth,
                    events = eventsForMonth,
                    onDateClick = { date -> selectedDate = date }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Legend
                EventLegend()
            }
        }

        // Event Popup
        selectedDate?.let { date ->
            val dateEvents = events.filter { it.date == date }
            if (dateEvents.isNotEmpty()) {
                EventPopup(
                    date = date,
                    events = dateEvents,
                    onDismiss = { selectedDate = null }
                )
            } else {
                NoEventPopup(
                    date = date,
                    onDismiss = { selectedDate = null }
                )
            }
        }
    }
}

@Composable
fun CalendarTopBar(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = AppPurple,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Text(
            text = "Academic Calendar",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = AppPurple,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Tap on a date to view event details",
            fontSize = 13.sp,
            color = AppLightGrey.copy(alpha = 0.7f),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MonthNavigation(
    currentMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Previous month button
        IconButton(
            onClick = onPreviousMonth,
            modifier = Modifier
                .size(48.dp)
                .border(1.dp, AppPurple.copy(alpha = 0.5f), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous Month",
                tint = AppPurple,
                modifier = Modifier.size(28.dp)
            )
        }

        // Month & Year
        Text(
            text = "${
                currentMonth.month.getDisplayName(
                    TextStyle.FULL,
                    Locale.getDefault()
                )
            } ${currentMonth.year}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppPurple
        )

        // Next month button
        IconButton(
            onClick = onNextMonth,
            modifier = Modifier
                .size(48.dp)
                .border(1.dp, AppPurple.copy(alpha = 0.5f), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next Month",
                tint = AppPurple,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun CalendarGrid(
    currentMonth: YearMonth,
    events: List<AcademicEvent>,
    onDateClick: (LocalDate) -> Unit
) {
    Column {
        // Day headers (Mon, Tue, Wed, etc.)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").forEach { day ->
                Text(
                    text = day,
                    color = AppPurple.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Calendar days
        val firstDayOfMonth = currentMonth.atDay(1)
        val daysInMonth = currentMonth.lengthOfMonth()
        val startDayOfWeek = firstDayOfMonth.dayOfWeek.value // 1 = Monday, 7 = Sunday

        val totalCells = daysInMonth + (startDayOfWeek - 1)
        val rows = (totalCells + 6) / 7

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (row in 0 until rows) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (col in 0 until 7) {
                        val cellIndex = row * 7 + col
                        val dayOfMonth = cellIndex - (startDayOfWeek - 1) + 1

                        if (dayOfMonth in 1..daysInMonth) {
                            val date = currentMonth.atDay(dayOfMonth)
                            val dateEvents = events.filter { it.date == date }

                            DayCell(
                                day = dayOfMonth,
                                date = date,
                                events = dateEvents,
                                isToday = date == LocalDate.now(),
                                onClick = { onDateClick(date) },
                                rowIndex = row
                            )
                        } else {
                            // Empty cell
                            Box(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.DayCell(
    day: Int,
    date: LocalDate,
    events: List<AcademicEvent>,
    isToday: Boolean,
    onClick: () -> Unit,
    rowIndex: Int
) {
    var isVisible by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Staggered fade-in animation
    LaunchedEffect(Unit) {
        delay((rowIndex * 50).toLong())
        isVisible = true
    }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.1f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "day_scale"
    )

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(tween(200)) + scaleIn(
            initialScale = 0.8f,
            animationSpec = tween(200)
        ),
        modifier = Modifier.weight(1f)
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .scale(scale)
                .background(
                    color = Color.White.copy(alpha = if (isToday) 0.1f else 0.03f),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = if (isToday) 2.dp else 1.dp,
                    color = if (isToday) AppPurple else AppPurple.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = day.toString(),
                    color = if (isToday) AppPurple else AppWhite,
                    fontSize = 16.sp,
                    fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal
                )

                if (events.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        events.take(3).forEach { event ->
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(event.type.color, CircleShape)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EventLegend() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.05f)
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = AppPurple.copy(alpha = 0.2f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "LEGEND",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = AppLightGrey.copy(alpha = 0.6f),
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                EventType.values().forEach { type ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(type.color, CircleShape)
                        )
                        Text(
                            text = type.label.split("/")[0],
                            fontSize = 10.sp,
                            color = AppLightGrey.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EventPopup(
    date: LocalDate,
    events: List<AcademicEvent>,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.6f),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1A1A1A).copy(alpha = 0.95f)
            ),
            border = androidx.compose.foundation.BorderStroke(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(AppPurple, AppPurpleSecondary)
                )
            )
        ) {
            Column {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(AppPurple, AppPurpleSecondary)
                            )
                        )
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${date.dayOfMonth} ${
                            date.month.getDisplayName(
                                TextStyle.SHORT,
                                Locale.getDefault()
                            )
                        } ${date.year}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppWhite
                    )

                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = AppWhite
                        )
                    }
                }

                // Events list
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    events.forEach { event ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = event.type.color.copy(alpha = 0.15f)
                            ),
                            border = androidx.compose.foundation.BorderStroke(
                                width = 1.dp,
                                color = event.type.color.copy(alpha = 0.5f)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .background(event.type.color, CircleShape)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = event.type.label,
                                        fontSize = 11.sp,
                                        color = event.type.color,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = event.title,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AppWhite
                                )

                                if (event.description.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = event.description,
                                        fontSize = 13.sp,
                                        color = AppLightGrey
                                    )
                                }

                                if (event.organizedBy.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "Organized by: ${event.organizedBy}",
                                        fontSize = 12.sp,
                                        color = AppLightGrey.copy(alpha = 0.7f),
                                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NoEventPopup(date: LocalDate, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1A1A1A).copy(alpha = 0.95f)
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${date.dayOfMonth} ${
                        date.month.getDisplayName(
                            TextStyle.SHORT,
                            Locale.getDefault()
                        )
                    }",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppPurple
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "— No events —",
                    fontSize = 14.sp,
                    color = AppLightGrey.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = onDismiss) {
                    Text("Close", color = AppPurple)
                }
            }
        }
    }
}

// Sample events
fun getSampleCalendarEvents(): List<AcademicEvent> {
    val today = LocalDate.now()
    return listOf(
        AcademicEvent(
            date = today.plusDays(5),
            title = "TechFest 2025",
            type = EventType.FEST,
            description = "Annual tech fest with coding competitions and workshops",
            organizedBy = "Tech Club"
        ),
        AcademicEvent(
            date = today.plusDays(10),
            title = "Mid-term Exams",
            type = EventType.EXAM,
            description = "Mid-term examinations for all subjects",
            organizedBy = "Examination Department"
        ),
        AcademicEvent(
            date = today.plusDays(15),
            title = "Republic Day",
            type = EventType.HOLIDAY,
            description = "National holiday - college closed",
            organizedBy = ""
        ),
        AcademicEvent(
            date = today.plusDays(20),
            title = "AI/ML Workshop",
            type = EventType.WORKSHOP,
            description = "Hands-on workshop on machine learning fundamentals",
            organizedBy = "AI Department"
        ),
        AcademicEvent(
            date = today.plusDays(25),
            title = "Project Submission",
            type = EventType.DEADLINE,
            description = "Final project submission deadline",
            organizedBy = "Academic Office"
        )
    )
}
