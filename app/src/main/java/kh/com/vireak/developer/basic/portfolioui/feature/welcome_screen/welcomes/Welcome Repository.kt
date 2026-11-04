package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.welcomes

import androidx.annotation.DrawableRes
import kh.com.vireak.developer.basic.portfolioui.R


data class PagerModel(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)


class WelcomeRepository{
    fun onboardingPages(): List<PagerModel> = listOf(
        PagerModel(
            "Secures Transactions & Reliable Anytime",
            "You can get 100% security in every transaction. So, you can blindly rely on us.",
            R.drawable.img_pager1
        ),
        PagerModel(
            "Fast & Easy Payments",
            "Send and receive money in just a few taps. No hassle, no long waiting times.",
            R.drawable.img_pager2
        ),
        PagerModel(
            "Track Your Spending",
            "Get a clear overview of your expenses and manage your budget smarter every day.",
            R.drawable.img_pager3
        )
    )
}