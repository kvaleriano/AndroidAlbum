package com.example.axealbum.Helper

import android.graphics.Typeface
import android.support.annotation.LayoutRes
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.axealbum.R

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun TextView.setAppFont() {
    val typeface = ResourcesCompat.getFont(context, R.font.montserrat_light)
    //val typeface = Typeface.createFromAsset(context.assets, "montserrat_light")
    setTypeface(typeface)
}

fun TextView.setAppFontBold() {
    val typeface = ResourcesCompat.getFont(context, R.font.montserrat_bold)
    //val typeface = Typeface.createFromAsset(context.assets, "montserrat_bold")
    setTypeface(typeface)
}