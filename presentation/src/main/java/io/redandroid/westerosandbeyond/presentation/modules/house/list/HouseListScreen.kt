package io.redandroid.westerosandbeyond.presentation.modules.house.list

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

private typealias HouseSelectedCallback = (houseUrl: String) -> Unit

@Composable
fun HouseListScreen(
    vm: HouseListViewModel = hiltViewModel(),
    houseSelectedCallback: HouseSelectedCallback
) {
    vm.loadData()

    Button(onClick = { houseSelectedCallback("1") }) {
        Text("Button")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}