package com.fee.aqarat.ui.auth.newPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fee.aqarat.base.BaseFragment
import com.fee.aqarat.databinding.FragmentNewPasswordBinding


class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNewPasswordBinding
        get() = FragmentNewPasswordBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

        initUi()
    }

    private fun initUi() {
        binding.confirmButtonLayout.setOnClickListener {
            val action = NewPasswordFragmentDirections.actionNewPasswordFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }


}