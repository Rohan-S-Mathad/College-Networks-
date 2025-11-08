package com.example.myapplication2.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication2.screens.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = NavRoutes.Splash.route,
    onExitApp: () -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = NavRoutes.Splash.route,
            exitTransition = {
                fadeOut(animationSpec = tween(300))
            }
        ) {
            SplashScreen(
                onNavigateToLoginSelection = {
                    navController.navigate(NavRoutes.LoginSelection.route) {
                        popUpTo(NavRoutes.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = NavRoutes.LoginSelection.route,
            enterTransition = {
                fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(300))
            }
        ) {
            LoginSelectionScreen(
                onGuestClick = {
                    navController.navigate(NavRoutes.GuestAuth.route)
                }
            )
        }

        composable(
            route = NavRoutes.GuestAuth.route,
            enterTransition = {
                fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300))
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(300))
            }
        ) {
            GuestAuthScreen(
                onAuthSuccess = {
                    navController.navigate(NavRoutes.GuestMainPage.route) {
                        popUpTo(NavRoutes.LoginSelection.route) {
                        inclusive = false
                        }
                    }
                },
                onBackToSelection = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = NavRoutes.GuestMainPage.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(300))
            }
        ) {
            // Handle back button press to exit app
            BackHandler {
                onExitApp()
            }

            GuestMainPage()
        }
    }
}
