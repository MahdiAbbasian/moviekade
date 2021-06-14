package com.android.moviekade.business.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CAT_NEW_MOVIE")
data class LocalNewMovie(

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var countryProduct: String? = null,
    var rateImdb: String? = null,
    var categoryName: String? = null,
    var director: String? = null,
    var actorsName: String? = null,
    var persianName: String? = null,
    var published: String? = null,
    var linkImg: String? = null,
    var name: String? = null,
    var rank: String? = null,
    var time: String? = null,
    var genreName: String? = null
)