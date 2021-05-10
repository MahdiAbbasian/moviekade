package com.android.demomvvm.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.demomvvm.model.data.remote.response.InformationHomeItemAnimationResponse
import com.android.demomvvm.utils.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimationViewModel: ViewModel() {
    val animationResponseLiveData: MutableLiveData<List<InformationHomeItemAnimationResponse>> = MutableLiveData()
    var itemResponses: List<InformationHomeItemAnimationResponse> = ArrayList()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = BaseApplication.api.getAnimationMovie("animation_movie")
                if (response.isSuccessful) {
                    response.body()?.let {
                        itemResponses = it.informationHomeResponse!!
                        animationResponseLiveData.value = it.informationHomeResponse
                    }?.run {
                        Log.v("DEBUG : ", response.body().toString())
                    }
                } else {
                    Log.d("responseNotSucceed", response.message())
                }
            } catch (e: Throwable) {
                Log.d("Error", e.toString())
            }
        }
    }
}