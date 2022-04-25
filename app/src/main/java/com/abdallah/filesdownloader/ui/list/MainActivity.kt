package com.abdallah.filesdownloader.ui.list


import android.Manifest
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdallah.filesdownloader.R
import com.abdallah.filesdownloader.common.base.BaseActivity
import com.abdallah.filesdownloader.common.base.ViewState
import com.abdallah.filesdownloader.data.remote.models.FileItem
import com.abdallah.filesdownloader.databinding.ActivityMainBinding
import com.abdallah.filesdownloader.domain.models.DownloadData
import com.abdallah.filesdownloader.domain.models.DownloadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,FilesViewModel>(R.layout.activity_main) {
    override val viewModel: FilesViewModel by viewModels()

    lateinit var fileAdapter: FilesListAdapter
    private var currentFileItem:FileItem?=null
    private var isFileDownloading: Boolean = false

    override fun setup() {

        fileAdapter= FilesListAdapter(onItemDownloadClicked = { item ->
            downloadFile(item)
        })
        setupRecycleView()
        viewModel.getFilesList()
    }

    private fun setupRecycleView(){
        binder.recycleView.adapter=fileAdapter
        binder.recycleView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }

    private fun downloadFile(item: FileItem) {
        if (!isFileDownloading) {
            isFileDownloading=true
            currentFileItem = item
            askWriteStoragePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    private val askWriteStoragePermission =registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        if (result != null && currentFileItem!=null && !isFileDownloading)
            viewModel.downloadFile(currentFileItem!!)
    }

    override fun render(state: ViewState) {
        when(state){
            is FilesViewState.Success->{
                fileAdapter.submitList(state.data)
            }
            is FilesViewState.DownloadingResult->{

                onDownloadDataReceived(state.data)

            }
        }
    }
    private fun onDownloadDataReceived(data: DownloadData) {

        binder.tvProgressMsg.text = data.message
        binder.downloadProgressBar.progress = data.progress
        binder.downloadProgressBar.visibility =
            if (data.downloadState == DownloadState.LOADING) View.VISIBLE else View.GONE

        isFileDownloading = data.downloadState == DownloadState.LOADING
    }

}