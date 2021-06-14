package com.android.moviekade.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.moviekade.business.data.remote.response.InformationHomeItemTopMovieResponse
import com.android.moviekade.framework.utils.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopMovieViewModel: ViewModel() {
    val topMovieResponseLiveData: MutableLiveData<List<InformationHomeItemTopMovieResponse>> = MutableLiveData()
    var itemResponses: List<InformationHomeItemTopMovieResponse> = ArrayList()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = BaseApplication.api.getTopMovie("top_movie")
                if (response.isSuccessful) {
                    response.body()?.let {
                        itemResponses = it.informationHomeResponse!!
                        topMovieResponseLiveData.value = it.informationHomeResponse
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