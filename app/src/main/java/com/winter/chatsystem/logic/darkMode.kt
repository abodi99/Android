package com.winter.chatsystem.logic

import android.content.Context

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

var darkMode by mutableStateOf(false)


fun getTheme(context: Context): Boolean {
    val sharedPrefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    return sharedPrefs.getBoolean("myBoolean", false)
}

fun setTheme(context: Context, dark: Boolean) {
    val sharedPrefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    val editor = sharedPrefs.edit()
    editor.putBoolean("myBoolean", dark)
    editor.apply()
}