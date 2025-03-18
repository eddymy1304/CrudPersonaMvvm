package com.eddymy1304.crudpersonamvvm.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.eddymy1304.crudpersonamvvm.feature.detail.detailScreen
import com.eddymy1304.crudpersonamvvm.feature.detail.navigateToDetail
import com.eddymy1304.crudpersonamvvm.feature.home.HomeScreen
import com.eddymy1304.crudpersonamvvm.feature.home.homeScreen

@Composable
fun NavHostApp(
    modifier: Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = HomeScreen,
        modifier = modifier
    ) {

        homeScreen { numberDocument ->
            navController.navigateToDetail(numberDocument)
        }

        detailScreen {
            navController.navigateUp()
        }

    }

}