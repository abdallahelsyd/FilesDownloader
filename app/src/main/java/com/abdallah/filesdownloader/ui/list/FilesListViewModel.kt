package com.abdallah.filesdownloader.ui.list


import com.abdallah.filesdownloader.common.base.BaseViewModel
import com.abdallah.filesdownloader.di.ContextProviders
import com.abdallah.filesdownloader.domain.usecases.GetFileListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilesListViewModel @Inject constructor(
    private val fileListUseCase: GetFileListUseCase,
    contextProviders: ContextProviders
) : BaseViewModel(contextProviders) {


    private var filesList = emptyList<FileListItem>()

    fun getFilesList() = launchBlock {
        fileListUseCase.invoke().collect { list ->
            filesList = list.map { FileListItem(it.id, it.name, it.type, it.url, 0) }
            setState(ListViewState.Success(filesList))
        }

    }


}