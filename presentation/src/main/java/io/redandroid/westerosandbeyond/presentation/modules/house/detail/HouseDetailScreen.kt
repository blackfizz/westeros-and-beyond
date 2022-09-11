package io.redandroid.westerosandbeyond.presentation.modules.house.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HouseDetailScreen(houseUrl: String? = null, vm: HouseDetailViewModel = hiltViewModel()) {
    Text("Url: $houseUrl")
}