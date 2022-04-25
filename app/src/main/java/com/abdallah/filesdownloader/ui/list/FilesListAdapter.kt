package com.abdallah.filesdownloader.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.abdallah.filesdownloader.databinding.ListItemBinding

class FilesListAdapter(
    private val onItemDownloadClicked: (item: FileListItem) -> Unit,
) : ListAdapter<FileListItem, FilesListAdapter.ViewHolder>(ItemDiffUtil) {

    private object ItemDiffUtil : DiffUtil.ItemCallback<FileListItem>() {
        override fun areItemsTheSame(oldItem: FileListItem, newItem: FileListItem) = oldItem == newItem
        override fun areContentsTheSame(oldItem: FileListItem, newItem: FileListItem) = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),position)
    }

    inner class ViewHolder(private val binder: ListItemBinding) :
        RecyclerView.ViewHolder(binder.root) {

        fun bind(item: FileListItem,position: Int) = with(binder) {
            btnDownload.setOnClickListener {
                onItemDownloadClicked(item)
            }
            tvName.text=item.name
            tvType.text=item.type
        }
    }

}