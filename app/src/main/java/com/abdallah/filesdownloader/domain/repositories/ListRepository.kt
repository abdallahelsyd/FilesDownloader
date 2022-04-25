package com.abdallah.filesdownloader.domain.repositories

import com.abdallah.filesdownloader.common.Constants
import com.abdallah.filesdownloader.common.base.BaseRepository
import com.abdallah.filesdownloader.data.remote.models.FileListResponse
import com.abdallah.filesdownloader.di.ContextProviders
import com.google.gson.Gson

class ListRepository(contextProviders: ContextProviders):BaseRepository(contextProviders),
    IRepository {
    override fun getListOfFiles() = networkHandler {
            Gson().fromJson(Constants.fakeResponse, FileListResponse::class.java)

    }
}