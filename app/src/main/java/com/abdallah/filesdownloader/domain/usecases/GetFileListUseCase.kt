package com.abdallah.filesdownloader.domain.usecases


import com.abdallah.filelistdownloader.domain.useCases.UseCase
import com.abdallah.filesdownloader.data.remote.models.FileListResponse
import com.abdallah.filesdownloader.domain.repositories.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetFileListUseCase @Inject constructor(private val listRepository: IRepository):
    UseCase<Flow<FileListResponse>> {
    override fun invoke(): Flow<FileListResponse> = listRepository.getListOfFiles()
}