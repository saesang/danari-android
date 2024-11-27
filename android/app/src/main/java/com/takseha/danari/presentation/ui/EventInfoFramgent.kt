package com.takseha.danari.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.takseha.danari.data.dto.circle.Post
import com.takseha.danari.databinding.FragmentEventInfoFramgentBinding

class EventInfoFramgent : Fragment() {
    private var _binding: FragmentEventInfoFramgentBinding? = null
    private val binding get() = _binding!!
    private lateinit var eventInfo : Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            eventInfo = it.getSerializable("event") as Post
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventInfoFramgentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            backBtn.setOnClickListener {
                it.findNavController().popBackStack()
            }
            title.text = eventInfo.postTitle
            postDate.text = eventInfo.createdAt
            contents.text = eventInfo.postContent
        }
    }
}