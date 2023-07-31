package com.dragic.gamehunter.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dragic.gamehunter.view.navigation.Details
import com.dragic.gamehunter.view.navigation.Favorites
import com.dragic.gamehunter.view.navigation.Home
import com.dragic.gamehunter.view.navigation.Navigation
import com.dragic.gamehunter.view.uicomponents.BottomBar
import com.dragic.gamehunter.view.uicomponents.TopBar
import com.dragic.gamehunter.viewmodel.HomeViewModel

private const val TWEEN_VISIBILITY_ANIMATION_DURATION = 500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameHunterApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route ?: Home.route
    val viewModel: HomeViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            if (route == Favorites.route || route == Details.routeWithArgs) {
                TopBar(
                    currentScreenRoute = route,
                    onSortCLicked = { viewModel.setShowDialog(true) },
                    onArrowBackClicked = { navController.navigateUp() },
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = route != Details.routeWithArgs,
                enter = slideInVertically(
                    animationSpec = tween(TWEEN_VISIBILITY_ANIMATION_DURATION, easing = LinearEasing)
                ) + fadeIn(),
                exit = slideOutVertically(
                    animationSpec = tween(TWEEN_VISIBILITY_ANIMATION_DURATION, easing = LinearEasing)
                ) + fadeOut(),
            ) {
                NavigationBar(Modifier.background(MaterialTheme.colorScheme.background)) {
                    BottomBar(
                        route = route,
                        onRouteSelected = { targetRoute ->
                            navController.apply {
                                navigate(targetRoute) {
                                    restoreState = true
                                    launchSingleTop = true
                                    graph.startDestinationRoute?.let { route ->
                                        popUpTo(route = route) {
                                            saveState = true
                                        }
                                    }
                                    popBackStack()
                                }
                            }
                        }
                    )
                }

            }
        }
    ) { innerPadding ->
        Navigation(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}
