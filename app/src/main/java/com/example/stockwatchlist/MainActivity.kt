package com.example.stockwatchlist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.stockwatchlist.fragments.StocksFragment
import com.example.stockwatchlist.fragments.WatchlistFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.nav_stocks -> {
                    loadFragment(StocksFragment())
                    true
                }
                R.id.nav_watchlist -> {
                    loadFragment(WatchlistFragment())
                    true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.nav_stocks }
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}