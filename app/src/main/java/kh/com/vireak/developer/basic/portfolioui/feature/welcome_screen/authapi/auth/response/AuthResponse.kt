package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.response

import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.auth.AuthUser

data class AuthResponse (
    val token: String,
    val user: AuthUser
)