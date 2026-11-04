package kh.com.vireak.developer.basic.portfolioui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember.HomeScreen
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.welcomes.WelcomeScreen
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.login.LoginScreen
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.register.RegisterScreen
import kh.com.vireak.developer.basic.portfolioui.util.LoadingContent
import kh.com.vireak.developer.basic.portfolioui.util.LoadingUtil

@Composable
fun AppNavigation() {
    val backStack = remember { mutableStateListOf<NavigationKey>(NavigationKey.WelcomeScreen)}
    val onBack = {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },
        entryProvider = { key ->
            when (key) {
                NavigationKey.WelcomeScreen -> NavEntry(key){
                    WelcomeScreen(
                        onNavigateToLogin = {
                            backStack.add(NavigationKey.LoginScreen)
                        }
                    )
                }
                NavigationKey.LoginScreen -> NavEntry(key){
                    LoginScreen(
                        onLoginSuccess = {
                            backStack.add(NavigationKey.HomeScreen)
                        },
                        onNavigateToRegister = {
                            backStack.add(NavigationKey.RegisterScreen)
                        }
                    )
                }
                NavigationKey.RegisterScreen -> NavEntry(key){
                    RegisterScreen(
                        onBack = onBack,
                    )
                }
                NavigationKey.HomeScreen -> NavEntry(key){
                    HomeScreen ()
                }

                else -> throw IllegalArgumentException("Unknown navigation key: $key")
            }
        },
    )
    if (LoadingUtil.isLoading.value) {
        LoadingContent()
    }
}



