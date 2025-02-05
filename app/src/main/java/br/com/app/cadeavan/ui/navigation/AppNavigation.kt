package br.com.app.cadeavan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.app.cadeavan.ui.screens.DriverScreen
import br.com.app.cadeavan.ui.screens.MainScreen
import br.com.app.cadeavan.ui.screens.TrackerScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Início") {
        composable("Tela Inicial") { MainScreen(navController) }
        composable("Motorista") { DriverScreen(navController) }
        composable("Responsável") { TrackerScreen(navController) }
    }
}