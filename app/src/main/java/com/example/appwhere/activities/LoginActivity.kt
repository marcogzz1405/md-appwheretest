package com.example.appwhere.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.appwhere.R
import com.example.appwhere.bases.BaseActivity

class LoginActivity : BaseActivity() {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
