package com.android.moviekade.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.moviekade.business.data.remote.response.InformationHomeItemNewMovieResponse
import com.android.moviekade.framework.utils.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewMovieViewModel: ViewModel() {
    val newMovieResponseLiveData: MutableLiveData<List<InformationHomeItemNewMovieResponse>> = MutableLiveData()
    var itemResponses: List<InformationHomeItemNewMovieResponse> = ArrayList()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = BaseApplication.api.getNewMovie("new_movie")
                if (response.isSuccessful) {
                    response.body()?.let {
                        itemResponses = it.informationHomeResponse!!
                        newMovieResponseLiveData.value = it.informationHomeResponse
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