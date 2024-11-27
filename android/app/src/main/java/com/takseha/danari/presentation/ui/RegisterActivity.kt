package com.takseha.danari.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.takseha.danari.R
import com.takseha.danari.databinding.ActivityRegisterBinding
import com.takseha.danari.presentation.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewmodel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setBinding()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            backBtn.setOnClickListener {
                this@RegisterActivity.finish()
            }

            registerBtn.setOnClickListener {
                lifecycleScope.launch {
                    viewmodel.onRegisterClick()
                    viewmodel.registerState.collectLatest {
                        if (it != null) {
                            if (it) {
                                this@RegisterActivity.finish()
                            } else {
                                this@RegisterActivity.finish()
                            }
                        }
                    }
                }
            }
        }

    }

    private fun setBinding() {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewmodel
        setContentView(binding.root)
    }
}