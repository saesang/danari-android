package com.takseha.danari.presentation.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.takseha.danari.R
import com.takseha.danari.databinding.ItemBranchBinding

class CategoryRVAdapter(
    private val context: Context,
    private val categoryList: List<String>
) : RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onClick(view: View, position: Int)
    }
    var onClickListener: OnClickListener? = null

    var selectedPosition = 0

    class ViewHolder(val binding: ItemBranchBinding) : RecyclerView.ViewHolder(binding.root) {
        val categoryName = binding.branchName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBranchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = categoryList[position]

        if (position == selectedPosition) {
            holder.categoryName.background = ContextCompat.getDrawable(context, R.drawable.box_stroke_r30_700)
            holder.categoryName.setTextColor(ContextCompat.getColor(context, R.color.BLACK))
        } else {
            holder.categoryName.background = ContextCompat.getDrawable(context, R.drawable.box_stroke_r30_200)
            holder.categoryName.setTextColor(ContextCompat.getColor(context, R.color.GS_500))
        }

        // 클릭 이벤트 처리
        holder.itemView.setOnClickListener { v ->
            selectedPosition = position
            notifyDataSetChanged()
            onClickListener?.onClick(v, position)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}
