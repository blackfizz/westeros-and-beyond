package io.redandroid.westerosandbeyond.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.redandroid.westerosandbeyond.core_ui.theme.WesterosAndBeyondTheme
import io.redandroid.westerosandbeyond.presentation.modules.house.detail.HouseDetailScreen
import io.redandroid.westerosandbeyond.presentation.modules.house.list.HouseListScreen
import java.net.URLEncoder

@Composable
fun WesterosAndBeyondScreen() {
    WesterosAndBeyondTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            WesterosAndBeyondNavHost()
        }
    }
}

@Composable
fun WesterosAndBeyondNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "houses"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable("houses") {
            HouseListScreen { houseUrl ->
                navController.navigate("houses/$houseUrl")
            }
        }

        composable(
            route = "houses/{houseUrl}",
            arguments = listOf(
                navArgument("houseUrl") { type = NavType.StringType }
            )
        ) {
            val houseUrl = it.arguments?.getString("houseUrl", "")
            HouseDetailScreen(houseUrl)
        }
    }
}



