package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.request.LoginRequest
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.request.RegisterRequest
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.response.AuthResponse
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.authapi.auth.response.ErrorResponse
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.network.RetrofitClient
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.network.SessionManager
import kh.com.vireak.developer.basic.portfolioui.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response





class AuthVm : ViewModel() {
    private val _state = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val state = _state.asStateFlow()


    fun login(email: String, password: String){
        if (email.isBlank() || password.isBlank()){
            _state.value = UiState.Error(message = "Email and password can not be blank")
            return
        }
        viewModelScope.launch {
            call { RetrofitClient.api.login(LoginRequest(email, password))}
        }
    }

    fun register(name: String, email: String, password: String){
        if(name.isBlank() || email.isBlank() || password.isBlank()){
            _state.value = UiState.Error(message = "Please fill in these fields")
            return
        }
        viewModelScope.launch {
            call { RetrofitClient.api.register(RegisterRequest(name, email, password))}
        }
    }

    fun resetState(){
        _state.value = UiState.Idle
    }


    private suspend fun call(request: suspend () -> Response<AuthResponse>) {
        _state.value = UiState.Loading
        try {
            val response = request()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                SessionManager.token = body.token
                _state.value = UiState.Success(Unit)
            } else {
                _state.value = UiState.Error(message = readError(response))
            }
        }catch (e: Exception){
            _state.value = UiState.Error(message = e.message ?: "Something went wrong please try again")
        }
    }

    // The API answers errors like {"error":"Wrong email or password"}
    private fun readError(response: Response<*>): String {
        if(response.code() == 401) return "Wrong email or password"
        return try {
            val json = response.errorBody()?.string()
            Gson().fromJson(json, ErrorResponse::class.java)?.string
                ?: "Error ${response.code()}"
        } catch (e: Exception) {
            "Error ${response.code()}"
        }
    }
}