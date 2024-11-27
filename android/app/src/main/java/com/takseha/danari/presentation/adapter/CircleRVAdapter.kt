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
import com.google.gson.annotations.SerializedName
import com.takseha.danari.R
import com.takseha.danari.data.dto.circle.CircleInfo
import com.takseha.danari.data.dto.circle.CircleListResponse
import com.takseha.danari.databinding.ItemBranchBinding
import com.takseha.danari.databinding.ItemCircleBinding

class CircleRVAdapter(
    private val context: Context,
    private val circleList: List<CircleInfo>
) : RecyclerView.Adapter<CircleRVAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onClick(view: View, position: Int)
    }
    var onClickListener: OnClickListener? = null

    class ViewHolder(val binding: ItemCircleBinding) : RecyclerView.ViewHolder(binding.root) {
        val description = binding.circleDesc
        val circleLocation = binding.circleLocation
        val clubName = binding.circleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCircleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.description.text = circleList[position].description
        holder.circleLocation.text = circleList[position].roomNumber
        holder.clubName.text = circleList[position].clubName


        // 클릭 이벤트 처리
        holder.itemView.setOnClickListener { v ->
            onClickListener?.onClick(v, position)
        }
    }

    override fun getItemCount(): Int {
        return circleList.size
    }
}
