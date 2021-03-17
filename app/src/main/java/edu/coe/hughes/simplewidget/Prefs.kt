package edu.coe.hughes.simplewidget

import android.content.Context
import android.util.Log

class Prefs {
    companion object {
        val PREFS_NAME = "edu.coe.hughes.simpleWidget.RandomWidget"
        val PREF_PREFIX_KEY = "appwidget_"
        val LOGKEY = "Prefs"

        @JvmStatic
        fun savePref(context: Context, appWidgetId: Int, text: String) {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
            prefs.putString(PREF_PREFIX_KEY + appWidgetId, text)
            Log.i(LOGKEY, text)
            prefs.apply()
        }

        @JvmStatic
        fun loadPref(context: Context, appWidgetId: Int): String? {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0)
            val titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, "EXAMPLE")
            if (titleValue != null) {
                Log.i(LOGKEY, titleValue)
            }
            return titleValue
        }
        @JvmStatic
        fun deleteTitlePref(context: Context, appWidgetId: Int) {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
            prefs.remove(PREF_PREFIX_KEY + appWidgetId)
            prefs.apply()
        }
    }
}