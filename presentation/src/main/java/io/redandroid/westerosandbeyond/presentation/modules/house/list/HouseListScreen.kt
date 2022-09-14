package io.redandroid.westerosandbeyond.presentation.modules.house.list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import io.redandroid.westerosandbeyond.core_ui.composables.CenteredContent
import io.redandroid.westerosandbeyond.model.modules.house.House
import io.redandroid.westerosandbeyond.model.modules.house.mockedHouse
import kotlinx.coroutines.flow.Flow

private typealias HouseSelectedCallback = (houseUrl: String) -> Unit

@Composable
fun HouseListScreen(
    vm: HouseListViewModel = hiltViewModel(),
    houseSelectedCallback: HouseSelectedCallback,
) {

    HouseList(houses = vm.houses, houseSelectedCallback = houseSelectedCallback)
}

@Composable
fun HouseList(houses: Flow<PagingData<House>>, houseSelectedCallback: HouseSelectedCallback) {
    val pagingItems = houses.collectAsLazyPagingItems()

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(pagingItems) { house ->
                house?.let {
                    HouseItem(house = house, houseSelectedCallback = houseSelectedCallback)
                }
            }

            when {
                pagingItems.loadState.refresh is LoadState.Loading -> {
                    item {
                        CenteredContent(modifier = Modifier.fillParentMaxSize()) {
                            LinearProgressIndicator()
                        }
                    }
                }
                pagingItems.loadState.append is LoadState.Loading -> {
                    item {
                        LinearProgressIndicator(
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HouseItem(house: House, houseSelectedCallback: HouseSelectedCallback) {
    Box(
        modifier = Modifier
            .clickable { houseSelectedCallback(house.url) }
            .fillMaxWidth()
            .padding(8.dp)
            .border(3.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(6.dp))
    ) {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            shadowElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = house.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )

                if (house.region.isNotBlank()) {
                    Text(
                        text = house.region,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HouseItem(house = mockedHouse()) {}
}