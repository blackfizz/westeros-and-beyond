package io.redandroid.westerosandbeyond.presentation.modules.house.detail

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.mockedHouse
import io.redandroid.westerosandbeyond.presentation.modules.house.detail.HouseDetailState.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HouseDetailScreen(vm: HouseDetailViewModel = hiltViewModel()) {
    val state: HouseDetailState by vm.state.collectAsStateWithLifecycle(Empty)

    when (state) {
        // Why is smart cast not working?
        is Success -> HouseDetailSuccess((state as Success).house)
        is Loading -> HouseDetailLoading()
        is Empty -> HouseDetailEmpty()
        is Error -> HouseDetailError()
    }
}

@Composable
fun HouseDetailSuccess(house: House) {
    Text(house.name)
}

@Composable
fun HouseDetailEmpty() {

}

@Composable
fun HouseDetailLoading() {
    LinearProgressIndicator()
}

@Composable
fun HouseDetailError() {
    Text("Nix gefunden")
}

@Preview(showBackground = true)
@Composable
fun HousePreview() {
    HouseDetailSuccess(house = mockedHouse())
}