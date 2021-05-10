package com.android.demomvvm.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.demomvvm.model.data.remote.response.SeriesItemResponse
import com.android.demomvvm.utils.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeriesViewModel: ViewModel() {
    val seriesLiveDataResponse: MutableLiveData<List<SeriesItemResponse>> = MutableLiveData()
    var itemResponses: List<SeriesItemResponse> = ArrayList()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = BaseApplication.api.getSeries("series")
                if (response.isSuccessful) {
                    response.body()?.let {
                        itemResponses = it.seriesResponses!!
                        seriesLiveDataResponse.value = it.seriesResponses
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