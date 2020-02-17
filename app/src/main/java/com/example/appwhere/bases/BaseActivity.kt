package com.example.appwhere.bases

import androidx.appcompat.app.AppCompatActivity
import com.example.appwhere.MainActivity
import com.example.appwhere.activities.LoginActivity

abstract class BaseActivity : AppCompatActivity() {

    fun launchMainActivity() {
        startActivity(MainActivity.getInstance(this@BaseActivity))
    }

    fun launchLoginActivity() {
        startActivity(LoginActivity.getInstance(this@BaseActivity))
    }

}