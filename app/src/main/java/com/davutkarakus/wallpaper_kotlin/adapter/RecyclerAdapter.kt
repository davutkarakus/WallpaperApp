package com.davutkarakus.wallpaper_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.davutkarakus.wallpaper_kotlin.R
import com.davutkarakus.wallpaper_kotlin.model.newWallpaperModel
import com.davutkarakus.wallpaper_kotlin.model.wallPaperModel
import com.davutkarakus.wallpaper_kotlin.util.gorselIndir
import com.davutkarakus.wallpaper_kotlin.util.placeHolderYap
import com.davutkarakus.wallpaper_kotlin.view.wallPaperListFragment
import com.davutkarakus.wallpaper_kotlin.view.wallPaperListFragmentDirections
import kotlinx.android.synthetic.main.fragment_wall_paper_ac.view.*
import kotlinx.android.synthetic.main.recycler_row.view.*
import kotlinx.android.synthetic.main.recycler_row.view.imageView

class recyclerAdapter(val wpList:newWallpaperModel):RecyclerView.Adapter<recyclerAdapter.RvHolder>() {


    class RvHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_row,parent,false)
        return RvHolder(view)
    }

    override fun onBindViewHolder(holder: RvHolder, position: Int) {
    holder.itemView.imageView.gorselIndir(wpList.photos[position].src.medium, placeHolderYap(holder.itemView.context))
        holder.itemView.imageView.setOnClickListener {
            val action=wallPaperListFragmentDirections.actionWallPaperListFragmentToWallPaperAcFragment(position,wpList)
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int {

         return wpList.photos.size
    }

}