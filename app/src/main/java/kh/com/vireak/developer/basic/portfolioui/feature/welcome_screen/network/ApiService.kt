package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.network

import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.request.LoginRequest
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.request.RegisterRequest
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>
}