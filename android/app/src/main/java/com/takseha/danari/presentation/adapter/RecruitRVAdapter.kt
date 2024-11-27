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

class RecruitRVAdapter(
    private val context: Context,
    private val recruitList: List<Post>
) : RecyclerView.Adapter<RecruitRVAdapter.ViewHolder>() {

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
        holder.title.text = recruitList[position].postTitle
        holder.date.text = recruitList[position].createdAt

        // 클릭 이벤트 처리
        holder.itemView.setOnClickListener { v ->
            onClickListener?.onClick(v, position)
        }
    }

    override fun getItemCount(): Int {
        return recruitList.size
    }
}
