package com.example.appwhere.bases

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    fun hideKeyboard() {
        // Check if no view has focus:
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(fragment::class.java.simpleName).replace(R.id.contentFrame, fragment, fragment::class.java.simpleName).commit()
    }

    fun addFragment(fragment: Fragment, callback: Boolean) {
        if (callback) {
            supportFragmentManager.beginTransaction().addToBackStack(fragment::class.java.simpleName).replace(R.id.contentFrame, fragment, fragment::class.java.simpleName).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.contentFrame, fragment, fragment::class.java.simpleName).commit()
        }
    }


    fun launchMainActivity() {
        startActivity(MainActivity.getInstance(this@BaseActivity))
    }

    fun launchLoginActivity() {
        startActivity(LoginActivity.getInstance(this@BaseActivity))
    }

}