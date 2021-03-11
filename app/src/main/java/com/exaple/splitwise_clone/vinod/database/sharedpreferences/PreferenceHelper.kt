package com.exaple.splitwise_clone.vinod.database.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(val context: Context) {

    companion object {
        var sharedPreferences: SharedPreferences? = null
    }

    fun sharedPreference(): SharedPreferences? {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("login_pref", Context.MODE_PRIVATE)
        }
        return sharedPreferences
    }

    fun writeIntToPreference(key: String?, value: Int) {
        sharedPreference()
        val editor = sharedPreferences!!.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun writeBooleanToPreference(key: String?, value: Boolean) {
        sharedPreference()
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun readBooleanFromPreference(key: String?): Boolean {
        sharedPreference()
        return sharedPreferences!!.getBoolean(key, true)
    }

    fun readIntFromPreference(key: String?): Int {
        sharedPreference()
        return sharedPreferences!!.getInt(key, 0)
    }
}