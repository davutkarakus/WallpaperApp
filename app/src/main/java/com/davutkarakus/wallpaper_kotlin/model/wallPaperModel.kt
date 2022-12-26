package com.davutkarakus.wallpaper_kotlin.model


import com.google.gson.annotations.SerializedName

data class wallPaperModel(
    @SerializedName("MaterialWallpaper")
    val materialWallpaper: List<MaterialWallpaper>?
)