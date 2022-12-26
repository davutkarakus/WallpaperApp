package com.davutkarakus.wallpaper_kotlin.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.davutkarakus.wallpaper_kotlin.R
import com.davutkarakus.wallpaper_kotlin.adapter.favoriRecycler
import com.davutkarakus.wallpaper_kotlin.model.favoriWallpaperModel
import com.davutkarakus.wallpaper_kotlin.sharedPref.sharedPref
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_favori.*


class favoriFragment() : Fragment() {
     lateinit var favorirecyclerAdapter: favoriRecycler
     var favoList:List<favoriWallpaperModel?>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoList= sharedPref().readListFromPref(context)
    }

    override fun onResume() {
        super.onResume()
        favoList=sharedPref().readListFromPref(context)
        val layoutManager= GridLayoutManager(context,2)
        favorirecyclerAdapter=favoriRecycler(favoList)
        favoriRecycler.layoutManager=layoutManager
        favoriRecycler.adapter=favorirecyclerAdapter
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favori, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager= GridLayoutManager(context,2)
        favorirecyclerAdapter=favoriRecycler(favoList)
        favoriRecycler.layoutManager=layoutManager
        favoriRecycler.adapter=favorirecyclerAdapter
    }

}