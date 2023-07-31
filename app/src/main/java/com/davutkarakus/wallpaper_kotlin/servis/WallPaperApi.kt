package com.davutkarakus.wallpaper_kotlin.servis

import com.davutkarakus.wallpaper_kotlin.model.NewWallpaperModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WallPaperApi {
    //http://idriscelebi.com/eviller_scorpion/api.php?home
    // BASE_URL http://idriscelebi.com/eviller_scorpion/
    // api.php?home
    @Headers("Authorization: 563492ad6f9170000100000149cad636913642708ad5f90afcb4ec8b")
    @GET("v1/search?")
    fun getWallPaper(@Query("query") query:String ,@Query("per_page") per_page:String):Single<NewWallpaperModel>
}