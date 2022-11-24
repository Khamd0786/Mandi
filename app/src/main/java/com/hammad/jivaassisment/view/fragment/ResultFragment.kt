package com.hammad.jivaassisment.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hammad.jivaassisment.R
import com.hammad.jivaassisment.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var mUserName: String? = null
    private lateinit var binding: FragmentResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mUserName = arguments?.getString(KEY_USER_NAME)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
        bindListener()
    }

    private fun bindView() {
        binding.tvSeller.text = buildString {
            append(getString(R.string.thank_you2))
            append(mUserName)
            append(getString(R.string.selling_message))
        }
    }

    private fun bindListener() {
        binding.btnReturn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {
        private const val KEY_USER_NAME = "key_user_name"

        fun getArgs(userName: String) = Bundle().also {
            it.putString(KEY_USER_NAME, userName)
        }
    }
}