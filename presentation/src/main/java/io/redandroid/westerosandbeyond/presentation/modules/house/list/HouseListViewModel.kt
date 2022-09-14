package io.redandroid.westerosandbeyond.presentation.modules.house.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.redandroid.westerosandbeyond.domain.modules.house.PaginateHousesUseCase
import io.redandroid.westerosandbeyond.model.modules.house.House
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HouseListViewModel @Inject constructor(
    private val paginateHousesUseCase: PaginateHousesUseCase
): ViewModel() {
    val houses : Flow<PagingData<House>> = paginateHousesUseCase().flow.cachedIn(viewModelScope)
}