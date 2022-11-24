package com.hammad.jivaassisment.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hammad.jivaassisment.databinding.FragmentRegistrationBinding
import com.hammad.jivaassisment.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val viewModels by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindView()
        bindListener()
        bindObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun bindView() {

    }

    private fun bindListener() {
        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        viewModels.register(binding.container.etBox.text.toString())

        lifecycleScope.launchWhenCreated {
            delay(200)
            findNavController().navigateUp()
        }
    }


    private fun bindObserver() {

    }

}