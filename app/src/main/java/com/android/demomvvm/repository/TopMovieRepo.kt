package com.android.demomvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.demomvvm.model.database.MovieKadeDB
import com.android.demomvvm.model.local.LocalTopMovie
import java.io.IOException

class TopMovieRepo(private var context: Context) {
    private val db = MovieKadeDB.getMovieKadeDB(context.applicationContext)
    private val topMovieResponseLiveData: MutableLiveData<List<LocalTopMovie>> = MutableLiveData()

    suspend fun getAllItems(): LiveData<List<LocalTopMovie>> {
        val value = db!!.topMovieDao().getAllTopMovie()
        topMovieResponseLiveData.value = value
        return topMovieResponseLiveData
    }

    suspend fun deleteItem(deleteItemModel: List<LocalTopMovie>) {
        return db!!.topMovieDao().deleteSliderObject(deleteItemModel)
    }

    suspend fun clearAllItems() {
        db!!.topMovieDao().clearTopMovie()
    }

    suspend fun updateItem(updateItemModel: List<LocalTopMovie>) {
        db!!.topMovieDao().updateTopMovie(updateItemModel)
    }

    suspend fun insertAll(list: List<LocalTopMovie>) {
        try {
            for(ob in list) {
                val uid = db!!.topMovieDao().insertTopMovieObject(ob)
                ob.id = uid
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
}