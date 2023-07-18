package com.dragic.gamehunter.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dragic.gamehunter.view.favorites.FavoritesScreen
import com.dragic.gamehunter.view.gamedetails.GameDetailsScreen
import com.dragic.gamehunter.view.home.HomeScreen

private const val DEAL_ID_DEFAULT_VALUE = 0

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(onDealClick = { dealId ->
                navController.navigateToDetails(dealId)
            })
        }
        composable(route = Favorites.route) {
            FavoritesScreen()
        }
        composable(
            route = Details.routeWithArgs,
            arguments = Details.arguments
        ) {
            val dealId = it.arguments?.getInt(Details.detailsTypeArg) ?: DEAL_ID_DEFAULT_VALUE
            GameDetailsScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navigateToDetails(dealId: Int) {
    this.navigateSingleTopTo("${Details.route}/$dealId")
}
