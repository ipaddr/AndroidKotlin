package com.example.androidkotlin.day1.navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import androidx.navigation.ui.*
import com.example.navigationcomponent.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.example.androidkotlin.R

class NavigationComponentActivity: AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var toolbar: Toolbar
    lateinit var navView: NavigationView
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_component)

        staticNavigation()
        //dynamicNavigation()
        //navHostFragmentProgramatically()
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
                || navController.navigateUp(appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.navcomponent_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
                || item.onNavDestinationSelected(navController)
    }

    private fun staticNavigation(){
        drawerLayout = findViewById(R.id.drawerlayout)

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar)

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        navView = findViewById(R.id.nav_view)

        bottomNav = findViewById(R.id.bottom_nav)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        toolbar.setupWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        bottomNav.setupWithNavController(navController)
    }

    private fun dynamicNavigation(){
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        navController.graph = navController.createGraph(startDestination =
        Constant.nav_routes.MAIN_FRAGMENT){
            fragment<NavigationComponentMainFragment>(Constant.nav_routes.MAIN_FRAGMENT){
                label = "Main Fragment"
            }
            fragment<BankingFragment>(Constant.nav_routes.BANKING_FRAGMENT){
                label = "Banking Fragment"
            }
            fragment<ChooseAmountFragment>(Constant.nav_routes.CHOOSE_AMOUNT_FRAGMENT){
                label = "Choose Amount"
            }
            fragment<ChoseRecepientFragment>(Constant.nav_routes.CHOOSE_RECEPIENT_FRAGMENT)
            fragment<ViewBalanceFragment>(Constant.nav_routes.VIEW_BALANCE_FRAGMENT)
        }
    }

    private fun navHostFragmentProgramatically(){
        val finalHost = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, finalHost)
            .setPrimaryNavigationFragment(finalHost)
            .commit()
    }

}