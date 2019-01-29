package com

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.util.LocaleManager // May change according to LocaleManager class localization in your project

// Original work : https://gist.github.com/Jasonlhy/f6d1211c4f848a3f685bd2d28c6e2eb5

class App : Application(){

    override fun onCreate() {
        super.onCreate()
    }

    // Necessary method for LocaleManager 
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.setLocale(base))
    }

    // Necessary method for LocaleManager 
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }

}
