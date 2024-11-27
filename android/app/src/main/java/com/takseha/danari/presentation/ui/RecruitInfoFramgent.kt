package com.takseha.danari.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.takseha.danari.R
import com.takseha.danari.data.dto.circle.Post
import com.takseha.danari.databinding.FragmentEventListBinding
import com.takseha.danari.databinding.FragmentRecruitInfoFramgentBinding

class RecruitInfoFramgent : Fragment() {
    private var _binding: FragmentRecruitInfoFramgentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recruitInfo : Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recruitInfo = it.getSerializable("recruit") as Post
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecruitInfoFramgentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            backBtn.setOnClickListener {
                it.findNavController().popBackStack()
            }
            title.text = recruitInfo.postTitle
            postDate.text = recruitInfo.createdAt
            contents.text = recruitInfo.postContent
        }
    }
}