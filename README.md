# Kotlin-LocaleManager
LocaleManager converted from Java to Kotlin

In order to user LocaleManager, don't forget to declare the 2 necessary overrided methods in App.kt file (that is also in this repository) or any other class that extends Application().

Original project :
https://gist.github.com/Jasonlhy/f6d1211c4f848a3f685bd2d28c6e2eb5

This class will help you to arbitrary define the locale used by an Activity in your application.

For example, you have to call **LocaleManager.setNewLocale(this@YourActivityName, "en")** in the first activity of your application and then every activity will be set to the Locale "en"...

To get the locale, use **val currentLocale = ConfigurationCompat.getLocales(resources.configuration)[0]**

https://ntic974.blogspot.com
