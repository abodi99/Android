package com.winter.chatsystem.classes

import android.app.Application
import com.yariksoffice.lingver.Lingver

class Language : Application() {
    override fun onCreate() {
        super.onCreate()
        Lingver.init(this)
    }
}