package com.abdallah.filesdownloader

import android.app.Application
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AppInstance : Application(){

    override fun onCreate() {
        super.onCreate()
        val config = PRDownloaderConfig.newBuilder()
            .setDatabaseEnabled(true)
            .setReadTimeout(30_000)
            .setConnectTimeout(30_000)
            .build()
        PRDownloader.initialize(this,config)
    }
}