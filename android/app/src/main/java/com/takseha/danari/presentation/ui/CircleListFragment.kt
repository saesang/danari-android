package com.takseha.danari.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.takseha.danari.R
import com.takseha.danari.data.dto.circle.CircleInfo
import com.takseha.danari.databinding.FragmentCircleListBinding
import com.takseha.danari.databinding.FragmentCircleMapBinding
import com.takseha.danari.presentation.adapter.CategoryRVAdapter
import com.takseha.danari.presentation.adapter.CircleRVAdapter
import com.takseha.danari.presentation.viewmodel.CircleListViewModel
import com.takseha.danari.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CircleListFragment : Fragment() {
    private var _binding: FragmentCircleListBinding? = null
    private val binding get() = _binding!!
    private val viewmodel : CircleListViewModel by viewModels()

    private val branchList = listOf("전체", "교양분과", "봉사분과", "학술분과", "공연예술분과", "문예창작분과", "종교분과", "체육분과")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCircleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel.getAllCircles()
            viewLifecycleOwner.lifecycleScope.launch {
                val categoryRVAdapter =
                    CategoryRVAdapter(requireContext(), branchList)

                branchCategories.adapter = categoryRVAdapter
                branchCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                clickBranchItem(categoryRVAdapter, branchList)

                viewmodel.selectedBranchPosition.collectLatest { position ->
                    categoryRVAdapter.selectedPosition = position
                    categoryRVAdapter.notifyDataSetChanged()
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewmodel.uiState.collectLatest {
                    val clubList = it
                    val circleRVAdapter =
                        CircleRVAdapter(requireContext(), clubList)

                    circleList.adapter = circleRVAdapter
                    circleList.layoutManager = LinearLayoutManager(requireContext())
                    clickCircleItem(circleRVAdapter, clubList)
                }
            }
            mapBtn.setOnClickListener {
                it.findNavController().navigate(R.id.action_circleListFragment_to_circleMapFragment)
            }
        }
    }

    private fun clickBranchItem(
        categoryRVAdapter: CategoryRVAdapter,
        branchList: List<String>
    ) {
        categoryRVAdapter.onClickListener = object : CategoryRVAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                viewmodel.selectBranch(position)
                if (position == 0) {
                    viewmodel.getAllCircles()
                } else {
                    viewmodel.getDepartmentCircles(branchList[position])
                }
            }
        }
    }

    private fun clickCircleItem(
        circleRVAdapter: CircleRVAdapter,
        clubList: List<CircleInfo>
    ) {
        circleRVAdapter.onClickListener = object : CircleRVAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(requireContext(), CircleHomeActivity::class.java)
                intent.putExtra("clubName", clubList[position].clubName)
                intent.putExtra("description", clubList[position].description)
                intent.putExtra("roomNumber", clubList[position].roomNumber)
                startActivity(intent)
            }
        }
    }
}

