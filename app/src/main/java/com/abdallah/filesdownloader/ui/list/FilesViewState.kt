package com.abdallah.filesdownloader.ui.list

import com.abdallah.filesdownloader.common.base.ViewState
import com.abdallah.filesdownloader.data.remote.models.FileItem
import com.abdallah.filesdownloader.domain.models.DownloadData
import kotlinx.coroutines.flow.Flow

sealed class FilesViewState:ViewState(){
    data class Success(val data:List<FileItem>):Loaded<List<FileItem>>(data)
    data class DownloadingResult(val data: DownloadData) : Loaded<DownloadData>(data)
}
