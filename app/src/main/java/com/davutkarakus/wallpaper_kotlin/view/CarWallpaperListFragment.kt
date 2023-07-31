package com.davutkarakus.wallpaper_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.davutkarakus.wallpaper_kotlin.R
import com.davutkarakus.wallpaper_kotlin.adapter.recyclerAdapter
import com.davutkarakus.wallpaper_kotlin.viewmodel.WallPaperViewModel
import kotlinx.android.synthetic.main.fragment_nature_wall_paper_list.recyclerView
import kotlinx.android.synthetic.main.fragment_nature_wall_paper_list.textView


class CarWallpaperListFragment : Fragment() {
    private lateinit var recyclerAdapter: recyclerAdapter
    private lateinit var viewModel: WallPaperViewModel
    var pos:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_wallpaper_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(WallPaperViewModel::class.java)
        viewModel.refreshData("cars","80")
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.bilgiler.observe(viewLifecycleOwner, Observer {
            it?.let {
                val layoutManager= GridLayoutManager(context,2)
                recyclerView.layoutManager=layoutManager
                recyclerAdapter= recyclerAdapter(it)
                if(pos!=null){
                    recyclerView.scrollToPosition(pos!!)
                    pos=null
                }
                recyclerView.adapter=recyclerAdapter
                recyclerView.visibility= View.VISIBLE
            }
        })
        viewModel.hataMesaji.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    recyclerView.visibility=View.INVISIBLE
                    textView.visibility=View.VISIBLE

                }else{
                    recyclerView.visibility=View.VISIBLE
                    textView.visibility=View.INVISIBLE
                }
            }
        })
    }
}