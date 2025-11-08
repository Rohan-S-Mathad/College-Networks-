package com.example.myapplication2.screens

import android.graphics.BitmapFactory
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication2.R
import com.example.myapplication2.ui.theme.AppBlack
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToLoginSelection: () -> Unit) {
    val context = LocalContext.current
    var startAnimation by remember { mutableStateOf(false) }

    // Try to load custom logo from assets (only as fallback)
    val customLogo = remember {
        val possibleNames = listOf(
            "applogo.png",
            "applogo.jpg",
            "applogo.jpeg",
            "app_logo.png",
            "app_logo.jpg",
            "logo.png",
            "logo.jpg"
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

    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "splash_alpha"
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(2000)
        startAnimation = false
        delay(1000)
        onNavigateToLoginSelection()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBlack),
        contentAlignment = Alignment.Center
    ) {
        // Always use app icon first, custom logo as fallback
        if (customLogo == null) {
            // Use app icon (ic_launcher)
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .alpha(alpha),
                contentScale = ContentScale.Fit
            )
        } else {
            // Use custom logo from assets
            Image(
                bitmap = customLogo.asImageBitmap(),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .alpha(alpha),
                contentScale = ContentScale.Fit
            )
        }
    }
}
