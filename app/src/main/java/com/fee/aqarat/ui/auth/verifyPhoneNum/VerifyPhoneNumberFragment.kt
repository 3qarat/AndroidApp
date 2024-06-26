package com.fee.aqarat.ui.auth.verifyPhoneNum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fee.aqarat.base.BaseFragment
import com.fee.aqarat.databinding.FragmentVerifyPhoneNumberBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyPhoneNumberFragment : BaseFragment<FragmentVerifyPhoneNumberBinding>() {


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentVerifyPhoneNumberBinding
        get() = FragmentVerifyPhoneNumberBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

        initUi()

    }

    private fun initUi() {
        binding.confirmButtonLayout.setOnClickListener {
            val action =
                VerifyPhoneNumberFragmentDirections.actionVerifyPhoneNumberFragmentToNewPasswordFragment()
            findNavController().navigate(action)
        }
    }

}