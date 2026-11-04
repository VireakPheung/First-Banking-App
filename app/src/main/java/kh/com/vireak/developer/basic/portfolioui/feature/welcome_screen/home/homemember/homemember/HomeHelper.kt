package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember

import java.util.Calendar
import java.util.Locale
import kotlin.math.abs

fun formatMoney(amount: Long, currency: String): String =
    if (currency == "USD") formatUsd(amount) else formatKhr(amount)


fun formatUsd(cents: Long): String{
    val sign = if (cents < 0 ) "-" else ""
    val value = abs(cents)
    return String.format(Locale.US, "%s$%.2f", sign, value / 100.0)
}

fun formatKhr(riel: Long): String{
    val sign = if (riel < 0) "-" else ""
    return String.format(Locale.US, "%s៛%,d", sign, abs(riel))
}

private val months = listOf(
    "Jan", "Feb", "Mar", "Apr", "May", "Jun",
    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
)

// "2026-10-20" -> "Oct"
fun monthShort(date: String): String {
    val month = date.split("-").getOrNull(1)?.toIntOrNull() ?: return ""
    return months.getOrElse(month - 1) { "" }
}

// "2026-10-20" -> "20"
fun dayOf(date: String): String = date.split("-").getOrNull(2) ?: ""

fun greeting(): String {
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when {
        hour < 12 -> "Good morning"
        hour < 18 -> "Good afternoon"
        else -> "Good evening"
    }
}

//for user default user profile, cut off the first and last name
// "Sokha Kim" -> "SK"
fun initialsOf(name: String): String =
    name.trim().split(" ").filter { it.isNotBlank() }
        .take(2).joinToString("") { it.first().uppercase() }
        .ifEmpty { "?" }

fun accountTypeLabel(type: String): String =
    if (type == "current") "Current · ចរន្ត" else "Savings · សន្សំ"
