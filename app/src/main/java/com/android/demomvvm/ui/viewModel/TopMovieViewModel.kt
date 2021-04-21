package com.android.demomvvm.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.demomvvm.model.data.InformationHomeItemAnimation
import com.android.demomvvm.model.data.InformationHomeItemTopMovie
import com.android.demomvvm.utils.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TopMovieViewModel: ViewModel() {
    val topMovieLiveData: MutableLiveData<List<InformationHomeItemTopMovie>> = MutableLiveData()
    var items: List<InformationHomeItemTopMovie> = ArrayList()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = BaseApplication.api.getTopMovie("top_movie")
                if (response.isSuccessful) {
                    response.body()?.let {
                        items = it.informationHome!!
                        topMovieLiveData.value = it.informationHome
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