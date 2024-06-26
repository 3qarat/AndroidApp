package com.fee.aqarat.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fee.aqarat.base.BaseFragment
import com.fee.aqarat.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

        initUi()

    }

    private fun initUi() {
        binding.forgetPasswordTextView.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment()
            findNavController().navigate(action)
        }

        binding.createAccountTextView.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }


}