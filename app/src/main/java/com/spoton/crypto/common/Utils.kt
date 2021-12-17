package com.spoton.crypto.common

// Extension function to get price with just 5 digits after decimal.
fun String.toFiveDecimalStringWithDollar(): String {
    return "$${String.format("%.5f", this.toDouble())}"
}

// Extension function to get 24 hour change with just 2 digits after decimal & add + sign if price has increased.
fun String.toTwoDecimalStringWithPercentage(): String {
    val stringInDouble = this.toDouble()
    var stringToReturn = "${String.format("%.2f", stringInDouble)}%"
    if (stringInDouble > 0) {
        stringToReturn = "+".plus(stringToReturn)
    }
    return stringToReturn
}
