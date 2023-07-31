package com.davutkarakus.wallpaper_kotlin.sharedPref

import android.content.Context
import com.davutkarakus.wallpaper_kotlin.model.FavoriWallpaperModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SharedPref {
  private val LIST_KEY: String = "list_key100"
    fun deleteListInPref(context: Context?,position:Int,list: List<FavoriWallpaperModel?>){
        val pref = context?.getSharedPreferences("DATA", Context.MODE_PRIVATE)
        val jsonString = pref?.getString(LIST_KEY, "")
        val gson = Gson()
        val type: Type = object : TypeToken<List<FavoriWallpaperModel>>() {}.type
        val list: List<FavoriWallpaperModel?>? =gson.fromJson<List<FavoriWallpaperModel>?>(jsonString, type)
        val list_2=list!!.toMutableList()
        list_2.removeAt(position)
        val editor = pref?.edit()
        editor?.clear()?.apply()
        writeListInPref(context,list_2)
    }
    fun writeListInPref(context: Context?, list: List<FavoriWallpaperModel?>) {
        val gson = Gson()
        val allData:List<FavoriWallpaperModel?>
        if(readListFromPref(context)!=null){
                allData=readListFromPref(context)!!+list[list.size-1]
        }else{
            allData=list
        }
        val jsonString = gson.toJson(allData)
        val pref = context?.getSharedPreferences("DATA", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putString(LIST_KEY, jsonString)
        editor?.apply()
    }

    fun readListFromPref(context: Context?): List<FavoriWallpaperModel?>?{
        val pref = context?.getSharedPreferences("DATA", Context.MODE_PRIVATE)
        val jsonString = pref?.getString(LIST_KEY, "")
        val gson = Gson()
        val type: Type = object : TypeToken<List<FavoriWallpaperModel>>() {}.type
        val list: List<FavoriWallpaperModel?>? =gson.fromJson<List<FavoriWallpaperModel>?>(jsonString, type)
        return list
    }

}