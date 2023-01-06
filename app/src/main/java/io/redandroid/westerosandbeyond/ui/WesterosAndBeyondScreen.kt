package io.redandroid.westerosandbeyond.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WesterosAndBeyondScreen() {
    val homeNavController = rememberNavController()

    WesterosAndBeyondTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Surface(
                    shadowElevation = 4.dp
                ) {
                    TopAppBar(
                        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                        title = {
                            Text(
                                color = MaterialTheme.colorScheme.onPrimary,
                                text = "Houses"
                            )
                        }
                    )
                }

            },
            content = { padding ->
                Box(
                    modifier = Modifier.padding(padding)
                ) {
                    WesterosAndBeyondNavHost(
                        navController = homeNavController
                    )
                }
            }
        )
    }
}

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



