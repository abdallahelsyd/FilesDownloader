package com.abdallah.filesdownloader.domain.repositories

import com.abdallah.filesdownloader.common.base.BaseRepository
import com.abdallah.filesdownloader.data.local.FileManager
import com.abdallah.filesdownloader.data.remote.ApiService
import com.abdallah.filesdownloader.data.remote.models.FileItem
import com.abdallah.filesdownloader.di.ContextProviders

class FilesRepository(private val apiService: ApiService, private val fileManager: FileManager, contextProviders: ContextProviders):BaseRepository(contextProviders),
    IRepository {
    override fun getListOfFiles() = networkHandler {
            apiService.requestFileList()
    }

    override fun requestDownloadFile(item: FileItem)=networkHandler {   fileManager.downloadFile(item)}

}