package com.android.demomvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.demomvvm.model.database.MovieKadeDB
import com.android.demomvvm.model.local.LocalAnimationMovie
import com.android.demomvvm.model.local.LocalSeries
import java.io.IOException

class SeriesRepo(private var context: Context) {
    private val db = MovieKadeDB.getMovieKadeDB(context.applicationContext)
    private val seriesResponseLiveData: MutableLiveData<List<LocalSeries>> = MutableLiveData()

    suspend fun getAllItems(): LiveData<List<LocalSeries>> {
        val value = db!!.seriesDao().getAllSeries()
        seriesResponseLiveData.value = value
        return seriesResponseLiveData
    }

    suspend fun deleteItem(deleteItemModel: List<LocalSeries>) {
        return db!!.seriesDao().deleteSeriesObject(deleteItemModel)
    }

    suspend fun clearAllItems() {
        db!!.seriesDao().clearSeries()
    }

    suspend fun updateItem(updateItemModel: List<LocalSeries>) {
        db!!.seriesDao().updateSeries(updateItemModel)
    }

    suspend fun insertAll(list: List<LocalSeries>) {
        try {
            for(ob in list) {
                val uid = db!!.seriesDao().insertSeriesObject(ob)
                ob.idSeries = uid
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
}