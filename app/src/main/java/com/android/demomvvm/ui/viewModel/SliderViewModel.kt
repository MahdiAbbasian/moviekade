package com.android.demomvvm.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.demomvvm.model.data.SliderItem
import com.android.demomvvm.utils.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SliderViewModel: ViewModel() {
    val sliderLiveData: MutableLiveData<List<SliderItem>> = MutableLiveData()
    var items: ArrayList<SliderItem> = ArrayList()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = BaseApplication.api.getSlider()
                if (response.isSuccessful) {
                    response.body()?.let {
                        items = it.slider!!
                        sliderLiveData.value = it.slider
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