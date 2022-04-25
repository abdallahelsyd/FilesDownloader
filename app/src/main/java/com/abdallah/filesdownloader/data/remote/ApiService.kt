package com.abdallah.filesdownloader.data.remote

import com.abdallah.filesdownloader.data.remote.models.FileListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/movies")
    suspend fun requestFileList(): FileListResponse

}