package com.cseltz.android.grocerylist.di

import android.app.Application

class MainApplication: Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }

}