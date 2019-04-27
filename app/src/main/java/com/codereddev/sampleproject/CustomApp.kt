package com.codereddev.sampleproject

import android.app.Application
import com.codereddev.somelib.SomeManager

class CustomApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SomeManager()
    }
}