package ru.alexsergeev.express.screens

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.alexsergeev.express.LockScreenOrientation
import ru.alexsergeev.express.R
import ru.alexsergeev.express.rates.BusinessRate
import ru.alexsergeev.express.rates.ComfortRate
import ru.alexsergeev.express.rates.EconomyRate
import ru.alexsergeev.express.rates.MinivanRate
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.utils.pagerTabIndicatorOffset

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RateScreen(
    navController: NavController,
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val tabList = listOf("Эконом", "Комфорт", "Бизнес", "Минивэн")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, bottom = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.Black)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
                .size(170.dp)
                .padding(top = 48.dp),
            painter = painterResource(id = R.drawable.slavlogonew),
            contentDescription = "test image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            text = "Выбор тарифа",
            color = Color.White
        )
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { position ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, position)
                )
            },
            containerColor = DarkRed,
            contentColor = Color.White
        ) {
            tabList.forEachIndexed { index, level ->
                Tab(selected = tabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = level)
                    }
                )
            }
        }
        HorizontalPager(
            count = tabList.size,
            state = pagerState,
            modifier = Modifier
                .weight(1.0f)
                .fillMaxHeight(0.5f)
        ) { index ->
            when (tabIndex) {
                0 -> EconomyRate(
                    navController = navController,
                )

                1 -> ComfortRate(
                    navController = navController,
                )

                2 -> BusinessRate(
                    navController = navController,
                )

                else -> MinivanRate(
                    navController = navController,
                )
            }
        }
    }
}