package com.davutkarakus.wallpaper_kotlin.model


import com.google.gson.annotations.SerializedName

data class WallPaperModel(
    @SerializedName("MaterialWallpaper")
    val materialWallpaper: List<MaterialWallpaper>?
)