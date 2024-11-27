package com.takseha.danari.presentation.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.takseha.danari.data.dto.circle.Post
import com.takseha.danari.databinding.ItemPostingBinding

class EventRVAdapter(
    private val context: Context,
    private val postList: List<Post>
) : RecyclerView.Adapter<EventRVAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onClick(view: View, position: Int)
    }
    var onClickListener: OnClickListener? = null

    class ViewHolder(val binding: ItemPostingBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val date = binding.postDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = postList[position].postTitle
        holder.date.text = postList[position].createdAt

        // 클릭 이벤트 처리
        holder.itemView.setOnClickListener { v ->
            onClickListener?.onClick(v, position)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}
