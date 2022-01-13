package com.example.bitsoapp20.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitsoapp20.databinding.MainFragmentBinding
import com.example.bitsoapp20.domain.AvailableBook
import com.example.bitsoapp20.domain.Currency
import com.example.bitsoapp20.ui.availableBooks.AdapterAvailableBooks
import com.example.bitsoapp20.ui.currency.AdapterCurrency
import com.example.bitsoapp20.ui.currency.ViewModelCurrency
import com.example.bitsoapp20.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment:Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding
        get() = _binding!!

    private lateinit var adapterCurrency: AdapterCurrency
    private lateinit var adapterAvailableBook: AdapterAvailableBooks
    private val viewModel:ViewModelCurrency by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MainFragmentBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapterCurrency= AdapterCurrency {book->
            fillAvailableBooks(book)
        }
        adapterAvailableBook=AdapterAvailableBooks{ }

        viewModel.callServices()


        viewModel.currencies.observe(viewLifecycleOwner,{ resources ->
            fillCurrencies(resources)
        })

        binding.AvailableBooksList.run {
            adapter=adapterAvailableBook
            layoutManager= object : LinearLayoutManager(requireContext()) {

            }
        }
    }

    private fun fillCurrencies(resources:Resource<List<Currency>>){
        binding.currencyList.run {
            adapter=adapterCurrency
            layoutManager= object : LinearLayoutManager(requireContext(),HORIZONTAL,false) {

            }
        }
        when(resources.status){
            Resource.Status.SUCCESS->{
                adapterCurrency.submitList(resources.data)
            }
            Resource.Status.ERROR->
                Toast.makeText(activity, resources.message, Toast.LENGTH_SHORT).show()

        }


    }
    private fun fillAvailableBooks(book:String){
        viewModel.availableBooks.observe(viewLifecycleOwner,{resources ->
            when(resources.status){
                Resource.Status.SUCCESS->{
                    adapterAvailableBook.submitList(resources.data?.filter {
                        it.book.split("_")[0] == book
                    })
                }
                Resource.Status.ERROR->
                    Toast.makeText(activity, resources.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}