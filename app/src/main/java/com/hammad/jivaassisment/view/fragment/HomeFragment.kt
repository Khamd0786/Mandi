package com.hammad.jivaassisment.view.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.core.text.isDigitsOnly
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hammad.jivaassisment.R
import com.hammad.jivaassisment.databinding.FragmentHomeBinding
import com.hammad.jivaassisment.extensions.executeDebounced
import com.hammad.jivaassisment.model.User
import com.hammad.jivaassisment.view.fragment.bottomFragment.VillageBottomSheet
import com.hammad.jivaassisment.viewmodel.HomeViewModel
import com.hammad.jivaassisment.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val DEFAULT_MULTIPLIER: Double = 0.98

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val userViewModel by viewModels<UserViewModel>()
    private val viewModel by viewModels<HomeViewModel>()

    private var mPrice = 0

    private var mRegisteredUser: User? = null
    private var mMultiplier: Double = DEFAULT_MULTIPLIER
    private var mItems: Int = 0

    private var mUserName : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
        bindListener()
        bindObserver()
    }

    private fun bindView() {

        binding.containerSeller.h1.text = getString(R.string.seller_h1)
        binding.containerSeller.etBox.hint = "Name"

        binding.containerLoyalty.h1.text = getString(R.string.loyalty_h1)
        binding.containerLoyalty.etBox.hint = "XXXX"

        binding.containerVillage.h1.text = getString(R.string.village)
        binding.containerVillage.etBox.hint = "Village1"
        binding.containerVillage.icDrop.visibility = View.VISIBLE
        binding.containerVillage.etBox.isFocusable = false
        binding.containerVillage.etBox.inputType = InputType.TYPE_NULL

        binding.containerGrossWeight.h1.text = getString(R.string.gross_weight)
        binding.containerGrossWeight.etBox.hint = "No. of items"
        binding.containerGrossWeight.tvUnit.visibility = View.VISIBLE
        binding.containerGrossWeight.etBox.inputType = InputType.TYPE_CLASS_NUMBER


    }

    private fun bindListener() {

        binding.icAdd.setOnClickListener {
            navigateRegistration()
        }

        binding.containerSeller.etBox.doOnTextChanged { text, start, before, count ->
            mUserName = text.toString()
            executeDebounced {
                userViewModel.getUserByUsername(text.toString())
            }
        }

        binding.containerLoyalty.etBox.doOnTextChanged { text, start, before, count ->
            executeDebounced {
                userViewModel.getUserByLoyaltyId(text.toString())
            }
        }

        binding.containerVillage.etBox.setOnClickListener {
            openSheet()
        }

        binding.containerGrossWeight.etBox.doOnTextChanged { text, start, before, count ->
            if (text?.isDigitsOnly()?.not() == true) {
                Toast.makeText(context, "Number Only", Toast.LENGTH_SHORT).show()
                return@doOnTextChanged
            }

            mItems = if (text.isNullOrEmpty().not()) text.toString().toInt() else 0
            calculatePrice()
        }

        binding.btnSell.setOnClickListener {
            navigateNext()
        }
    }

    private fun bindObserver() {
        lifecycleScope.launchWhenCreated {
            userViewModel.userShareFlow.collectLatest {
                updateUserInfo(it)
            }
        }
    }

    @UiThread
    private fun updateUserInfo(user: User?) {
        mRegisteredUser = user
        mMultiplier = user?.multiplier ?: DEFAULT_MULTIPLIER
        binding.tvLoyaltyIndex.text = "Applied Loyalty Index $mMultiplier"

        if (user == null)
            return

        val sellerBox = binding.containerSeller.etBox
        val loyalBox = binding.containerLoyalty.etBox

        sellerBox.setText(user.userName)
        sellerBox.setSelection(sellerBox.length())

        loyalBox.setText(user.loyaltyId)
        loyalBox.setSelection(loyalBox.length())

        calculatePrice()
    }

    private fun navigateRegistration() {
        findNavController().navigate(R.id.action_homeFragment_to_registrationFragment)
    }


    private fun navigateNext() {
        val args = ResultFragment.getArgs(mUserName ?: "")
        findNavController().navigate(R.id.action_homeFragment_to_resultFragment, args)
    }

    private fun openSheet() {
        VillageBottomSheet.open(childFragmentManager, viewModel.getVillages()) {
            viewModel.mSelectedVillage = it
            mPrice = viewModel.getPrice()
            binding.containerVillage.etBox.setText(it)

            calculatePrice()
        }
    }

    private fun calculatePrice() {
        val grossPrice = mItems * mPrice * mMultiplier
        binding.tvGrossPrice.text = grossPrice.toString()
    }

}