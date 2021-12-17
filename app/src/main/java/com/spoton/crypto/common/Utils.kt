package com.spoton.crypto.common

fun String.toFiveDecimalStringWithDollar(): String {
    return "$${String.format("%.5f", this.toDouble())}"
}

fun String.toTwoDecimalStringWithPercentage(): String {
    val stringInDouble = this.toDouble()
    var stringToReturn = "${String.format("%.2f", stringInDouble)}%"
    if (stringInDouble > 0) {
        stringToReturn = "+".plus(stringToReturn)
    }
    return stringToReturn
}
