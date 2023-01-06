package io.redandroid.westerosandbeyond.ui

import android.view.animation.BounceInterpolator
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.redandroid.westerosandbeyond.core.encodeUrl
import io.redandroid.westerosandbeyond.core.navigation.Navigation
import io.redandroid.westerosandbeyond.core_ui.theme.WesterosAndBeyondTheme
import io.redandroid.westerosandbeyond.presentation.modules.house.MainViewModel
import io.redandroid.westerosandbeyond.presentation.modules.house.detail.HouseDetailScreen
import io.redandroid.westerosandbeyond.presentation.modules.house.list.HouseListScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun WesterosAndBeyondScreen() {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
    val mainViewModel = hiltViewModel<MainViewModel>(viewModelStoreOwner = viewModelStoreOwner)

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val title: String by mainViewModel.titleState.collectAsStateWithLifecycle("")

    WesterosAndBeyondTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Surface(
                    shadowElevation = 4.dp
                ) {
                    TopAppBar(
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        navigationIcon = {
                            val detailVisible = currentDestination?.route == Navigation.houseDetailPath

                            AnimatedVisibility(
                                visible = detailVisible,
                                enter = fadeIn(animationSpec = tween(500)) +
                                        expandHorizontally(animationSpec = tween(500)),
                                exit = fadeOut(animationSpec = tween(500)) +
                                        shrinkHorizontally (animationSpec = tween(500))
                            ){
                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(
                                        contentColor = MaterialTheme.colorScheme.onPrimary
                                    ),
                                    onClick = { navController.popBackStack() }
                                ) {
                                    Icon(Icons.Filled.ArrowBack, "backIcon")
                                }
                            }
                        },
                        title = {
                            Text(
                                color = MaterialTheme.colorScheme.onPrimary,
                                text = title
                            )
                        },
                        actions = {
                            IconButton(
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                onClick = { /*TODO*/ }
                            ) {
                                Icon(Icons.Filled.Search, "searchIcon")
                            }
                        }
                    )
                }
            },
            content = { padding ->
                Box(
                    modifier = Modifier.padding(padding)
                ) {
                    WesterosAndBeyondNavHost(
                        navController = navController,
                        mainVm = mainViewModel
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
    startDestination: String = Navigation.houseListPath,
    mainVm: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Navigation.houseListPath) {
            HouseListScreen(
                mainVm = mainVm
            ) { houseUrl ->
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
            HouseDetailScreen(
                mainVm = mainVm
            )
        }
    }
}



