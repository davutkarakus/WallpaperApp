package com.davutkarakus.wallpaper_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.davutkarakus.wallpaper_kotlin.R
import com.davutkarakus.wallpaper_kotlin.adapter.MyViewPagerAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_wall_paper_list.*

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    lateinit var drawerLayout: DrawerLayout
    lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar=findViewById(R.id.toolbar)
       navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController=navHostFragment.navController
       drawerLayout=findViewById(R.id.drawerLayout)
        appBarConfiguration= AppBarConfiguration(navController.graph,drawerLayout)
        toolbar.setupWithNavController(navController,appBarConfiguration)
        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
       return navController.navigateUp() || return super.onSupportNavigateUp()
    }


}