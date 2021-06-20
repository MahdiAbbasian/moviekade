package com.android.moviekade.framework.utils

import android.app.Application
import android.graphics.Typeface
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    companion object {
        lateinit var fRegular: Typeface
        lateinit var fBold: Typeface
    }

    override fun onCreate() {
        super.onCreate()
        fRegular = fonts(Fonts.REGULAR)
        fBold = fonts(Fonts.BOLD)
    }

    private fun fonts(which: Fonts): Typeface = Typeface.createFromAsset(this.assets, which.path)

}