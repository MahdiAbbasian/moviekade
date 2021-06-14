package com.android.moviekade.presentation.viewModel

import androidx.lifecycle.*
import com.android.moviekade.presentation.database.DataState
import com.android.moviekade.business.domain.entity.AnimationMovie
import com.android.moviekade.business.usecase.AnimationMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.android.moviekade.presentation.MainState as MainState

@HiltViewModel
class AnimationViewModel @Inject constructor(
    private val animationMovieUseCase: AnimationMovieUseCase
): ViewModel() {

    private val _state = MutableLiveData<MainState<*>>()
    val state: LiveData<MainState<*>> get() = _state

    fun getAnimationMovie(): MutableLiveData<AnimationMovie> {
        viewModelScope.launch(Dispatchers.IO) {
            animationMovieUseCase.invokeAnimationMovie().onEach { dataState ->
                 when (dataState) {
                    is DataState.Error -> {
                        _state.value = dataState.errorMessage?.let { MainState.Error(it) }
                    }
                    is DataState.Loading -> {
                        _state.value = MainState.Loading
                    }
                    is DataState.Success -> {
                        _state.value = MainState.Loaded(dataState.data)
                    }
                }
            }
                .launchIn(viewModelScope)
        }
        return MutableLiveData<AnimationMovie>()
    }

}