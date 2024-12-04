package com.takseha.danari.presentation.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.takseha.danari.data.dto.circle.Review
import com.takseha.danari.databinding.ItemReviewBinding

class ReviewRVAdapter(
    private val context: Context,
    private val reviewList: List<Review>
) : RecyclerView.Adapter<ReviewRVAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onClick(view: View, position: Int)
    }
    var onClickListener: OnClickListener? = null

    class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val content = binding.contents
        val date = binding.reviewDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.text = reviewList[position].reviewContent
        holder.date.text = reviewList[position].createdAt

        // 클릭 이벤트 처리
        holder.itemView.setOnClickListener { v ->
            onClickListener?.onClick(v, position)
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}
