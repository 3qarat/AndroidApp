package com.fee.aqarat.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fee.aqarat.base.BaseFragment
import com.fee.aqarat.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

    }


}