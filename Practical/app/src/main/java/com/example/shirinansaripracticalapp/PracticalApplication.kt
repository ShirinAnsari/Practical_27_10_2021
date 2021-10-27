package com.example.shirinansaripracticalapp

import android.app.Application

class PracticalApplication : Application() {

    init {
        appContext = this
    }

    companion object {
        private var appContext: PracticalApplication? = null

        fun getAppContext(): PracticalApplication? {
            if (appContext == null) {
                synchronized(PracticalApplication::class.java) {
                    appContext = PracticalApplication()
                }
            }
            return appContext
        }

    }

    override fun onCreate() {
        super.onCreate()
    }
}