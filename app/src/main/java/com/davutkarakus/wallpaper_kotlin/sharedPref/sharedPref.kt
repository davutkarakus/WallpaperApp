package com.davutkarakus.wallpaper_kotlin.sharedPref

import android.content.Context
import com.davutkarakus.wallpaper_kotlin.model.favoriWallpaperModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type


class sharedPref {
  private val LIST_KEY: String = "list_key100"
    fun deleteListInPref(context: Context?,position:Int,list: List<favoriWallpaperModel?>){
   /*     val jsArray = JSONArray()
        val gson = Gson()
        val jsonString = gson.toJson(list)
        val pref = context?.getSharedPreferences("DATA", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.remove(jsonString[position].toString())
        editor?.apply()
        println()


    */
        val pref = context?.getSharedPreferences("DATA", Context.MODE_PRIVATE)
        val jsonString = pref?.getString(LIST_KEY, "")
        val gson = Gson()
        val type: Type = object : TypeToken<List<favoriWallpaperModel>>() {}.type
        val list: List<favoriWallpaperModel?>? =gson.fromJson<List<favoriWallpaperModel>?>(jsonString, type)
        val list_2=list!!.toMutableList()
        list_2.removeAt(position)
        val editor = pref?.edit()
        editor?.clear()?.apply()
        writeListInPref(context,list_2)
    }
    fun writeListInPref(context: Context?, list: List<favoriWallpaperModel?>) {
        val gson = Gson()
        var allData:List<favoriWallpaperModel?>
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

    fun readListFromPref(context: Context?): List<favoriWallpaperModel?>?{
        val pref = context?.getSharedPreferences("DATA", Context.MODE_PRIVATE)
        val jsonString = pref?.getString(LIST_KEY, "")
        val gson = Gson()
        val type: Type = object : TypeToken<List<favoriWallpaperModel>>() {}.type
        val list: List<favoriWallpaperModel?>? =gson.fromJson<List<favoriWallpaperModel>?>(jsonString, type)
        return list
    }

}