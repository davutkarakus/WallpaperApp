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
import com.davutkarakus.wallpaper_kotlin.viewmodel.wallPaperViewModel
import kotlinx.android.synthetic.main.fragment_animal_wall_paper_list.*

class animalWallpaperListFragment : Fragment() {

    private lateinit var recyclerAdapter: recyclerAdapter
    private lateinit var viewModel: wallPaperViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animal_wall_paper_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(wallPaperViewModel::class.java)
        viewModel.refreshData("animal","80")
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.bilgiler.observe(viewLifecycleOwner, Observer {
            it?.let {
                var layoutManager= GridLayoutManager(context,2)
                recyclerVieww.layoutManager=layoutManager
                recyclerAdapter= recyclerAdapter(it)
                /*        if(pos!=null){
                            recyclerView.scrollToPosition(pos!!)
                            pos=null
                        }


                 */
                recyclerVieww.adapter=recyclerAdapter
                recyclerVieww.visibility= View.VISIBLE
            }
        })
        viewModel.hataMesaji.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    recyclerVieww.visibility=View.INVISIBLE
                    textView.visibility=View.VISIBLE

                }else{
                    recyclerVieww.visibility=View.VISIBLE
                    textView.visibility=View.INVISIBLE
                }
            }
        })
    }
}