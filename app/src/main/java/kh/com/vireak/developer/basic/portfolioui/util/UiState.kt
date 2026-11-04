package kh.com.vireak.developer.basic.portfolioui.util


// One state for every screen. T is the data the screen shows on Success.
sealed interface UiState<out T> {
    object Idle : UiState<Nothing>
    object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}