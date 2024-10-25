package com.example.cooking.data.local

import android.content.SharedPreferences
import javax.inject.Inject


class PreferenceManager @Inject constructor(private val preferences: SharedPreferences) {

    companion object {
        private const val LOGIN = "LOGIN"
    }

    var savedLogin: String
        get() = preferences.getString(LOGIN, "") ?: ""
        set(value) {
            preferences.edit().putString(LOGIN, value).apply()
        }
}