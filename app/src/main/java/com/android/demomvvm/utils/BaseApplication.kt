package com.android.demomvvm.utils

import android.app.Application
import android.graphics.Typeface
import com.android.demomvvm.network.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseApplication : Application() {
    companion object {
        private const val baserUrl = "******"
        lateinit var fRegular: Typeface
        lateinit var fBold: Typeface
        lateinit var api: Api
    }
        override fun onCreate() {
            super.onCreate()
            initFont()
            setUpRetrofit()
        }

        fun fonts(which: Fonts): Typeface = Typeface.createFromAsset(this.assets, which.path)

        private fun initFont() {
            fRegular = fonts(Fonts.REGULAR)
            fBold = fonts(Fonts.BOLD)
        }

        private fun setUpRetrofit() {
            api = Retrofit.Builder()
                    .baseUrl(baserUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(Api::class.java)
        }
}