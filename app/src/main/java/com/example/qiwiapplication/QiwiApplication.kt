package com.example.qiwiapplication

import android.app.Application
import android.content.Context

class QiwiApplication: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}