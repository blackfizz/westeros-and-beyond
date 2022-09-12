package io.redandroid.westerosandbeyond.presentation.modules.house.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.redandroid.westerosandbeyond.core.DispatcherProvider
import io.redandroid.westerosandbeyond.domain.modules.house.PaginateHousesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseListViewModel @Inject constructor(
    private val paginateHousesUseCase: PaginateHousesUseCase,
    private val dispatcherProvider: DispatcherProvider
): ViewModel() {

    fun loadData() {
        viewModelScope.launch(dispatcherProvider.main) {
            val houses = paginateHousesUseCase()
            Log.d("HouseList", "Houses: $houses")
        }
    }

}