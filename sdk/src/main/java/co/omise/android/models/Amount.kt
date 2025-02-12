package co.omise.android.models

/**
 * Amount handles formatting of a given amount based on the supplied currency.
 *
 * @param amount The specified amount.
 * @param currency The specified currency.
 */
data class Amount(val amount: Long, val currency: String) {

    val localAmount: Double
        get() = when (currency.toLowerCase()) {
            "jpy" -> amount.toDouble()
            else -> amount / 100.0
        }

    fun toAmountString(): String {
        return "$localAmount ${currency.toUpperCase()}"
    }

    fun toString(decimalPlaces: Int): String {
        return String.format("%." + decimalPlaces + "f", localAmount)
    }

    companion object {

        @JvmStatic
        fun fromLocalAmount(localAMount: Double, currency: String): Amount {
            val subunitAmount = if (currency == "jpy") {
                localAMount.toLong()
            } else {
                (localAMount*100).toLong()
            }
            return Amount(subunitAmount, currency)
        }
    }
}
