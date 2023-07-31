package com.davutkarakus.wallpaper_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.davutkarakus.wallpaper_kotlin.R
import com.davutkarakus.wallpaper_kotlin.adapter.FavoriRecyclerAdapter
import com.davutkarakus.wallpaper_kotlin.model.FavoriWallpaperModel
import com.davutkarakus.wallpaper_kotlin.sharedPref.SharedPref
import kotlinx.android.synthetic.main.fragment_favori.*


class FavoriFragment() : Fragment() {
     lateinit var favorirecyclerAdapter: FavoriRecyclerAdapter
     var favoList:List<FavoriWallpaperModel?>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoList= SharedPref().readListFromPref(context)
    }

    override fun onResume() {
        super.onResume()
        favoList=SharedPref().readListFromPref(context)
        val layoutManager= GridLayoutManager(context,2)
        favorirecyclerAdapter=FavoriRecyclerAdapter(favoList)
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
        favorirecyclerAdapter=FavoriRecyclerAdapter(favoList)
        favoriRecycler.layoutManager=layoutManager
        favoriRecycler.adapter=favorirecyclerAdapter
    }

}