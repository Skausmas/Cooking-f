package com.example.cooking.data.local

import android.content.SharedPreferences
import javax.inject.Inject


class PreferenceManager @Inject constructor(private val preferences: SharedPreferences) {

    companion object {
        private const val LOGIN = "LOGIN"
        private const val USER_ID = "USER_ID"
    }

    var savedLogin: String
        get() = preferences.getString(LOGIN, "") ?: ""
        set(value) {
            preferences.edit().putString(LOGIN, value).apply()
        }

    var userId: Int
        get() = preferences.getInt(USER_ID, -1) ?: -1
        set(value) {
            preferences.edit().putInt(USER_ID, value).apply()
        }
}