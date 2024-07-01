package com.fee.aqarat.ui.auth.register

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
import com.fee.aqarat.databinding.FragmentRegisterBinding
import com.fee.aqarat.domain.models.auth.SignUpBody
import com.fee.aqarat.domain.models.auth.SignUpResponse
import com.fee.aqarat.ui.auth.AuthViewModel
import com.fee.aqarat.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val authViewModel: AuthViewModel by viewModels()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

        binding.signUpButtonLayout.setOnClickListener {
            if (isValidated()) {
                binding.loadingProgressBar.loadingProgressBar.isVisible = true
                authViewModel.signUp(
                    SignUpBody(
                        username = binding.nameEditText.text.toString(),
                        email = binding.emailEditText.text.toString(),
                        mobileNum = listOf(binding.phoneEditText.text.toString()),
                        password = binding.passwordEditText.text.toString()
                    )
                )
            }
        }

        observeResponse()

    }

    private fun isValidated(): Boolean {
        if (binding.nameEditText.text.toString().trim().isEmpty()) {
            binding.nameEditText.error = getString(R.string.required)
            return false
        } else {
            binding.nameEditText.error = null
        }

        if (binding.emailEditText.text.toString().trim().isEmpty()) {
            binding.emailEditText.error = getString(R.string.required)
            return false
        } else {
            binding.emailEditText.error = null
        }

        if (binding.phoneEditText.text.toString().trim().isEmpty()) {
            binding.phoneEditText.error = getString(R.string.required)
            return false
        } else {
            binding.phoneEditText.error = null
        }

        if (binding.passwordEditText.text.toString().trim().isEmpty()) {
            binding.passwordEditText.error = getString(R.string.required)
            return false
        } else {
            binding.passwordEditText.error = null
        }

        val password = binding.passwordEditText.text.toString()
        val confirmPassword = binding.confirmPasswordEditText.text.toString()

        if (binding.confirmPasswordEditText.text.toString().trim().isEmpty()) {
            binding.confirmPasswordEditText.error = getString(R.string.required)
            return false
        } else if (password != confirmPassword) {

            binding.confirmPasswordEditText.error = null
            binding.confirmPasswordEditText.error = getString(R.string.passwords_not_the_same)
            return false
        } else binding.confirmPasswordEditText.error = null

        return true
    }

    private fun observeResponse() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.signUpFlow.collect {
                    handleSignUpResponse(it)
                }
            }
        }
    }

    private fun handleSignUpResponse(resource: Resource<SignUpResponse>) {

        when (resource) {

            is Resource.Success -> {
                binding.loadingProgressBar.loadingProgressBar.isVisible = false
                showSuccessToast(getString(R.string.signUp_successfully))
                navigateToHome()
            }

            is Resource.Error -> {
                binding.loadingProgressBar.loadingProgressBar.isVisible = false
                showErrorToast(resource.exception.message.toString() + " ")
                Log.e(
                    "hamzaAuth",
                    "handleSignUpResponse: ${resource.exception.message}"
                )
            }

            else -> {}
        }

    }

    private fun navigateToHome() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToMainGraph()
        findNavController().navigate(action)
    }

}