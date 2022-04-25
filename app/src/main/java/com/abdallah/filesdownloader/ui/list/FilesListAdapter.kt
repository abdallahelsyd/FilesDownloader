package com.abdallah.filesdownloader.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdallah.filesdownloader.data.remote.models.FileItem

import com.abdallah.filesdownloader.databinding.ListItemBinding

class FilesListAdapter(
    private val onItemDownloadClicked: (item: FileItem) -> Unit,
) : ListAdapter<FileItem, FilesListAdapter.ViewHolder>(ItemDiffUtil) {

    private object ItemDiffUtil : DiffUtil.ItemCallback<FileItem>() {
        override fun areItemsTheSame(oldItem: FileItem, newItem: FileItem) = oldItem == newItem
        override fun areContentsTheSame(oldItem: FileItem, newItem: FileItem) = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binder: ListItemBinding) :
        RecyclerView.ViewHolder(binder.root) {

        fun bind(item: FileItem) = with(binder) {
            btnDownload.setOnClickListener {
                onItemDownloadClicked(item)
            }
            tvName.text=item.name
            tvType.text=item.type
        }
    }

}