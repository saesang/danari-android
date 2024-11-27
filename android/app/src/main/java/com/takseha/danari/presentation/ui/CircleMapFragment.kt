package com.takseha.danari.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.takseha.danari.R
import com.takseha.danari.data.dto.circle.CircleInfo
import com.takseha.danari.databinding.FragmentCircleMapBinding
import com.takseha.danari.presentation.adapter.CategoryRVAdapter
import com.takseha.danari.presentation.adapter.CircleRVAdapter
import com.takseha.danari.presentation.adapter.FloorRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CircleMapFragment : Fragment() {
    private var _binding: FragmentCircleMapBinding? = null
    private val binding get() = _binding!!
    private val floorList = listOf("3층", "4층", "5층", "6층")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCircleMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val floorRVAdapter =
                FloorRVAdapter(requireContext(), floorList)

            floorCategories.adapter = floorRVAdapter
            floorCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            clickFloorItem(floorRVAdapter, floorList)

            listBtn.setOnClickListener {
                it.findNavController().navigate(R.id.action_circleMapFragment_to_circleListFragment)
            }
        }
    }

    private fun clickFloorItem(
        floorRVAdapter: FloorRVAdapter,
        floorList: List<String>
    ) {
        with(binding) {
            floorRVAdapter.onClickListener = object : FloorRVAdapter.OnClickListener {
                override fun onClick(view: View, position: Int) {
                    when (position) {
                        0 -> mapImg.setImageResource(R.drawable.map_3)
                        1 -> mapImg.setImageResource(R.drawable.map_4)
                        2 -> mapImg.setImageResource(R.drawable.map_5)
                        else -> mapImg.setImageResource(R.drawable.map_6)
                    }
                }
            }
        }
    }
}