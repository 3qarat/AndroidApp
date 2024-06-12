package com.fee.aqarat.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fee.aqarat.base.BaseFragment
import com.fee.aqarat.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate


    override fun prepareView(savedInstanceState: Bundle?) {

    }


}