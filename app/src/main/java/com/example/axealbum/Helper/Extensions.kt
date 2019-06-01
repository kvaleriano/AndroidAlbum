package com.example.axealbum.Helper

import android.graphics.Typeface
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun TextView.setAppFont() {
    val typeface = Typeface.createFromAsset(context.assets, "montserrat_light")
    setTypeface(typeface)
}

fun TextView.setAppFontBold() {
    val typeface = Typeface.createFromAsset(context.assets, "montserrat_bold")
    setTypeface(typeface)
}