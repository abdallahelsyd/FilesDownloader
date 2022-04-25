package com.abdallah.filesdownloader.domain.usecases


import com.abdallah.filesdownloader.data.remote.models.FileItem
import com.abdallah.filesdownloader.domain.usecases.UseCase
import com.abdallah.filesdownloader.data.remote.models.FileListResponse
import com.abdallah.filesdownloader.domain.models.DownloadData
import com.abdallah.filesdownloader.domain.repositories.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DownloadFileUseCase @Inject constructor(private val listRepository: IRepository):
    UseCase.WithParam<Flow<Flow<DownloadData>>,FileItem> {
    override fun invoke(param:FileItem):Flow<Flow<DownloadData>> = listRepository.requestDownloadFile(param)
}