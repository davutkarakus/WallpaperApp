package com.davutkarakus.wallpaper_kotlin.view

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.davutkarakus.wallpaper_kotlin.R
import com.davutkarakus.wallpaper_kotlin.model.FavoriWallpaperModel
import com.davutkarakus.wallpaper_kotlin.sharedPref.SharedPref
import com.davutkarakus.wallpaper_kotlin.util.buyukGorselIndir
import com.davutkarakus.wallpaper_kotlin.util.placeHolderYap
import kotlinx.android.synthetic.main.recycler_row.*


class FavoriDetailFragment : Fragment() {

    private  var position:Int=0
    var favoList:List<FavoriWallpaperModel?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favori_wallpaper_ac, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerForContextMenu(imageView)
        favoList= SharedPref().readListFromPref(context)
        arguments?.let {
        position=FavoriDetailFragmentArgs.fromBundle(it).position
        }
        buyukGorselYap()
        context?.let {

            imageView.setOnTouchListener(object : WallPaperDetailFragment.OnSwipeTouchListener(it) {
                override fun onSwipeRight() {
                    super.onSwipeRight()
                    if (position != 0) {
                        position--
                        buyukGorselYap()
                    }
                }
                override fun deneme() {
                    super.deneme()
                    imageView.showContextMenu()
                }
                override fun onSwipeLeft() {
                    super.onSwipeLeft()
                    println()
                    if (position != favoList!!.size - 1) {
                        position++
                        buyukGorselYap()
                    }
                }

                override fun onSwipeBottom() {
                    super.onSwipeBottom()
                    val navBuilder = NavOptions.Builder()
                    val navOptions: NavOptions = navBuilder.setPopUpTo(R.id.FavoriFragment,true).build()
                    val action=FavoriDetailFragmentDirections.actionFavoriWallpaperAcFragmentToFavoriFragment()
                    Navigation.findNavController(view).navigate(action,navOptions)
                }
            })
        }
    }
    fun buyukGorselYap(){
        imageView.buyukGorselIndir(
            favoList?.get(position)?.image,
            placeHolderYap(requireContext())
        )
    }
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        MenuInflater(context).inflate(R.menu.main_menu_2,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.arka_plan_yap_2->{
                context?.let {
                    Glide.with(it).asBitmap().load(favoList!![position]!!.image).into(object:
                        SimpleTarget<Bitmap?>(){
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap?>?
                        ) {
                            val wallpapermanager = WallpaperManager.getInstance(it)
                            wallpapermanager.setBitmap(resource)
                        }
                    })
                }
                return true
            }
            R.id.favori_cikar-> {
                SharedPref().deleteListInPref(context,position,favoList!!)
                view?.let { Navigation.findNavController(it).popBackStack() }
                return true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }
    }
}
