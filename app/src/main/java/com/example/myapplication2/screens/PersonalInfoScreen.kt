package com.example.myapplication2.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication2.data.UserRepository
import com.example.myapplication2.ui.theme.*
import kotlinx.coroutines.delay

// Data classes for library and payment info
data class BorrowedBook(
    val title: String,
    val borrowDate: String,
    val dueDate: String,
    val status: String // "Current" or "Returned"
)

data class PaymentDetail(
    val name: String,
    val dateOpened: String,
    val status: String, // "Paid", "Pending", "Overdue"
    val amount: String,
    val deadline: String
)

@Composable
fun PersonalInfoScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val emailProfile = remember { UserRepository.getCurrentEmailProfile(context) }
    var showContent by remember { mutableStateOf(false) }

    // Expanded states for each section
    var personalExpanded by remember { mutableStateOf(false) }
    var academicExpanded by remember { mutableStateOf(false) }
    var libraryExpanded by remember { mutableStateOf(false) }
    var paymentExpanded by remember { mutableStateOf(false) }

    // Show more states
    var showAllLibraryBooks by remember { mutableStateOf(false) }
    var showAllPayments by remember { mutableStateOf(false) }

    // Sample library data - 10 test cases
    val libraryBooks = remember {
        listOf(
            BorrowedBook("Introduction to Algorithms", "20 Dec 2024", "20 Jan 2025", "Current"),
            BorrowedBook("Clean Code: A Handbook", "18 Dec 2024", "18 Jan 2025", "Current"),
            BorrowedBook("Design Patterns", "15 Dec 2024", "15 Jan 2025", "Current"),
            BorrowedBook("The Pragmatic Programmer", "10 Dec 2024", "10 Jan 2025", "Current"),
            BorrowedBook(
                "Artificial Intelligence: A Modern Approach",
                "05 Dec 2024",
                "05 Jan 2025",
                "Current"
            ),
            BorrowedBook("Operating System Concepts", "01 Nov 2024", "01 Dec 2024", "Returned"),
            BorrowedBook("Computer Networks", "25 Oct 2024", "25 Nov 2024", "Returned"),
            BorrowedBook("Database System Concepts", "20 Oct 2024", "20 Nov 2024", "Returned"),
            BorrowedBook("Machine Learning Yearning", "15 Oct 2024", "15 Nov 2024", "Returned"),
            BorrowedBook("Python for Data Analysis", "10 Oct 2024", "10 Nov 2024", "Returned")
        )
    }

    // Sample payment data - 10 test cases
    val payments = remember {
        listOf(
            PaymentDetail("Semester Fees", "01 Aug 2024", "Paid", "₹45,000", "31 Aug 2024"),
            PaymentDetail("Library Fine", "15 Dec 2024", "Pending", "₹150", "30 Dec 2024"),
            PaymentDetail("Lab Equipment Fee", "10 Sep 2024", "Paid", "₹2,500", "20 Sep 2024"),
            PaymentDetail("Sports Fee", "01 Jul 2024", "Paid", "₹1,200", "15 Jul 2024"),
            PaymentDetail("Hostel Fees", "01 Jun 2024", "Paid", "₹35,000", "15 Jun 2024"),
            PaymentDetail("Exam Fee", "20 Nov 2024", "Paid", "₹800", "30 Nov 2024"),
            PaymentDetail("Bus Fee", "01 Dec 2024", "Pending", "₹3,500", "10 Jan 2025"),
            PaymentDetail("Internet Fee", "15 Nov 2024", "Overdue", "₹500", "30 Nov 2024"),
            PaymentDetail("Workshop Registration", "05 Oct 2024", "Paid", "₹1,500", "20 Oct 2024"),
            PaymentDetail("ID Card Replacement", "10 Nov 2024", "Paid", "₹200", "25 Nov 2024")
        )
    }

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

        AnimatedVisibility(
            visible = showContent,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            ) + fadeIn(tween(400))
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Top Bar
                PersonalInfoTopBar(onBack = onBack)

                // Scrollable content
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    // Profile Header
                    item {
                        ProfileHeader(emailProfile)
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    // Personal Information Section
                    item {
                        DropdownSection(
                            title = "Personal Information",
                            icon = Icons.Default.Person,
                            isExpanded = personalExpanded,
                            onToggle = { personalExpanded = !personalExpanded }
                        ) {
                            emailProfile?.let { profile ->
                                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                    InfoRow("Full Name", profile.name)
                                    InfoRow("Email", profile.email)
                                    InfoRow("Roll Number", profile.rollNumber)
                                    InfoRow("Address", profile.address)
                                }
                            }
                        }
                    }

                    // Academic Details Section
                    item {
                        DropdownSection(
                            title = "Academic Details",
                            icon = Icons.Default.School,
                            isExpanded = academicExpanded,
                            onToggle = { academicExpanded = !academicExpanded }
                        ) {
                            emailProfile?.let { profile ->
                                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                    InfoRow("Department", profile.department)
                                    InfoRow("Branch", profile.branch)
                                    InfoRow("Batch", profile.classNumber)
                                    InfoRow("Section", "A") // Add section field if available
                                    InfoRow("College", profile.college)
                                    InfoRow("Class Teacher", profile.classTeacherName)
                                    InfoRow("Teacher Contact", profile.teacherContact)
                                }
                            }
                        }
                    }

                    // Library Details Section
                    item {
                        DropdownSection(
                            title = "Library Details",
                            icon = Icons.Default.MenuBook,
                            isExpanded = libraryExpanded,
                            onToggle = { libraryExpanded = !libraryExpanded }
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                libraryBooks.take(if (showAllLibraryBooks) libraryBooks.size else 5)
                                    .forEach { book ->
                                    LibraryBookCard(book)
                                }
                                if (!showAllLibraryBooks && libraryBooks.size > 5) {
                                    ShowMoreButton(onClick = { showAllLibraryBooks = true })
                                }
                            }
                        }
                    }

                    // Payment Details Section
                    item {
                        DropdownSection(
                            title = "Payment Details",
                            icon = Icons.Default.Payment,
                            isExpanded = paymentExpanded,
                            onToggle = { paymentExpanded = !paymentExpanded }
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                payments.take(if (showAllPayments) payments.size else 5)
                                    .forEach { payment ->
                                    PaymentCard(payment)
                                }
                                if (!showAllPayments && payments.size > 5) {
                                    ShowMoreButton(onClick = { showAllPayments = true })
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
fun PersonalInfoTopBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Personal Information",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = AppPurple
        )
    }
}

@Composable
fun ProfileHeader(emailProfile: com.example.myapplication2.data.EmailUser?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(
                    width = 3.dp,
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            AppPurple,
                            AppPurpleSecondary,
                            AppPurple
                        )
                    ),
                    shape = CircleShape
                )
                .background(AppPurple.copy(alpha = 0.3f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = emailProfile?.name?.firstOrNull()?.toString() ?: "S",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = AppWhite
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = emailProfile?.name ?: "Student",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = AppPurple
        )

        Text(
            text = emailProfile?.rollNumber ?: "",
            fontSize = 14.sp,
            color = AppLightGrey
        )
    }
}

@Composable
fun DropdownSection(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.05f)
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.5.dp,
            color = AppPurple.copy(alpha = 0.4f)
        )
    ) {
        Column {
            // Header (clickable)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onToggle)
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(AppPurple.copy(alpha = 0.2f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = AppPurple,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppWhite,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    tint = AppPurple,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Expandable content
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White.copy(alpha = 0.03f),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = AppPurple.copy(alpha = 0.2f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = AppLightGrey.copy(alpha = 0.7f),
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 15.sp,
            color = AppWhite,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun LibraryBookCard(book: BorrowedBook) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (book.status) {
                "Current" -> Color(0xFF1E90FF).copy(alpha = 0.15f)
                else -> Color.White.copy(alpha = 0.03f)
            }
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = when (book.status) {
                "Current" -> Color(0xFF1E90FF).copy(alpha = 0.5f)
                else -> AppLightGrey.copy(alpha = 0.2f)
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Book,
                    contentDescription = null,
                    tint = if (book.status == "Current") Color(0xFF1E90FF) else AppLightGrey,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = book.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppWhite
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Borrowed",
                        fontSize = 11.sp,
                        color = AppLightGrey.copy(alpha = 0.7f)
                    )
                    Text(
                        text = book.borrowDate,
                        fontSize = 13.sp,
                        color = AppWhite
                    )
                }

                Column {
                    Text(
                        text = "Due Date",
                        fontSize = 11.sp,
                        color = AppLightGrey.copy(alpha = 0.7f)
                    )
                    Text(
                        text = book.dueDate,
                        fontSize = 13.sp,
                        color = if (book.status == "Current") Color(0xFFFFA500) else AppWhite
                    )
                }

                Column {
                    Text(
                        text = "Status",
                        fontSize = 11.sp,
                        color = AppLightGrey.copy(alpha = 0.7f)
                    )
                    Text(
                        text = book.status,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (book.status == "Current") Color(0xFF00FF7F) else AppLightGrey
                    )
                }
            }
        }
    }
}

@Composable
fun PaymentCard(payment: PaymentDetail) {
    val statusColor = when (payment.status) {
        "Paid" -> Color(0xFF00FF7F)
        "Pending" -> Color(0xFFFFA500)
        "Overdue" -> Color(0xFFFF4C4C)
        else -> AppLightGrey
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = statusColor.copy(alpha = 0.1f)
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = statusColor.copy(alpha = 0.4f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = payment.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppWhite
                )

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = statusColor.copy(alpha = 0.3f)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = payment.status,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = statusColor,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Amount",
                        fontSize = 11.sp,
                        color = AppLightGrey.copy(alpha = 0.7f)
                    )
                    Text(
                        text = payment.amount,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppPurple
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Deadline",
                        fontSize = 11.sp,
                        color = AppLightGrey.copy(alpha = 0.7f)
                    )
                    Text(
                        text = payment.deadline,
                        fontSize = 13.sp,
                        color = if (payment.status == "Overdue") Color(0xFFFF4C4C) else AppWhite
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Opened: ${payment.dateOpened}",
                fontSize = 12.sp,
                color = AppLightGrey.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
fun ShowMoreButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppPurple.copy(alpha = 0.2f),
            contentColor = AppPurple
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = "Show More",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
