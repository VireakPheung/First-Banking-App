package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.account

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember.BankAccount
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember.accountTypeLabel
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember.formatKhr
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember.formatMoney
import kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember.formatUsd
import kh.com.vireak.developer.basic.portfolioui.ui.theme.Blue
import kh.com.vireak.developer.basic.portfolioui.ui.theme.BlueSoft
import kh.com.vireak.developer.basic.portfolioui.ui.theme.CardBorder
import kh.com.vireak.developer.basic.portfolioui.ui.theme.NavyDark
import kh.com.vireak.developer.basic.portfolioui.ui.theme.PortfolioUiTheme
import kh.com.vireak.developer.basic.portfolioui.ui.theme.ScreenBg
import kh.com.vireak.developer.basic.portfolioui.ui.theme.Slate
import kh.com.vireak.developer.basic.portfolioui.ui.theme.SoftText
import kh.com.vireak.developer.basic.portfolioui.ui.theme.YellowDark
import kh.com.vireak.developer.basic.portfolioui.ui.theme.YellowSoft

@Composable
fun AccountContent(
    data: AccountsData,
    onAddAccount: () -> Unit ={}
){
    var filter by rememberSaveable {mutableStateOf(CurrencyFilter.ALL) }
    val usdAccounts = data.accounts.filter{it.currency == "USD" }
    val khrAccounts = data.accounts.filter{it.currency == "KHR" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Accounts",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyDark
                )
                Text(
                    text = "គណនី",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Slate
                )
            }
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(color = Color.White)
                    .border(1.dp, Slate, CircleShape)
                    .clickable { onAddAccount() },
                contentAlignment = Alignment.Center
            ) {
                Text("+", fontSize = 28.sp, color = NavyDark)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        TotalCard(data.summary)

        Spacer(modifier = Modifier.height(20.dp))
        //filter chip
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            CurrencyFilter.values().forEach { item ->
                CurrencyChip(
                    label = item.label,
                    selected = filter == item,
                    onClick = { filter = item }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        if(data.accounts.isEmpty()){
            Text(text = "You have no accounts yet", color =   Slate, fontSize = 14.sp)
        }else{
            if (filter != CurrencyFilter.KHR && usdAccounts.isNotEmpty()){
                AccountGroup("US DOLLAR · ដុល្លារអាមេរិក", usdAccounts)
            }

            if (filter != CurrencyFilter.USD && khrAccounts.isNotEmpty()){
                AccountGroup("KHMER RIEL · រៀលកម្ពុជា", khrAccounts)
            }
        }

    }
}

@Composable
private fun TotalCard(summary: AccountSummary){
    Column(
        modifier = Modifier
            .background(color = NavyDark, RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        Text("Total balance · សមតុល្យសរុប", color = SoftText, fontSize = 12.sp)

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = formatUsd(summary.totalUsdCents),
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CurrencyBox(
                label = "USD · ដុល្លារ",
                amount = formatUsd(summary.usdCents),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(6.dp))
            CurrencyBox(
                label = "KHR · រៀល",
                amount = formatKhr(summary.khr),
                modifier = Modifier.weight(1f)
            )

        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Exchange rate $1 = ៛4000",
            color = SoftText,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun CurrencyBox(label: String, amount: String, modifier: Modifier){
    Column(
        modifier = modifier
            .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(label, color = SoftText, fontSize = 11.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = amount,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private enum class CurrencyFilter(val label: String) {
    ALL("All"),
    USD("USD \$"),
    KHR("KHR ៛")
}

@Composable
private fun CurrencyChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit = {}
){
    val shape = RoundedCornerShape(50.dp)
    Box(
        modifier = Modifier
            .clip(shape)
            .background(if (selected) NavyDark else Color.White)
            .border(1.dp, if (selected) NavyDark else CardBorder, shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 22.dp, vertical = 12.dp)
    ){
        Text(
            text = label,
            color = if (selected) Color.White else NavyDark,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun AccountGroup(title: String, accounts: List<BankAccount>){
    Row(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.8.sp,
            color = Slate
        )

        Text(
            text = if (accounts.size == 1) "1 account" else "${accounts.size} accounts",
            fontSize = 13.sp,
            color = Slate
        )
    }
    accounts.forEach { account ->
        AccountCard(account)
        Spacer(modifier = Modifier.height(12.dp))
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
private fun AccountCard(account: BankAccount){
    val isUsd = account.currency == "USD"
    val shape = RoundedCornerShape(20.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape)
            .border(1.dp, CardBorder, shape)
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(if (isUsd) BlueSoft else YellowSoft, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isUsd) "\$" else "៛",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = if (isUsd) Blue else YellowDark
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = accountTypeLabel(account.type),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = NavyDark
            )
            Text(
                text = "${account.currency} •••• ${account.number}",
                fontSize = 14.sp,
                color = Slate
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = formatMoney(account.balance, account.currency),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = NavyDark
            )
            Text("Available", fontSize = 13.sp, color = Slate)
        }
    }

}


val fakeAccountData = AccountsData(
    summary = AccountSummary(
        totalUsdCents = 2314050,
        usdCents = 1569550,
        khr = 29780000,
        ),
    accounts = listOf(
        BankAccount(1, 1, "savings", "USD", "4821", 1248050, true),
        BankAccount(2, 1, "current", "USD", "7305", 321500, false),
        BankAccount(3, 1, "savings", "KHR", "1190", 25600000, false),
        BankAccount(4, 1, "current", "KHR", "6642", 4180000, false)
    )

)


@Preview(showBackground = false)
@Composable
fun AccountScreenPreview(){
    PortfolioUiTheme {
        AccountContent(
            data = fakeAccountData
        )
    }
}
