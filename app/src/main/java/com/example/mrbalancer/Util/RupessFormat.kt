package com.example.mrbalancer.Util

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

object RupessFormat {


    fun rupessConverter(Amount:Int):String?{

        val rupeesFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        rupeesFormat.currency = Currency.getInstance("INR")
        val formattedAmount = rupeesFormat.format(Amount)
        return formattedAmount
    }
}