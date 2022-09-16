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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.redandroid.westerosandbeyond.core_ui.R
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
        Pair(stringResource(R.string.module_house_region_label), house.region),
        Pair(stringResource(R.string.module_house_coat_of_arms_label), house.coatOfArms),
        Pair(stringResource(R.string.module_house_words_label), house.words),
        Pair(stringResource(R.string.module_house_titles_label), house.titles.joinToString("\n")),
        Pair(stringResource(R.string.module_house_seats_label), house.seats.joinToString("\n")),
        Pair(stringResource(R.string.module_house_founded_label), house.founded),
        Pair(stringResource(R.string.module_house_died_out_label), house.diedOut),
        Pair(stringResource(R.string.module_house_ancestral_weapons_label), house.ancestralWeapons.joinToString("\n")),
        Pair(stringResource(R.string.module_house_cadet_branches_label), house.cadetBranches.joinToString("\n")),
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
        Text(stringResource(R.string.module_house_detail_error_state_label))
    }
}

@Preview(showBackground = true)
@Composable
fun HousePreview() {
    WesterosAndBeyondTheme {
        HouseDetailSuccess(house = mockedHouse())
    }
}