package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.account

import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember.BankAccount


// Matches the JSON from GET /accounts/summary
data class AccountSummary(
    val totalUsdCents: Long,   // everything converted to dollars, in cents
    val usdCents: Long,        // USD accounts only
    val khr: Long,             // KHR accounts only, in riel
)

// Everything the Accounts screen needs
data class AccountsData(
    val summary: AccountSummary,
    val accounts: List<BankAccount>
)