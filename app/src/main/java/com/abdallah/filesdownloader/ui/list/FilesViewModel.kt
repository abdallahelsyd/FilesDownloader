package com.abdallah.filesdownloader.ui.list


import com.abdallah.filesdownloader.common.base.BaseViewModel
import com.abdallah.filesdownloader.data.remote.models.FileItem
import com.abdallah.filesdownloader.di.ContextProviders
import com.abdallah.filesdownloader.domain.usecases.DownloadFileUseCase
import com.abdallah.filesdownloader.domain.usecases.GetFileListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class FilesViewModel @Inject constructor(
    private val fileListUseCase: GetFileListUseCase,
    private val downloadFileUseCase: DownloadFileUseCase,
    contextProviders: ContextProviders
) : BaseViewModel(contextProviders) {


    private var filesList = emptyList<FileItem>()

    /*init {
        setState(FilesViewState.Success(filesList))
    }*/
    fun getFilesList() = launchBlock {
        fileListUseCase.invoke().collect { list ->
            filesList = list
            setState(FilesViewState.Success(filesList))
        }
    }
    fun downloadFile(item: FileItem)=launchBlock(false) {
        downloadFileUseCase.invoke(item).collect { flow->
            flow.collect{

                setState(FilesViewState.DownloadingResult(it))
            }
        }
    }

}