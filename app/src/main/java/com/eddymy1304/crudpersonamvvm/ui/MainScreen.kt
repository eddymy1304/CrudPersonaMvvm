package com.eddymy1304.crudpersonamvvm.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.eddymy1304.crudpersonamvvm.feature.home.HomeRoot
import com.eddymy1304.crudpersonamvvm.ui.components.NavHostApp
import com.eddymy1304.crudpersonamvvm.ui.theme.CrudPersonaMvvmTheme

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    CrudPersonaMvvmTheme {
        Scaffold(
            topBar = {},
            bottomBar = {}
        ) { paddingValues ->
            NavHostApp(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }
    }
}