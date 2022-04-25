package com.abdallah.filesdownloader.data.remote

import com.abdallah.filesdownloader.common.Constants
import com.abdallah.filesdownloader.data.remote.models.FileListResponse
import com.google.gson.Gson

class RemoteDataSource:ApiService {
    override suspend fun requestFileList(): FileListResponse {
        return Gson().fromJson(Constants.fakeResponse, FileListResponse::class.java)
    }
}