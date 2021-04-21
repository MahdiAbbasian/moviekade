package com.android.demomvvm.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.demomvvm.model.data.SeriesItem
import com.android.demomvvm.utils.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SeriesViewModel: ViewModel() {
    val seriesLiveData: MutableLiveData<List<SeriesItem>> = MutableLiveData()
    var items: List<SeriesItem> = ArrayList()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = BaseApplication.api.getSeries("series")
                if (response.isSuccessful) {
                    response.body()?.let {
                        items = it.series!!
                        seriesLiveData.value = it.series
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