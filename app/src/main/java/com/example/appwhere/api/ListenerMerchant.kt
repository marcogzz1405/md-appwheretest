package com.example.appwhere.api

import com.example.appwhere.bases.BaseListener
import com.example.appwhere.models.Login
import com.example.appwhere.models.Merchant

interface ListenerMerchant : BaseListener {

    fun showErrorMessage(message: String)

    fun successEntry(merchant: Merchant)

}