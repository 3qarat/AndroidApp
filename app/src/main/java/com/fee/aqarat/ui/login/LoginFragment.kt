package com.fee.aqarat.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fee.aqarat.base.BaseFragment
import com.fee.aqarat.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

    }


}