package com.android.demomvvm.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.demomvvm.model.data.InformationHomeItemNewMovie
import com.android.demomvvm.utils.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewMovieViewModel: ViewModel() {
    val newMovieLiveData: MutableLiveData<List<InformationHomeItemNewMovie>> = MutableLiveData()
    var items: List<InformationHomeItemNewMovie> = ArrayList()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = BaseApplication.api.getNewMovie("new_movie")
                if (response.isSuccessful) {
                    response.body()?.let {
                        items = it.informationHome!!
                        newMovieLiveData.value = it.informationHome
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