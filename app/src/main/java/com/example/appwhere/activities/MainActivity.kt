package com.example.appwhere.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appwhere.R
import com.example.appwhere.bases.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_map -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_legend -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_legend -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
