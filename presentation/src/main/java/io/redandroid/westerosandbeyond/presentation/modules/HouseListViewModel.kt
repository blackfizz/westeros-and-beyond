package io.redandroid.westerosandbeyond.presentation.modules

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.redandroid.westerosandbeyond.domain.PaginateHousesUseCase
import javax.inject.Inject

@HiltViewModel
class HouseListViewModel @Inject constructor(
    private val paginateHousesUseCase: PaginateHousesUseCase
): ViewModel() {
}