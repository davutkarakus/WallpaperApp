package com.davutkarakus.wallpaper_kotlin.model


import com.google.gson.annotations.SerializedName

data class MaterialWallpaper(
    @SerializedName("cat_id")
    val catİd: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("image_date")
    val imageDate: String?
)