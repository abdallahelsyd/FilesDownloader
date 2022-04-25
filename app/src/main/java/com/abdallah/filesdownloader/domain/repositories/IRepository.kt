package com.abdallah.filesdownloader.domain.repositories

import androidx.lifecycle.LiveData
import com.abdallah.filesdownloader.data.remote.models.FileItem
import com.abdallah.filesdownloader.data.remote.models.FileListResponse
import com.abdallah.filesdownloader.domain.models.DownloadData
import kotlinx.coroutines.flow.Flow


interface IRepository {
    fun getListOfFiles(): Flow<FileListResponse>
    fun requestDownloadFile(item: FileItem): Flow<Flow<DownloadData>>
}