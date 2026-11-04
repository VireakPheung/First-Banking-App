package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember

import android.R.attr.data
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kh.com.vireak.developer.basic.portfolioui.navigation.NavigationKey
import kh.com.vireak.developer.basic.portfolioui.ui.theme.PortfolioUiTheme

@Composable
fun HomeScreen(
    onClickItem: (NavigationKey) -> Unit = {},
){
    HomeContent(
        data = fakeData
    )
}

@Preview(showBackground = false)
@Composable
fun HomeScreenPreviews(){
    PortfolioUiTheme {
        HomeScreen()
    }
}