package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember

import androidx.annotation.DrawableRes

data class UserProfile (
    val id: Int,
    val name: String,
    val email: String
)

data class BankAccount(
    val id: Int,
    val userId: Int,
    val type: String,
    val currency: String,
    val number: String,
    val balance: Long,
    val isDefault: Boolean
)
data class ScheduledPayment(
    val id: Int,
    val title: String,
    val kind: String,
    val currency: String,
    val amount: Long,
    val date: String
)
data class Favorite(
    val id: Int,
    val name: String,
    val initial: String,
    val color: String
)

data class Promotion(
    val id: Int,
    val title: String,
    val titleKm: String?,
    val validUntil: String,
    val color: String
)

// Everything the Home screen needs, in one object
data class HomeData(
    val user: UserProfile,
    val defaultAccount: BankAccount?,   // null if the user has no accounts yet
    val accounts: List<BankAccount>,
    val schedule: List<ScheduledPayment>,
    val favorites: List<Favorite>,
    val promotions: List<Promotion>,
    val miniApp: List<MiniApp>
)

data class MiniApp(
    val id: Int,
    val name: String,
    val color: String,
    @DrawableRes val icon: Int,
)

