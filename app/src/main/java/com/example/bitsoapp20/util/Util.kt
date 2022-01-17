package com.example.bitsoapp20.util

import com.example.bitsoapp20.domain.AvailableBook
import com.example.bitsoapp20.domain.Currency
import com.example.bitsoapp20.domain.StatusAvailableBooks
import java.text.DecimalFormat

private const val CURRENCY_BASE_IMAGE_URL = "https://cryptoicon-api.vercel.app/api/icon/"

suspend  fun <T> performGetOperation(networkCall: suspend () -> Resource<T>): Resource<T>{
     return networkCall.invoke()
}

suspend  fun <T> performGetOperation2(networkCall: suspend () -> T): T{
     return networkCall.invoke()
}

fun StatusAvailableBooks.filterData(code: String?): List<AvailableBook> {
     return this.payload.filter {
          it.book.split("_")[0] == code
     }
}

fun List<AvailableBook>?.getCurrencies(): List<Currency> {
     val _currencies = mutableListOf<Currency>()

     this?.iterator()?.forEach { availableBook ->
          val splitNameBook = availableBook.book.split("_")
          val tempCurrency = Currency(
               code = splitNameBook[0],
               name = splitNameBook[0],
               imageUrl = CURRENCY_BASE_IMAGE_URL + splitNameBook[0]
          )
          if (!_currencies.contains(tempCurrency)) {
               _currencies.add(tempCurrency)
          }
     }
     return _currencies
}
fun String.attachedName(): String = when (this) {
     "btc" -> "bitcoin"
     "eth" -> "Ethereum"
     "xrp" -> "Xrp"
     "ltc" -> "Litecoin"
     "bch" -> "Bitcoin Cash"
     "tusd" -> "True Usd"
     "mana" -> "Mana"
     "bat" -> "Bat"
     "dai" -> "Dai"
     "usd" -> "Usd"
     "comp" -> "Compound"
     "link" -> "Chain Link"
     "uni" -> "Uni Swap"
     "aave" -> "Aave"
     "chz" -> "chiliz"
     "axs" -> "axs"
     else -> this
}
fun Double.reformatNumber(): String = DecimalFormat("#.################$").format(this)