package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.network

object SessionManager {
    @Volatile
    var token: String? = null

    fun clear(){
        token = null
    }
}