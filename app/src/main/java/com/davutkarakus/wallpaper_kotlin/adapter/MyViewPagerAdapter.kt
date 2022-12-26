package com.davutkarakus.wallpaper_kotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.davutkarakus.wallpaper_kotlin.view.natureWallpaperListFragment
import com.davutkarakus.wallpaper_kotlin.view.animalWallpaperListFragment
import com.davutkarakus.wallpaper_kotlin.view.carWallpaperListFragment
import java.lang.RuntimeException

class MyViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
     when(position){
            0 ->  return  natureWallpaperListFragment()
            1 ->  return  animalWallpaperListFragment()
            2 ->  return  carWallpaperListFragment()
          else -> {
              throw RuntimeException("Invalid position :$position")
          }
      }
    }
}