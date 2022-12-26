package com.davutkarakus.wallpaper_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davutkarakus.wallpaper_kotlin.model.newWallpaperModel
import com.davutkarakus.wallpaper_kotlin.model.wallPaperModel

import com.davutkarakus.wallpaper_kotlin.servis.WallPaperApiServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class wallPaperViewModel:ViewModel() {
    var bilgiler=MutableLiveData<newWallpaperModel>()
    var hataMesaji=MutableLiveData<Boolean>()
    private val wallPaperApiServis=WallPaperApiServis()
    val disposable=CompositeDisposable()

    fun refreshData(query:String,per_page:String){
        verileriInternettenAl(query,per_page)
    }

    private fun verileriInternettenAl(query:String,per_page:String) {
        hataMesaji.value=false
        disposable.add(
            wallPaperApiServis.getData(query,per_page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<newWallpaperModel>(){
                    override fun onSuccess(t: newWallpaperModel) {
                        bilgiler.value=t
                        hataMesaji.value=false
                    }

                    override fun onError(e: Throwable) {
                        hataMesaji.value=true
                        e.printStackTrace()
                    }

                })
        )
    }
}