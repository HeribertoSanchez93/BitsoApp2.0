package com.example.bitsoapp20.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitsoapp20.domain.AvailableBook
import com.example.bitsoapp20.domain.Currency
import com.example.bitsoapp20.repo.RepoAvailableBooks
import com.example.bitsoapp20.repo.RepoCurrency
import com.example.bitsoapp20.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCurrency @Inject constructor (
    private val repoCurrency: RepoCurrency,
    private val repoAvailableBooks: RepoAvailableBooks
        ):ViewModel() {

    private val _currencies: MutableLiveData<Resource<List<Currency>>> = MutableLiveData()
    var currencies: LiveData<Resource<List<Currency>>> = _currencies

    private val _availableBooks: MutableLiveData<Resource<List<AvailableBook>>> = MutableLiveData()
    var availableBooks: LiveData<Resource<List<AvailableBook>>> = _availableBooks

    fun callServices(){
        getListOfAvailableBooks()
    }

    private fun getListOfAvailableBooks() {
        CoroutineScope(Dispatchers.IO).launch {
            _availableBooks.postValue(Resource.loading())
            val listAvailableBooks=repoAvailableBooks.getAvailableBooks()

            getListOfCurrencies(listAvailableBooks)
            val listData= encapsulateList(listAvailableBooks)
            _availableBooks.postValue(listData as Resource<List<AvailableBook>>?)
        }
    }
    private fun getListOfCurrencies(listAvailableBooks:List<AvailableBook>?) {
        CoroutineScope(Dispatchers.IO).launch {
            _currencies.postValue(Resource.loading())
            val listCurrencies= repoCurrency.getCurrencies(listAvailableBooks)
            val listData= encapsulateList(listCurrencies)

            _currencies.postValue(listData)

        }
    }
    private fun <T> encapsulateList(listData:T):Resource<T>{
        return if(listData!=null)
            Resource.success(listData)
        else
            Resource.error("Network call has failed")
    }

}