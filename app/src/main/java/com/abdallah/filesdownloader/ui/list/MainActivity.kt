package com.abdallah.filesdownloader.ui.list


import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdallah.filesdownloader.R
import com.abdallah.filesdownloader.common.base.BaseActivity
import com.abdallah.filesdownloader.common.base.ViewState
import com.abdallah.filesdownloader.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,FilesListViewModel>(R.layout.activity_main) {
    override val viewModel: FilesListViewModel by viewModels()

    lateinit var fileAdapter: FilesListAdapter

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

    private fun downloadFile(item: FileListItem) {

    }
    override fun render(state: ViewState) {
        when(state){
            is ListViewState.Success->{
                fileAdapter.submitList(state.data)
            }
        }
    }

}