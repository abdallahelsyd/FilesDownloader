package com.abdallah.filesdownloader.domain.repositories

import com.abdallah.filesdownloader.data.remote.models.FileListResponse
import kotlinx.coroutines.flow.Flow


interface IRepository {
    fun getListOfFiles(): Flow<FileListResponse>
}