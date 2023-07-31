package com.davutkarakus.wallpaper_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.davutkarakus.wallpaper_kotlin.R
import com.davutkarakus.wallpaper_kotlin.adapter.MyViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_wall_paper_list.*


class MainFragment : Fragment() {
    var pos:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pos=MainFragmentArgs.fromBundle(it).position
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
        val tabLayoutMediator=TabLayoutMediator(tabLayout_1,deneme_viewpager){tab,poisiton->
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