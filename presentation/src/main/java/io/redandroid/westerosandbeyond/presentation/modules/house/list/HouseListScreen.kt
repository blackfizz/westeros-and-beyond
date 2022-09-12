package io.redandroid.westerosandbeyond.presentation.modules.house.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.redandroid.westerosandbeyond.model.modules.house.House

private typealias HouseSelectedCallback = (houseUrl: String) -> Unit

@Composable
fun HouseListScreen(
    vm: HouseListViewModel = hiltViewModel(),
    houseSelectedCallback: HouseSelectedCallback
) {
    vm.loadData()

    val houses : List<House> by vm.houses.observeAsState(listOf())

    HouseList(houses = houses, houseSelectedCallback = houseSelectedCallback)
}

@Composable
fun HouseList(houses: List<House>, houseSelectedCallback: HouseSelectedCallback) {
    LazyColumn {
        items(houses) { house ->
            HouseItem(house = house , houseSelectedCallback = houseSelectedCallback)
        }
    }
}

@Composable
fun HouseItem(house: House, houseSelectedCallback: HouseSelectedCallback) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shadowElevation = 4.dp,
        modifier = Modifier
            .clickable { houseSelectedCallback(house.url) }
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = house.name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HouseList(houses =  listOf()) {

    }
}