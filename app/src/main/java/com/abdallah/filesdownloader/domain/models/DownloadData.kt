package com.abdallah.filesdownloader.domain.models


data class DownloadData(
    var downloadState: DownloadState,
    var message: String,
    var totla: Long = 0,
    var current: Long = 0,
    var progress: Int = 0
)

enum class DownloadState {
    ERROR,
    SUCCESS,
    LOADING,
    IDLE
}


