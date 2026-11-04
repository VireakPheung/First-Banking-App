package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
