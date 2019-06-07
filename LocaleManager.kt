package com.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*
import android.preference.PreferenceManager
import android.content.res.Resources
import android.annotation.SuppressLint

/*
Thanks to this Kotlin class you can force the Locale in any activity in your application
Example of call from an activity : LocaleManager.setNewLocale(this@YourActivityName, "en")
Don't forget to override attachBaseContext() and onConfigurationChanged() in App.kt class

Original work : https://gist.github.com/Jasonlhy/f6d1211c4f848a3f685bd2d28c6e2eb5
*/

class LocaleManager {

    companion object {
        private val LANGUAGE_KEY = "CHOOSE_LANGUAGE"

        fun setLocale(c: Context): Context {
            val savedLanguage = getLanguage(c)
            return savedLanguage?.let { updateResources(c, it) } ?: c
        }

        fun setNewLocale(c: Context, language: String): Context {
            persistLanguage(c, language)
            return updateResources(c, language)
        }

        fun setNewLocale(c: Context, newLocale: Locale): Context {
            persistLanguage(c, newLocale.toString())
            return updateResources(c, newLocale)
        }

        fun getLanguage(c: Context): String? {
            val prefs = PreferenceManager.getDefaultSharedPreferences(c)
            val currentLocale = getLocale(c.resources)
            return if (!prefs.contains(LANGUAGE_KEY)) null else prefs.getString(LANGUAGE_KEY, currentLocale.toString())
        }

        @SuppressLint("ApplySharedPref")
        private fun persistLanguage(c: Context, language: String) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(c)
            prefs.edit().putString(LANGUAGE_KEY, language).commit()
        }

        private fun updateResources(context: Context, language: String): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)
            return updateResources(context, locale)
        }

        @Suppress("DEPRECATION")
        private fun updateResources(context: Context, locale: Locale): Context {
            var context = context
            val res = context.resources
            val config = Configuration(res.configuration)
            if (Build.VERSION.SDK_INT >= 17) {
                config.setLocale(locale)
                context = context.createConfigurationContext(config)
                res.updateConfiguration(config, res.displayMetrics)
            } else {
                config.locale = locale
                res.updateConfiguration(config, res.displayMetrics)
            }
            return context
        }

        fun getLocale(res: Resources): Locale {
            val config = res.configuration
            return if (Build.VERSION.SDK_INT >= 24) config.locales.get(0) else config.locale
        }

        fun getSavedLocale(c: Context): Locale {
            val savedLanguage = getLanguage(c)
            return if (savedLanguage == null) getLocale(c.resources) else Locale(getLanguage(c))
        }
    }


}
