package com.abdallah.filesdownloader.ui.list

import com.abdallah.filesdownloader.common.base.ViewState

sealed class ListViewState:ViewState(){
    data class Success(val data:List<FileListItem>):Loaded<List<FileListItem>>(data)
}
