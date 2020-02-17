package com.example.appwhere.bases

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.appwhere.R
import com.example.appwhere.activities.MainActivity
import com.example.appwhere.activities.LoginActivity
import com.example.appwhere.utilities.Settings

abstract class BaseActivity : AppCompatActivity() {

    fun showMessage(type: Int, title: String, message: String){
        if(type == Settings.TYPE_MESSAGE_INFO || type == Settings.TYPE_MESSAGE_ERROR) {
            AlertDialog.Builder(this@BaseActivity).setTitle(title).setMessage(message).setPositiveButton(getString(R.string.accept)) { dialogInterface, _ -> dialogInterface.dismiss() }
                .create().show()
        }
    }

    fun launchMainActivity() {
        startActivity(MainActivity.getInstance(this@BaseActivity))
    }

    fun launchLoginActivity() {
        startActivity(LoginActivity.getInstance(this@BaseActivity))
    }

}