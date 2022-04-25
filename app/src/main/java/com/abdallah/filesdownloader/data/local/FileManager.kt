package com.abdallah.filesdownloader.data.local

import android.os.Build
import android.os.Environment
import android.widget.Toast
import com.abdallah.filesdownloader.data.remote.models.FileItem
import com.abdallah.filesdownloader.domain.models.DownloadData
import com.abdallah.filesdownloader.domain.models.DownloadState
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*


class FileManager {

    private val _downloadStateFlow = MutableStateFlow(
        DownloadData(DownloadState.IDLE, "", 0, 0)
    )


    private var file = File(Environment.getExternalStorageDirectory(), "nagwa" )


    private fun createDirectory() {
        if (!file.exists()) {
            file.mkdir()
        }
    }



    fun downloadFile(item: FileItem): Flow<DownloadData> {
        createDirectory()
        PRDownloader.download(item.url, file.path, item.name)
            .build()
            .setOnStartOrResumeListener {
                _downloadStateFlow.value = getDownloadData(DownloadState.LOADING, 0, 0)
            }
            .setOnPauseListener { }
            .setOnCancelListener { }
            .setOnProgressListener {
                _downloadStateFlow.value =
                    getDownloadData(DownloadState.LOADING, it.totalBytes, it.currentBytes)

            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    _downloadStateFlow.value = getDownloadData(DownloadState.SUCCESS, 0, 0)
                }

                override fun onError(error: com.downloader.Error?) {
                    _downloadStateFlow.value = getDownloadData(DownloadState.ERROR, 0, 0)
                }
            })
        return _downloadStateFlow
    }


    private fun getDownloadData(sate: DownloadState, total: Long, current: Long): DownloadData {
        return when (sate) {
            DownloadState.ERROR -> DownloadData(
                downloadState = sate,
                message = "Downloading Field"
            )
            DownloadState.SUCCESS -> DownloadData(
                downloadState = sate,
                message = "Downloading Complete"
            )
            DownloadState.LOADING -> DownloadData(
                downloadState = sate,
                message = "Downloading ${getProgressDisplayLine(current, total)}",
                progress = getProgress(current, total)
            )

            DownloadState.IDLE -> DownloadData(
                downloadState = sate,
                message = "No Downloads"
            )
        }
    }

    private fun getProgress(currentBytes: Long, totalBytes: Long): Int =
        ((getBytesToMBInt(currentBytes) / getBytesToMBInt(totalBytes)) * 100).toInt()


    private fun getProgressDisplayLine(currentBytes: Long, totalBytes: Long): String =
        getBytesToMBString(currentBytes) + "/" + getBytesToMBString(totalBytes)


    private fun getBytesToMBString(bytes: Long): String? =
        if (getBytesToMBInt(bytes) <= 0) "UNKNOWN" else formatStringData(bytes)


    private fun formatStringData(bytes: Long) = java.lang.String.format(
        Locale.ENGLISH,
        "%.2fMb",
        getBytesToMBInt(bytes)
    )

    private fun getBytesToMBInt(bytes: Long): Double {
        return bytes / (1024.00 * 1024.00)
    }
}