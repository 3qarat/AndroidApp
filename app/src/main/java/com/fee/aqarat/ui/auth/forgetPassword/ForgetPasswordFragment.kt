package com.fee.aqarat.ui.auth.forgetPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fee.aqarat.base.BaseFragment
import com.fee.aqarat.databinding.FragmentForgetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForgetPasswordBinding
        get() = FragmentForgetPasswordBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        initUi()
    }

    private fun initUi() {
        binding.confirmButtonLayout.setOnClickListener {
            val action =
                ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToVerifyPhoneNumberFragment()
            findNavController().navigate(action)
        }
    }

}