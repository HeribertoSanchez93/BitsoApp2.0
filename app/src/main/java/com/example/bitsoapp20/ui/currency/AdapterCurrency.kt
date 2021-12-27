package com.example.bitsoapp20.ui.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitsoapp20.databinding.ListCurrenciesAdapterBinding
import com.example.bitsoapp20.domain.Currency
import com.example.bitsoapp20.util.attachedName

typealias OnCurrencyClicked = (String) -> Unit

class AdapterCurrency (
    private val onCurrencyClicked: OnCurrencyClicked
        ): ListAdapter<Currency, AdapterCurrency.CurrencyViewHolder>(DIFF_CALLBACK)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return LayoutInflater.from(parent.context)
            .let { inflater -> ListCurrenciesAdapterBinding.inflate(inflater, parent, false) }
            .let { binding -> CurrencyViewHolder(binding, onCurrencyClicked) }
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CurrencyViewHolder(
        private val binding: ListCurrenciesAdapterBinding,
        private val onCurrencyClicked: OnCurrencyClicked,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currency) {
            binding.currency.text=currency.code
            binding.nameCurrency.text = currency.name.attachedName()
            Glide.with(binding.currencyImage)
                .load(currency.imageUrl)
                .into(binding.currencyImage)

            binding.linearLayoutCurrency.setOnClickListener {
                onCurrencyClicked.invoke(currency.name)
            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Currency>() {
            override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem.code == newItem.code

            override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem == newItem
        }
    }
}