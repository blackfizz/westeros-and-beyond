package io.redandroid.westerosandbeyond.presentation.modules.house

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _titleState = MutableStateFlow("")
    val titleState : Flow<String> = _titleState

    var title: String
        set(value) {
            _titleState.value = value
        }
        get() = _titleState.value
}