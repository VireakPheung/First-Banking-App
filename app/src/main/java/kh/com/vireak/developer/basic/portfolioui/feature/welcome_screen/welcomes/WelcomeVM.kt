package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.welcomes

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WelcomeVM (
    private val welcomeRepository: WelcomeRepository = WelcomeRepository()
) : ViewModel() {
    private val _page = MutableStateFlow (welcomeRepository.onboardingPages())
    val page = _page.asStateFlow()


}