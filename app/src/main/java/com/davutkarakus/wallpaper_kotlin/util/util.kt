package com.davutkarakus.wallpaper_kotlin.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.davutkarakus.wallpaper_kotlin.R

fun ImageView.buyukGorselIndir(url: String?,placeholder: CircularProgressDrawable){
    val options=RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher)
    Glide.with(context).setDefaultRequestOptions(options).load("$url").into(this)
}
fun ImageView.gorselIndir(url:String?,placeholder: CircularProgressDrawable){
    val options=RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher)
    Glide.with(context).setDefaultRequestOptions(options).load("$url").into(this)
}

fun placeHolderYap(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}