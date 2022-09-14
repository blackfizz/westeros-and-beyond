package io.redandroid.westerosandbeyond.presentation.modules.house.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.redandroid.westerosandbeyond.core.disptacher.DispatcherProvider
import io.redandroid.westerosandbeyond.core.navigation.Navigation
import io.redandroid.westerosandbeyond.domain.contracts.HouseRepository
import io.redandroid.westerosandbeyond.model.modules.house.House
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseDetailViewModel @Inject constructor(
    private val repository: HouseRepository,
    private val dispatcherProvider: DispatcherProvider,
    savedState: SavedStateHandle
): ViewModel() {

    //==============================================================================================
    // Member
    //==============================================================================================

    val houseUrl: String = savedState.get<String>(Navigation.houseUrlParam).orEmpty()

    private val _state = MutableStateFlow<HouseDetailState>(HouseDetailState.Empty)
    val state: Flow<HouseDetailState> = _state

    //==============================================================================================
    // Initialize
    //==============================================================================================

    init {
        loadHouse()
    }

    //==============================================================================================
    // Methods
    //==============================================================================================

    private fun loadHouse() {
        if (houseUrl.isBlank()) {
            changeState(HouseDetailState.Error)
        } else {
            changeState(HouseDetailState.Loading)

            viewModelScope.launch(dispatcherProvider.main) {
                val house = repository.getHouse(houseUrl)

                if (house != null) {
                    changeState(HouseDetailState.Success(house))
                } else {
                    changeState(HouseDetailState.Error)
                }
            }
        }
    }

    private fun changeState(newState: HouseDetailState) {
        _state.value = newState
    }
}

sealed class HouseDetailState {
    class Success(val house: House): HouseDetailState()
    object Loading: HouseDetailState()
    object Error: HouseDetailState()
    object Empty: HouseDetailState()
}