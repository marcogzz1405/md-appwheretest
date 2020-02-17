package com.example.appwhere.api

import com.example.appwhere.bases.BaseListener
import com.example.appwhere.models.Login

interface ListenerLogin : BaseListener {

    fun showErrorMessage(message: String)

    fun successEntry(login: Login)

}