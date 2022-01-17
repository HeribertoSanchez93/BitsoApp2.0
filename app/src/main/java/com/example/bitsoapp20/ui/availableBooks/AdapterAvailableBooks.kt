package com.example.bitsoapp20.ui.availableBooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitsoapp20.databinding.ListAvailableBooksAdapterBinding
import com.example.bitsoapp20.databinding.ListCurrenciesAdapterBinding
import com.example.bitsoapp20.domain.AvailableBook
import com.example.bitsoapp20.domain.Currency
import com.example.bitsoapp20.util.reformatNumber

typealias OnAvailableBooksClicked = (String) -> Unit

class AdapterAvailableBooks(
    private val onAvailableBooksClicked: OnAvailableBooksClicked
): ListAdapter<AvailableBook, AdapterAvailableBooks.AvailableBooksViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableBooksViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater-> ListAvailableBooksAdapterBinding.inflate(inflater,parent,false) }
            .let {binding-> AvailableBooksViewHolder(binding,onAvailableBooksClicked) }
    }

    override fun onBindViewHolder(holder: AvailableBooksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class AvailableBooksViewHolder(
        private val binding: ListAvailableBooksAdapterBinding,
        private val onAvailableBooksClicked: OnAvailableBooksClicked,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(availableBook: AvailableBook){
            binding.textBook.text=availableBook.book
            binding.minPrice.text= availableBook.minimum_price.reformatNumber()
            binding.maxPrice.text= availableBook.maximum_price.reformatNumber()
            binding.minAmount.text= availableBook.minimum_amount.reformatNumber()
            binding.maxAmount.text= availableBook.maximum_amount.reformatNumber()
            binding.minValue.text= availableBook.minimum_value.reformatNumber()
            binding.maxValue.text= availableBook.maximum_value.reformatNumber()
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AvailableBook>() {
            override fun areItemsTheSame(oldItem: AvailableBook, newItem: AvailableBook): Boolean =
                oldItem.book == newItem.book

            override fun areContentsTheSame(oldItem: AvailableBook, newItem: AvailableBook): Boolean =
                oldItem == newItem
        }
    }
}