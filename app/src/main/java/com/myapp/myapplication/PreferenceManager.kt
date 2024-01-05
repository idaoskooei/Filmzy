package com.myapp.myapplication

import android.content.SharedPreferences

class PreferenceManager(
    private val sharedPreferences: SharedPreferences
) {

    private fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    private fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getProfilePic(): String {
        return getString("profile_pic_uri") ?: ""
    }

    fun setProfilePic(uri: String) {
        putString("profile_pic_uri", uri)
    }
}