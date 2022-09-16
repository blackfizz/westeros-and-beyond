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
import io.redandroid.westerosandbeyond.core.encodeUrl
import io.redandroid.westerosandbeyond.core.navigation.Navigation
import io.redandroid.westerosandbeyond.core_ui.theme.WesterosAndBeyondTheme
import io.redandroid.westerosandbeyond.presentation.modules.house.detail.HouseDetailScreen
import io.redandroid.westerosandbeyond.presentation.modules.house.list.HouseListScreen

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

// TODO: Move navigation composables into presentation/feature module
@Composable
fun WesterosAndBeyondNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Navigation.houseListPath
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Navigation.houseListPath) {
            HouseListScreen { houseUrl ->
                val urlEncoded = houseUrl.encodeUrl()
                val detailPath = Navigation.generateHouseDetailPath(urlEncoded)

                navController.navigate(detailPath)
            }
        }

        composable(
            route = Navigation.houseDetailPath,
            arguments = listOf(
                navArgument(Navigation.houseUrlParam) { type = NavType.StringType }
            )
        ) {
            HouseDetailScreen()
        }
    }
}



