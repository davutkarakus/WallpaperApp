package com.davutkarakus.wallpaper_kotlin.servis

import com.davutkarakus.wallpaper_kotlin.model.newWallpaperModel
import com.davutkarakus.wallpaper_kotlin.model.wallPaperModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WallPaperApiServis {
    //http://idriscelebi.com/eviller_scorpion/api.php?home
    // BASE_URL http://idriscelebi.com/eviller_scorpion/
    // api.php?home
    //https://api.pexels.com/

    val BASE_URL="https://api.pexels.com/"
    val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WallPaperApi::class.java)

    fun getData(query:String,per_page:String):Single<newWallpaperModel>{
        return api.getWallPaper(query,per_page)
    }
}