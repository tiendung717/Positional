package app.simple.positional.preference

import android.content.Context
import app.simple.positional.constants.GPSLabelMode
import app.simple.positional.constants.preferences

class GPSPreferences {
    fun isLabelOn(context: Context): Boolean {
        return context.getSharedPreferences(preferences, Context.MODE_PRIVATE).getBoolean(GPSLabelMode, false)
    }

    fun setLabelMode(context: Context, value: Boolean) {
        context.getSharedPreferences(preferences, Context.MODE_PRIVATE).edit().putBoolean(GPSLabelMode, value).apply()
    }
}