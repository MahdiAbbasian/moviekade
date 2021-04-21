package com.android.demomvvm.utils

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun Context.toast(messege : String) {
    Toast.makeText(this, messege, Toast.LENGTH_LONG).show()
}