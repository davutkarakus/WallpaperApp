package com.davutkarakus.wallpaper_kotlin.view

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.davutkarakus.wallpaper_kotlin.R
import com.davutkarakus.wallpaper_kotlin.adapter.MyViewPagerAdapter
import com.davutkarakus.wallpaper_kotlin.adapter.recyclerAdapter
import com.davutkarakus.wallpaper_kotlin.viewmodel.wallPaperViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_wall_paper_ac.*
import kotlinx.android.synthetic.main.fragment_wall_paper_list.*


class wallPaperListFragment : Fragment() {

    private lateinit var recyclerAdapter: recyclerAdapter
    private lateinit var viewModel: wallPaperViewModel
    var pos:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pos=wallPaperListFragmentArgs.fromBundle(it).position
        }


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wall_paper_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var tabLayoutMediator=TabLayoutMediator(tabLayout_1,deneme_viewpager){tab,poisiton->
            when(poisiton){
                0 -> tab.text="Nature"
                1 -> tab.text="Animal"
                2 -> tab.text="Car"
            }
        }
        deneme_viewpager.adapter= fragmentManager?.let {
            MyViewPagerAdapter(childFragmentManager,lifecycle) }
        tabLayoutMediator.attach()
    }


}