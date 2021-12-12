package com.example.bitsoapp20.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitsoapp20.databinding.MainFragmentBinding
import com.example.bitsoapp20.mock.mockLogos
import com.example.bitsoapp20.ui.currency.AdapterCurrency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment:Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding
        get() = _binding!!

    private lateinit var adapterCurrency: AdapterCurrency

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MainFragmentBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapterCurrency= AdapterCurrency {  }
        binding.currencyList.run {
            adapter=adapterCurrency
            layoutManager= object : LinearLayoutManager(requireContext(),HORIZONTAL,false) {

            }
        }
        adapterCurrency.submitList(mockLogos)
    }
}