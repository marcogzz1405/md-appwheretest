package com.example.appwhere.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.appwhere.R
import com.example.appwhere.api.ListenerLogin
import com.example.appwhere.api.PresenterLogin
import com.example.appwhere.bases.BaseActivity
import com.example.appwhere.models.Login
import com.example.appwhere.utilities.Settings
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : BaseActivity(), ListenerLogin {

    private var presenterLogin: PresenterLogin? = null

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenterLogin = PresenterLogin()
        presenterLogin?.listener = this@LoginActivity

        btnLogin.setOnClickListener {
            attempLogin()
        }

    }

    private fun attempLogin() {
        // Reset errors.
        email.error = null
        password.error = null
        // Store values at the time of the login attemp
        val emailStr = email.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid email address
        if(TextUtils.isEmpty(emailStr)) {
            email.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        }

        // Check for a valid password, if the user entered one
        if(TextUtils.isEmpty(passwordStr)){
            password.error = getString(R.string.error_field_required)
            focusView = password
            cancel = true
        }

        if(cancel){
            focusView?.requestFocus()
        } else {
            //hideKeyboard()
            val nameTable = mutableMapOf<String, String>()
            nameTable.put("email", emailStr)
            nameTable.put("password", passwordStr)
            presenterLogin?.attempLogin(nameTable)
        }
    }

    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        login_form.visibility = if (show) View.GONE else View.INVISIBLE
        login_form.animate()
            .setDuration(shortAnimTime)
            .alpha((if (show) 0 else 1).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    login_form.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

        login_progress.visibility = if (show) View.VISIBLE else View.GONE
        login_progress.animate()
            .setDuration(shortAnimTime)
            .alpha((if (show) 1 else 0).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    login_progress.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
    }

    override fun showErrorMessage(message: String) {
        if(message != null){
            showMessage(Settings.TYPE_MESSAGE_ERROR, getString(R.string.error_title), message)
        }
    }

    override fun successEntry(login: Login) {
        email.setText("")
        password.setText("")
        launchMainActivity()
        finish()
    }

    override fun showProgress() {
        showProgress(true)
    }

    override fun hideProgress() {
        showProgress(false)
    }

}
