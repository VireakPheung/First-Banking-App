package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.account

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kh.com.vireak.developer.basic.portfolioui.ui.theme.PortfolioUiTheme

@Composable
fun AccountScreen(){
    AccountContent(data = fakeAccountData)
}


@Preview(showBackground = false)
@Composable
fun AccountPreview(){
    PortfolioUiTheme {
        AccountScreen()
    }
}