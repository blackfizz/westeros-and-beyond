package io.redandroid.westerosandbeyond.presentation.modules.house.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.redandroid.westerosandbeyond.core.DispatcherProvider
import io.redandroid.westerosandbeyond.domain.modules.house.PaginateHousesUseCase
import io.redandroid.westerosandbeyond.model.modules.house.House
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseListViewModel @Inject constructor(
    private val paginateHousesUseCase: PaginateHousesUseCase,
    private val dispatcherProvider: DispatcherProvider
): ViewModel() {

    private val _houses = MutableLiveData(listOf<House>())
    val houses : LiveData<List<House>> = _houses

    fun loadData() {
        viewModelScope.launch(dispatcherProvider.main) {
            val receivedHouses = paginateHousesUseCase()
            Log.d("HouseList", "Houses: $receivedHouses")

            _houses.postValue(receivedHouses)
        }
    }

}