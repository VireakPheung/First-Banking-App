package kh.com.vireak.developer.basic.portfolioui.feature.welcome_screen.home.homemember.homemember

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.vireak.developer.basic.portfolioui.R
import kh.com.vireak.developer.basic.portfolioui.ui.theme.Blue
import kh.com.vireak.developer.basic.portfolioui.ui.theme.BlueSoft
import kh.com.vireak.developer.basic.portfolioui.ui.theme.CardBorder
import kh.com.vireak.developer.basic.portfolioui.ui.theme.GreenDark
import kh.com.vireak.developer.basic.portfolioui.ui.theme.GreenSoft
import kh.com.vireak.developer.basic.portfolioui.ui.theme.NavyDark
import kh.com.vireak.developer.basic.portfolioui.ui.theme.PortfolioUiTheme
import kh.com.vireak.developer.basic.portfolioui.ui.theme.PurpleDark
import kh.com.vireak.developer.basic.portfolioui.ui.theme.PurpleSoft
import kh.com.vireak.developer.basic.portfolioui.ui.theme.ScreenBg
import kh.com.vireak.developer.basic.portfolioui.ui.theme.Slate
import kh.com.vireak.developer.basic.portfolioui.ui.theme.SoftText
import kh.com.vireak.developer.basic.portfolioui.ui.theme.YellowDark
import kh.com.vireak.developer.basic.portfolioui.ui.theme.YellowSoft


@Composable
fun HomeContent (
    data: HomeData
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {

        HomeHeader(data.user)
        Spacer(modifier = Modifier.height(24.dp))
        BalanceCard(data = data, onSeeAllAccount = {})

        Spacer(modifier = Modifier.height(24.dp))
        SectionHeader(title = "Banking services · សេវាធនាគារ")
        ServiceGrid(items = bankingServices)

        Spacer(modifier = Modifier.height(24.dp))
        SectionHeader(title = "Promotion · ប្រូម៉ូសិន", action = "See all")
        PromotionRow(data.promotions)

        Spacer(modifier = Modifier.height(24.dp))
        SectionHeader(title = "Schedule កាលវិភាគ", action = "See all")
        ScheduledCard(data.schedule)

        Spacer(modifier = Modifier.height(24.dp))
        SectionHeader(title = "Favorite ចំណូលចិត្ត", action = "Edit")
        FavoriteCard(data.favorites)

        Spacer(modifier = Modifier.height(24.dp))
        SectionHeader(title = "Mini app កម្មវិធី", action = "Explore")
        MiniApp(data.miniApp)

    }
}

// ---------- fake data, so you can design without the server ----------
val fakeData = HomeData(
    user = UserProfile(1, "Sokha Kim", "sokha@example.com"),
    defaultAccount = BankAccount(1, 1, "savings", "USD", "4821", 1248050, true),
    accounts = listOf(
        BankAccount(1, 1, "savings", "USD", "4821", 1248050, true),
        BankAccount(3, 1, "savings", "KHR", "1190", 25600000, false)
    ),
    schedule = listOf(
        ScheduledPayment(1, "Rent", "Standing order", "$", 35000, "2026-9-20"),
        ScheduledPayment(2, "Electricity bill", "Auto pay", "៛", 186500, "2026-10-25"),
        ScheduledPayment(3, "Internet", "Auto pay", "៛", 68000, "2026-11-21"),
        ScheduledPayment(3, "Water", "Auto pay", "៛", 43000, "2026-12-21"),
        ScheduledPayment(3, "Trash", "Auto pay", "៛", 10000, "2026-4-21"),
    ),
    favorites = listOf(
        Favorite(1, "Mom", "MK", "blue"),
        Favorite(2, "Brother", "BT", "yellow"),
        Favorite(3, "Coffee", "CF", "green"),
        Favorite(4, "Electric", "EC", "purple"),
        Favorite(5, "Water", "WT", "green"),
        Favorite(6, "Trash", "Tr", "blue"),
    ),
    promotions = listOf(
        Promotion(1, "0% fee transfers", "ផ្ទេរមិនគិតកម្រៃ", "2026-10-31", "blue"),
        Promotion(2, "Earn 4.5% savings", "ការប្រាក់សន្សំ", "2026-12-31", "yellow"),
        Promotion(3,"Invite friend to get 1$ free", "អញ្ជើញមិត្តភក្តិ", "2026-12-31", "orange")
    ),
    miniApp = listOf(
        MiniApp(1, "Drive", "green", icon = R.drawable.ic_car),
        MiniApp(2, "Food", "blue", icon = R.drawable.ic_food),
        MiniApp(3, "Ticket",  "yellow", icon = R.drawable.ic_ticket),
        MiniApp(4, "Insurance",  "purple", icon = R.drawable.ic_insurance),
        MiniApp(5, "Investment",  "yellow", icon = R.drawable.ic_investment),
        MiniApp(6, "P2P Transfer",  "green", icon = R.drawable.ic_p2p),
        MiniApp(7, "Delivery",  "blue", icon = R.drawable.ic_delivery),
    )
)

private data class ServiceItem(
    val label: String,
    val khmer: String,
    @DrawableRes val icon: Int
)

private val bankingServices = listOf(
    ServiceItem(
        label = "Transfer",
        khmer = "ផ្ទេរ",
        icon = R.drawable.ic_transfer
    ),
    ServiceItem(
        label = "Pay bill",
        khmer = "បង់វិក្កយបត្រ",
        icon = R.drawable.ic_paybill
    ),
    ServiceItem(
        label = "Top Up",
        khmer = "បញ្ចូលទឹកប្រាក់",
        icon = R.drawable.ic_topup
    ),
    ServiceItem(
        label = "Scan QR",
        khmer = "ស្កេន",
        icon = R.drawable.ic_scan
    ),
    ServiceItem(
        label = "Laons",
        khmer = "កម្ចី",
        icon = R.drawable.ic_loan
    ),
    ServiceItem(
        label = "Card",
        khmer = "កាត",
        icon = R.drawable.ic_card
    ),
    ServiceItem(
        label = "Exchange",
        khmer = "ប្តូរប្រាក់",
        icon = R.drawable.ic_exchange
    ),
    ServiceItem(
        label = "More",
        khmer = "ផ្សេងៗ",
        icon = R.drawable.ic_morevert
    ),

)



@Composable
private fun SectionHeader(title: String, action: String? = null, onAction: () -> Unit = {}){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = NavyDark
        )

        if (action != null){
            Text(
                text = action,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Blue,
                modifier = Modifier.clickable{onAction()}
            )
        }
    }
}

// White rounded card with a thin border (used by several sections)
private fun Modifier.whiteCard(): Modifier = this
    .fillMaxWidth()
    .background(color = Color.White, RoundedCornerShape(20.dp))
    .border(1.dp, CardBorder, RoundedCornerShape(20.dp))



//grid used by Banking services and Mini apps
@Composable
private fun ServiceGrid(items: List<ServiceItem>, onClick: (ServiceItem) -> Unit = {}){
    Column(modifier = Modifier
    .whiteCard()
    .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        items.chunked(4).forEachIndexed { index, rowItems ->
            if (index > 0) Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                rowItems.forEach { item ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onClick(item) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(BlueSoft, RoundedCornerShape(16.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            item.label,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = NavyDark,
                            textAlign = TextAlign.Center
                        )
                        if (item.khmer.isNotEmpty()) {
                            Text(
                                item.khmer,
                                fontSize = 11.sp,
                                color = Slate,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeHeader(user: UserProfile){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .padding(8.dp)
                .background(NavyDark, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = initialsOf(user.name),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "សួស្តី · ${greeting()}",
                fontSize = 12.sp,
                color = Slate
            )

            Text(
                text = user.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = NavyDark
            )
        }

        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.5f)),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_notification),
                contentDescription = "Notification",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
private fun BalanceCard(data: HomeData, onSeeAllAccount: () -> Unit){
    val account = data.defaultAccount

    //the account in the other currency shown in bottom of the card
    val other = data.accounts.firstOrNull { it.currency != account?.currency } //explain this line

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = NavyDark, RoundedCornerShape(28.dp))
            .padding(24.dp)
    ) {
        if (account == null) {
            Text(
                text = "No Account yet",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Your accounts will appear here", color = SoftText, fontSize = 13.sp)
        } else  {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${accountTypeLabel(type = account.type)} •••• ${account.number}",
                    color = SoftText,
                    fontSize = 14.sp
                )
                if(account.isDefault){
                    Box(
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(50))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            "Default",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Available balance",
                color = Color.White,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = formatMoney(account.balance, account.currency),
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp), color = Color.White)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column{
                    if (other != null){
                        Text(
                            text = "${other.currency} · រៀល •••• ${other.number}",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Text(
                            text = formatMoney(other.balance, other.currency),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

                OutlinedButton(
                    modifier = Modifier.height(52.dp),
                    onClick = onSeeAllAccount,
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.5f)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Text(
                        text = "All Accounts",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


    }
}

@Composable
private fun PromotionRow(promotions: List<Promotion>){
    if(promotions.isEmpty()){
         Text(
             text = "No promotion right now",
             color = Slate,
             fontSize = 14.sp
         )
    }else{
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(promotions, key = {it.id}){ promo ->
                val yellow = promo.color == "yellow"
                val textColor = if (yellow) YellowDark else Color.White

                Column(
                    modifier = Modifier
                        .width(320.dp)
                        .height(160.dp)
                        .background(
                            if (yellow) YellowSoft else Blue,
                            RoundedCornerShape(20.dp)
                        ),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                        .padding(18.dp),
                        text = promo.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ){
                        promo.titleKm?.let { Text(it, fontSize = 13.sp, color = textColor) }
                        Text(
                            text = "Until ${promo.validUntil}",
                            fontSize = 12.sp,
                            color = textColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ScheduledCard(items: List<ScheduledPayment>){
    Column(
        modifier = Modifier
            .whiteCard()
    ) {
        if(items.isEmpty()){
            Text(
                text = "No Schedule Payment",
                color = Slate,
                fontSize = 14.sp,
                modifier = Modifier.padding(18.dp)
            )
        }else{
            items.forEachIndexed { index, payment ->
                if(index > 0){
                    HorizontalDivider()
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .background(ScreenBg, RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = monthShort(payment.date),
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                color = Slate
                            )
                            Text(
                                text = dayOf(payment.date),
                                fontSize = 18.sp,
                                color = NavyDark,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = payment.title,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = NavyDark
                        )
                        Text(
                            text = payment.kind,
                            fontSize = 12.sp,
                            color = Slate
                        )
                    }

                    Text(
                        text = "-" + formatMoney(amount = payment.amount, currency = payment.currency),
                        color = NavyDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    
                }
            }
        }
    }
}


@Composable
private fun FavoriteCard(favorite: List<Favorite>){
    Column(
        modifier = Modifier
            .whiteCard()
            .padding(16.dp)
    ) {
        if (favorite.isEmpty()){
            Text(
                text = "No Favorite yet",
                color = Slate,
                fontSize = 14.sp,
                modifier = Modifier.padding(18.dp)
            )
        }else{
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favorite, key = {it.id}){ fav ->

                    //this is decided to color the favorite box
                    val fg: Color
                    val bg: Color
                    if (fav.color == "yellow"){
                        bg = YellowSoft
                        fg = YellowDark
                    }else if (fav.color == "green") {
                        bg = GreenSoft
                        fg = GreenDark
                    } else if (fav.color == "purple") {
                        bg = PurpleSoft
                        fg = PurpleDark
                    } else {
                        bg = BlueSoft
                        fg = Blue
                    }

                    Column(
                        modifier = Modifier.width(64.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(bg, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                fav.initial,
                                color = fg,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            fav.name,
                            fontSize = 13.sp,
                            color = NavyDark,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }


                }

            }
        }
    }
}

@Composable
private fun MiniApp(miniApp: List<MiniApp>){
    Column(
        modifier = Modifier
            .whiteCard()
            .padding(16.dp)
    ){
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(miniApp, key = {it.id}){ mini ->

                //this is decided to color the favorite box
                val fg: Color
                val bg: Color
                if (mini.color == "yellow"){
                    bg = YellowSoft
                    fg = YellowDark
                }else if (mini.color == "green") {
                    bg = GreenSoft
                    fg = GreenDark
                } else if (mini.color == "purple") {
                    bg = PurpleSoft
                    fg = PurpleDark
                } else {
                    bg = BlueSoft
                    fg = Blue
                }

                Column(
                    modifier = Modifier.width(64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(bg, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(mini.icon),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        mini.name,
                        fontSize = 13.sp,
                        color = NavyDark,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }


            }

        }
    }

}

@Preview(showBackground = false)
@Composable
fun HomeContentPreview(){
    PortfolioUiTheme{
        HomeContent(
            data = fakeData
        )
    }
}



