package com.eddymy1304.crudpersonamvvm.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreen

fun NavController.navigateToHome(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(HomeScreen, builder = navOptions)

fun NavGraphBuilder.homeScreen(onNavigateToDetail: (numberDocument: String) -> Unit) {
    composable<HomeScreen> {
        HomeRoot(onNavigateToDetail = onNavigateToDetail)
    }
}