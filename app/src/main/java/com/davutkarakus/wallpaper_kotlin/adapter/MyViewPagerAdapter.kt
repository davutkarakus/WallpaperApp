package com.davutkarakus.wallpaper_kotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.davutkarakus.wallpaper_kotlin.view.NatureWallpaperListFragment
import com.davutkarakus.wallpaper_kotlin.view.AnimalWallpaperListFragment
import com.davutkarakus.wallpaper_kotlin.view.CarWallpaperListFragment
import java.lang.RuntimeException

class MyViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
     when(position){
            0 ->  return  NatureWallpaperListFragment()
            1 ->  return  AnimalWallpaperListFragment()
            2 ->  return  CarWallpaperListFragment()
          else -> {
              throw RuntimeException("Invalid position :$position")
          }
      }
    }
}