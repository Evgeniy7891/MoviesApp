package ru.cft.movieapp.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.ActivityMainBinding
import ru.cft.movieapp.util.MAIN

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        MAIN = this

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.searchFragment,
                R.id.accountFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomMenu.setupWithNavController(navController)
        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_favorite) navController.navigate(R.id.action_mainFragment_to_favoriteFragment) else navController.popBackStack()

        return super.onOptionsItemSelected(item)
    }
}
