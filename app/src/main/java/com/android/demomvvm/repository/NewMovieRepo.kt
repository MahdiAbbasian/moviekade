package com.android.demomvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.demomvvm.model.database.MovieKadeDB
import com.android.demomvvm.model.local.LocalNewMovie
import java.io.IOException

class NewMovieRepo(private var context: Context) {
    private val db = MovieKadeDB.getMovieKadeDB(context.applicationContext)
    private val newMovieResponseLiveData: MutableLiveData<List<LocalNewMovie>> = MutableLiveData()

    suspend fun getAllItems(): LiveData<List<LocalNewMovie>> {
        val value = db!!.newMovieDao().getAllNewMovie()
        newMovieResponseLiveData.value = value
        return newMovieResponseLiveData
    }

    suspend fun deleteItem(deleteItemModel: List<LocalNewMovie>) {
        return db!!.newMovieDao().deleteNewMovieObject(deleteItemModel)
    }

    suspend fun clearAllItems() {
        db!!.newMovieDao().clearNewMovie()
    }

    suspend fun updateItem(updateItemModel: List<LocalNewMovie>) {
        db!!.newMovieDao().updateNewMovie(updateItemModel)
    }

    suspend fun insertAll(list: List<LocalNewMovie>) {
        try {
            for(ob in list) {
                val uid = db!!.newMovieDao().insertNewMovieObject(ob)
                ob.id = uid
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
}