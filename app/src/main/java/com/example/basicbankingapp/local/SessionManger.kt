package com.example.basicbankingapp.local

import android.content.Context
import com.example.basicbankingapp.R

object SessionManger {

    fun saveBoolean(context: Context, key: String, value: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(context: Context, key: String): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, false)
    }

}