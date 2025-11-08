package com.example.myapplication2.screens

import android.graphics.BitmapFactory
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication2.ui.theme.*
import kotlinx.coroutines.delay
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun AboutUsScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    var showContent by remember { mutableStateOf(false) }

    // Load founders image from assets
    val foundersImage = remember {
        val possibleNames = listOf(
            "founders.png",
            "founders.jpg",
            "founders.jpeg",
            "about_us.png",
            "about_us.jpg"
        )
        var bitmap: android.graphics.Bitmap? = null

        for (name in possibleNames) {
            try {
                val inputStream = context.assets.open(name)
                bitmap = BitmapFactory.decodeStream(inputStream)
                if (bitmap != null) break
            } catch (e: Exception) {
                // Try next filename
            }
        }
        bitmap
    }

    LaunchedEffect(Unit) {
        delay(100)
        showContent = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0A0A0A),
                            Color.Black,
                            Color(0xFF1A0A2E)
                        )
                    )
                )
        )

        // Animated particles
        AboutUsParticles()

        AnimatedVisibility(
            visible = showContent,
            enter = fadeIn(tween(500)) + slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(500)
            )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Top bar with back button
                AboutUsTopBar(onBack = onBack)

                // Scrollable content
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Title
                    item {
                        Text(
                            text = "About Us",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppPurple,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                    // Founders Image
                    item {
                        FoundersImageCard(foundersImage)
                    }

                    // Origin Story
                    item {
                        SectionCard(
                            title = "Our Story",
                            content = "We created this app from our everyday college experiences — missing announcements, crowded canteen lines, and endlessly searching chats for notes or event updates. That's when we thought:\n\n\"Why not build one smart platform where every student finds everything — instantly?\""
                        )
                    }

                    // Why We Built It
                    item {
                        SectionCard(
                            title = "💡 Why We Built It",
                            content = "We noticed key issues:\n\n• Important hackathon or event updates often don't reach everyone.\n\n• Study materials are scattered across multiple groups.\n\n• Long canteen queues and crowded libraries waste time.\n\nSo, we built a Campus Integration App to:\n\n• Deliver all college announcements and events in one place.\n\n• Notify students in real time about opportunities.\n\n• Provide organized study materials.\n\n• Show live crowd data for canteen and library, and even let students pre-order food."
                        )
                    }

                    // Vision
                    item {
                        SectionCard(
                            title = "🎯 Our Vision",
                            content = "To make college life smarter, faster, and better connected — bridging students with their campus through one unified digital experience.\n\n\"We built what we know will help out all students in a campus.\"\n— Rohan & Tarun, Founders, Campus Network"
                        )
                    }

                    // Bottom spacing
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun AboutUsTopBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.3f))
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
            text = "Back",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = AppPurple
        )
    }
}

@Composable
fun FoundersImageCard(imageBitmap: android.graphics.Bitmap?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.05f)
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 2.dp,
            brush = Brush.linearGradient(
                colors = listOf(AppPurple, AppPurpleSecondary)
            )
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap.asImageBitmap(),
                    contentDescription = "Founders - Rohan & Tarun",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                // Placeholder
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        // Placeholder avatar 1
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(AppPurple.copy(alpha = 0.3f))
                                .border(3.dp, AppPurple, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "R",
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppPurple
                            )
                        }

                        // Placeholder avatar 2
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(AppPurple.copy(alpha = 0.3f))
                                .border(3.dp, AppPurple, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "T",
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppPurple
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Rohan & Tarun",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppPurple
                    )

                    Text(
                        text = "Founders, Campus Network",
                        fontSize = 14.sp,
                        color = AppLightGrey
                    )
                }
            }
        }
    }
}

@Composable
fun SectionCard(
    title: String,
    content: String
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = AppPurple,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = content,
                fontSize = 15.sp,
                color = AppLightGrey,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
fun AboutUsParticles() {
    val infiniteTransition = rememberInfiniteTransition(label = "about_particles")

    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(15000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "particle_progress"
    )

    val particles = remember {
        List(25) {
            AboutParticle(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                speed = Random.nextFloat() * 0.3f + 0.2f,
                size = Random.nextFloat() * 2.5f + 1f
            )
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        particles.forEach { particle ->
            val currentY = (particle.y + animationProgress * particle.speed) % 1f
            val currentX = particle.x + sin(currentY * 8) * 0.04f

            val alpha = if (currentY < 0.1f) currentY / 0.1f
            else if (currentY > 0.9f) (1f - currentY) / 0.1f
            else 1f

            drawCircle(
                color = AppPurple.copy(alpha = alpha * 0.25f),
                radius = particle.size,
                center = Offset(
                    x = currentX * size.width,
                    y = currentY * size.height
                )
            )
        }
    }
}

private data class AboutParticle(
    val x: Float,
    val y: Float,
    val speed: Float,
    val size: Float
)
