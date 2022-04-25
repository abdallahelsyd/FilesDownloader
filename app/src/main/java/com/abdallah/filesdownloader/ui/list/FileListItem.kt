package com.abdallah.filesdownloader.ui.list

data class FileListItem(
    val id: Int,
    val name: String,
    val type: String,
    val url: String,
    var progress: Int=0
)