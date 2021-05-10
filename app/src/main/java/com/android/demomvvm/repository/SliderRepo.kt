package com.android.demomvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.demomvvm.model.database.MovieKadeDB
import com.android.demomvvm.model.local.LocalSlider
import java.io.IOException

class SliderRepo(private var context: Context) {
    private val db = MovieKadeDB.getMovieKadeDB(context.applicationContext)
    private val sliderResponseLiveData: MutableLiveData<List<LocalSlider>> = MutableLiveData()

    suspend fun getAllItems(): LiveData<List<LocalSlider>> {
        val value = db!!.sliderDao().getAllSlider()
        sliderResponseLiveData.value = value
        return sliderResponseLiveData
    }

    suspend fun deleteItem(deleteItemModel: List<LocalSlider>) {
        return db!!.sliderDao().deleteSliderObject(deleteItemModel)
    }

    suspend fun clearAllItems() {
        db!!.sliderDao().clearSlider()
    }

    suspend fun updateItem(updateItemModel: List<LocalSlider>) {
        db!!.sliderDao().updateSlider(updateItemModel)
    }

    suspend fun insertAll(list: List<LocalSlider>) {
        try {
            for(ob in list) {
                val uid = db!!.sliderDao().insertSliderObject(ob)
                ob.id = uid
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
}