package io.redandroid.westerosandbeyond.presentation.modules.house.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.redandroid.westerosandbeyond.core_ui.composables.CenteredContent
import io.redandroid.westerosandbeyond.core_ui.theme.WesterosAndBeyondTheme
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
    val valuePairs = listOf(
        Pair("Region", house.region),
        Pair("Coat of Arms", house.coatOfArms),
        Pair("Words", house.words),
        Pair("Titles", house.titles.joinToString("\n")),
        Pair("Seats", house.seats.joinToString("\n")),
        Pair("Founded", house.founded),
        Pair("Died Out", house.diedOut),
        Pair("Ancestral Weapons", house.ancestralWeapons.joinToString("\n")),
        Pair("Cadet Branches", house.cadetBranches.joinToString("\n")),
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = house.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )

            for (pair in valuePairs) {
                DetailEntry(pair)
            }
        }
    }
}

@Composable
fun HouseDetailEmpty() {

}

@Composable
fun HouseDetailLoading() {
    CenteredContent {
        LinearProgressIndicator()
    }
}

@Composable
fun HouseDetailError() {
    CenteredContent {
        Text("House could not be found")
    }
}

@Preview(showBackground = true)
@Composable
fun HousePreview() {
    WesterosAndBeyondTheme {
        HouseDetailSuccess(house = mockedHouse())
    }
}