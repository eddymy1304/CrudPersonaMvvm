package com.eddymy1304.crudpersonamvvm.feature.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class DetailScreen(val numberDocument: String)

fun NavController.navigateToDetail(
    numberDocument: String,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) =
    navigate(DetailScreen(numberDocument), navOptions)

fun NavGraphBuilder.detailScreen(onNavigateToHome: () -> Unit) {
    composable<DetailScreen> {
        DetailRoot(onNavigateToHome = onNavigateToHome)
    }
}