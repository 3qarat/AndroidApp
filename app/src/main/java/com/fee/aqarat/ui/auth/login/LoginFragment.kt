package com.fee.aqarat.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.fee.aqarat.R
import com.fee.aqarat.base.BaseFragment
import com.fee.aqarat.databinding.FragmentLoginBinding
import com.fee.aqarat.domain.models.auth.LoginBody
import com.fee.aqarat.domain.models.auth.LoginResponse
import com.fee.aqarat.ui.MainActivity
import com.fee.aqarat.ui.auth.AuthViewModel
import com.fee.aqarat.utils.Resource
import com.fee.aqarat.utils.SharedPrefUtils.getEmail
import com.fee.aqarat.utils.SharedPrefUtils.getPassword
import com.fee.aqarat.utils.SharedPrefUtils.isLogin
import com.fee.aqarat.utils.SharedPrefUtils.rememberMe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val authViewModel: AuthViewModel by viewModels()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

        initUi()

    }

    private fun initUi() {

        if (isLogin(context!!)) {
            navigateToHome()
        }

        binding.emailEditText.setText(getEmail(context!!))
        binding.passwordEditText.setText(getPassword(context!!))

        binding.loginLayout.setOnClickListener {
            if (isValidated()) {
                binding.loadingProgressBar.loadingProgressBar.isVisible = true
                authViewModel.login(
                    LoginBody(
                        email = binding.emailEditText.text.toString(),
                        password = binding.passwordEditText.text.toString()
                    )
                )
            }
        }



        binding.forgetPasswordTextView.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment()
            findNavController().navigate(action)
        }

        binding.createAccountTextView.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        observeResponse()
    }


    private fun isValidated(): Boolean {

        if (binding.emailEditText.text.toString().trim().isEmpty()) {
            binding.emailEditText.error = getString(R.string.required)
            return false
        } else {
            binding.emailEditText.error = null
        }

        if (binding.passwordEditText.text.toString().trim().isEmpty()) {
            binding.passwordEditText.error = getString(R.string.required)
            return false
        } else {
            binding.passwordEditText.error = null
        }

        return true
    }

    private fun observeResponse() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.loginFlow.collect {
                    handleLoginResponse(it)
                }
            }
        }
    }

    private fun handleLoginResponse(resource: Resource<LoginResponse>) {

        when (resource) {

            is Resource.Success -> {
                rememberMeFunctionality()
                binding.loadingProgressBar.loadingProgressBar.isVisible = false

                showSuccessToast(getString(R.string.signUp_successfully))
                navigateToHome()
            }

            is Resource.Error -> {
                binding.loadingProgressBar.loadingProgressBar.isVisible = false
                showErrorToast(resource.exception.message.toString() + " ")
                Log.e(
                    "hamzaAuth",
                    "handleLoginResponse: ${resource.exception.message}"
                )
            }

            else -> {}
        }

    }

    private fun navigateToHome() {
        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finishAffinity()
//        val action = LoginFragmentDirections.actionLoginFragmentToMainGraph()
//        findNavController().navigate(action)
    }

    private fun rememberMeFunctionality() {
        if (binding.rememberMeCheckBox.isChecked)
            rememberMe(
                context!!,
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        else
            rememberMe(context!!, "", "")
    }


}