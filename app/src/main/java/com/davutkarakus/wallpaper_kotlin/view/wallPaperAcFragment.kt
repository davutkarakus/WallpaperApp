package com.davutkarakus.wallpaper_kotlin.view

import android.app.Activity
import android.app.WallpaperManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.davutkarakus.wallpaper_kotlin.R
import com.davutkarakus.wallpaper_kotlin.model.*
import com.davutkarakus.wallpaper_kotlin.sharedPref.sharedPref
import com.davutkarakus.wallpaper_kotlin.util.buyukGorselIndir
import com.davutkarakus.wallpaper_kotlin.util.placeHolderYap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_row.*


class wallPaperAcFragment() : Fragment() {
    private lateinit var list:newWallpaperModel
    private  var position:Int=0
    var favoList=ArrayList<favoriWallpaperModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wall_paper_ac, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerForContextMenu(imageView)
        arguments?.let {
            list = wallPaperAcFragmentArgs.fromBundle(it).url
            position = wallPaperAcFragmentArgs.fromBundle(it).position
        }

        buyukGorselYap()
        context?.let {

            imageView.setOnTouchListener(object : OnSwipeTouchListener(it) {
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
                    if (position != list.photos.size - 1) {
                        position++
                        buyukGorselYap()
                    }
                }
                override fun onSwipeBottom() {
                    super.onSwipeBottom()
                    val navBuilder = NavOptions.Builder()
                    val navOptions: NavOptions = navBuilder.setPopUpTo(R.id.wallPaperAcFragment,true).build()
                    val action=wallPaperAcFragmentDirections.actionWallPaperAcFragmentToWallPaperListFragment(position)
                    Navigation.findNavController(view).navigate(action,navOptions)
                }
            })
        }


    }
    fun buyukGorselYap(){
        imageView.buyukGorselIndir(
            list.photos[position].src.portrait,
            placeHolderYap(requireContext())
        )
    }

    open class OnSwipeTouchListener(ctx: Context) : OnTouchListener {

        private val gestureDetector: GestureDetector

        companion object {

            private val SWIPE_THRESHOLD = 100
            private val SWIPE_VELOCITY_THRESHOLD = 100
        }

        init {
            gestureDetector = GestureDetector(ctx, GestureListener())
        }

        override fun onTouch(v: View, event: MotionEvent): Boolean {

            return gestureDetector.onTouchEvent(event)
        }


        private inner class GestureListener : SimpleOnGestureListener() {


            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent?) {
                super.onLongPress(e)
                deneme()
            }
            override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                var result = false
                try {
                    val diffY = e2.y - e1.y
                    val diffX = e2.x - e1.x
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight()
                            } else {
                                onSwipeLeft()
                            }
                            result = true
                        }
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom()
                        } else {
                            onSwipeTop()
                        }
                        result = true
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
                return result
            }


        }

        open fun deneme() {

        }

        open fun onSwipeRight() {}

        open fun onSwipeLeft() {}

        open fun onSwipeTop() {}

        open fun onSwipeBottom() {}

    }
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        MenuInflater(context).inflate(R.menu.main_menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            R.id.arka_plan->{
                context?.let {
                    Glide.with(it).asBitmap().load(list.photos[position].src.portrait).into(object:SimpleTarget<Bitmap?>(){
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap?>?
                        ) {
                         /*   val wallpapermanager = WallpaperManager.getInstance(it)
                            wallpapermanager.setBitmap(resource)
                            val action=wallPaperAcFragmentDirections.actionWallPaperAcFragmentSelf(position,list)
                            Navigation.findNavController(view).navigate(action)

                          */
                            val wallpapermanager = WallpaperManager.getInstance(it)
                            wallpapermanager.setBitmap(resource)
                            val navBuilder = NavOptions.Builder()
                            val navOptions: NavOptions = navBuilder.setPopUpTo(R.id.wallPaperAcFragment,true).build()
                            val action=wallPaperAcFragmentDirections.actionWallPaperAcFragmentSelf(position,list)
                            Navigation.findNavController(view!!).navigate(action,navOptions)
                        }
                    })
                }
                return true
            }
            R.id.favori-> {Toast.makeText(context,"Favoriye Eklendi",Toast.LENGTH_LONG).show()
                favoList.add(favoriWallpaperModel(list.photos[position].src.portrait))
                sharedPref().writeListInPref(context,favoList)
                return true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }
    }


}