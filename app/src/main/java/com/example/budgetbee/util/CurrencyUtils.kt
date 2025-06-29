package com.example.budgetbee.util

import java.util.Locale
import kotlin.math.abs

/**
 * Utility for formatting currency values with Indonesian suffixes:
 * - Juta for millions
 * - Miliar for billions
 * - Triliun for trillions
 * Formats with two decimal places.
 */
object CurrencyUtils {
    fun format(value: Double): String {
        val absValue = abs(value)
        val locale = Locale("in", "ID")
        val formatted = when {
            absValue >= 1_000_000_000_000_000 -> String.format(locale, "Rp %,.1f Kuadriliun", absValue / 1_000_000_000_000_000)
            absValue >= 1_000_000_000_000 -> String.format(locale, "Rp %,.1f Triliun", absValue / 1_000_000_000_000)
            absValue >= 1_000_000_000 -> String.format(locale, "Rp %,.1f Miliar", absValue / 1_000_000_000)
            absValue >= 1_000_000 -> String.format(locale, "Rp %,.1f Juta", absValue / 1_000_000)
            else -> String.format(locale, "Rp %,.2f", absValue)
        }
        return if (value < 0) "- $formatted" else formatted
    }
    fun fullformat(value: Double): String{
        val absValue = abs(value)
        val locale = Locale("in", "ID")
        return String.format(locale, "Rp %,.2f", absValue)
    }
}
