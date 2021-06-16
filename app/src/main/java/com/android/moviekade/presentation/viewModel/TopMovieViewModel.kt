package com.android.moviekade.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.moviekade.business.domain.entity.TopMovie
import com.android.moviekade.business.usecase.TopMovieUseCase
import com.android.moviekade.presentation.MainState
import com.android.moviekade.presentation.database.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopMovieViewModel @Inject constructor(
    private val topMovieUseCase: TopMovieUseCase
): ViewModel() {

    private val _state = MutableLiveData<MainState<*>>()
    val state: LiveData<MainState<*>> get() = _state

    fun getTopMovie(): MutableLiveData<TopMovie> {
        viewModelScope.launch(Dispatchers.IO) {
            topMovieUseCase.invokeTopMovie().onEach { dataState ->
                when(dataState) {
                    is DataState.Error -> {
                        _state.value = dataState.errorMessage?.let { MainState.Error(it) }
                    }
                    DataState.Loading -> {
                        _state.value = MainState.Loading
                    }
                    is DataState.Success -> {
                        _state.value = MainState.Loaded(dataState.data)
                    }
                }
            }
                .launchIn(viewModelScope)
        }
        return MutableLiveData<TopMovie>()
    }
}