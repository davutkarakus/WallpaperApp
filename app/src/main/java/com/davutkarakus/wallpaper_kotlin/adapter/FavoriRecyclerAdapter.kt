package com.davutkarakus.wallpaper_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.davutkarakus.wallpaper_kotlin.R
import com.davutkarakus.wallpaper_kotlin.model.FavoriWallpaperModel
import com.davutkarakus.wallpaper_kotlin.util.gorselIndir
import com.davutkarakus.wallpaper_kotlin.util.placeHolderYap
import com.davutkarakus.wallpaper_kotlin.view.FavoriFragmentDirections
import kotlinx.android.synthetic.main.recycler_row.view.*


class FavoriRecyclerAdapter(var favoList:List<FavoriWallpaperModel?>?):RecyclerView.Adapter<FavoriRecyclerAdapter.RvHolder>(){
    class RvHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_row,parent,false)
        return RvHolder(view)
    }

    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.itemView.imageView.gorselIndir(favoList?.get(position)?.image, placeHolderYap(holder.itemView.context))
        holder.itemView.imageView.setOnClickListener {
            val action=FavoriFragmentDirections.actionFavoriFragmentToFavoriWallpaperAcFragment(position)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        if(favoList?.size==null){
            return 0
        }else{
            return favoList!!.size
        }

    }
}