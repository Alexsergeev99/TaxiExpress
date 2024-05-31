package ru.alexsergeev.express.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.alexsergeev.express.R
import ru.alexsergeev.express.rates.BusinessRate
import ru.alexsergeev.express.rates.ComfortRate
import ru.alexsergeev.express.rates.EconomyRate
import ru.alexsergeev.express.rates.MinivanRate
import ru.alexsergeev.express.ui.theme.DarkRed

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RateScreen(
    navController: NavController,
    name: String?,
    phone: String?,
    from: String?,
    to: String?,
    date: String?,
    time: String?,
    passengers: String?
) {
    val tabList = listOf("Эконом", "Комфорт", "Бизнес", "Минивэн")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    val config = LocalConfiguration.current
    val portraitMode = remember {
        mutableStateOf(config.orientation)
    }
    if(portraitMode.value == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
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
                    name.toString(),
                    phone.toString(),
                    from.toString(),
                    to.toString(),
                    date.toString(),
                    time.toString(),
                    passengers.toString()
                )

                1 -> ComfortRate(navController = navController,
                    name.toString(),
                    phone.toString(),
                    from.toString(),
                    to.toString(),
                    date.toString(),
                    time.toString(),
                    passengers.toString())
                2 -> BusinessRate(navController = navController,
                    name.toString(),
                    phone.toString(),
                    from.toString(),
                    to.toString(),
                    date.toString(),
                    time.toString(),
                    passengers.toString())
                else -> MinivanRate(navController = navController,
                    name.toString(),
                    phone.toString(),
                    from.toString(),
                    to.toString(),
                    date.toString(),
                    time.toString(),
                    passengers.toString())
            }
        }
    }
    } else {
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
                    .size(130.dp)
                    .padding(top = 8.dp),
                painter = painterResource(id = R.drawable.slavlogonew),
                contentDescription = "test image"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
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
                        name.toString(),
                        phone.toString(),
                        from.toString(),
                        to.toString(),
                        date.toString(),
                        time.toString(),
                        passengers.toString()
                    )

                    1 -> ComfortRate(navController = navController,
                        name.toString(),
                        phone.toString(),
                        from.toString(),
                        to.toString(),
                        date.toString(),
                        time.toString(),
                        passengers.toString())
                    2 -> BusinessRate(navController = navController,
                        name.toString(),
                        phone.toString(),
                        from.toString(),
                        to.toString(),
                        date.toString(),
                        time.toString(),
                        passengers.toString())
                    else -> MinivanRate(navController = navController,
                        name.toString(),
                        phone.toString(),
                        from.toString(),
                        to.toString(),
                        date.toString(),
                        time.toString(),
                        passengers.toString())
                }
            }
        }
    }
}

@ExperimentalPagerApi
fun Modifier.pagerTabIndicatorOffset(
    pagerState: PagerState,
    tabPositions: List<TabPosition>,
    pageIndexMapping: (Int) -> Int = { it },
): Modifier = layout { measurable, constraints ->
    if (tabPositions.isEmpty()) {
        layout(constraints.maxWidth, 0) {}
    } else {
        val currentPage = minOf(tabPositions.lastIndex, pageIndexMapping(pagerState.currentPage))
        val currentTab = tabPositions[currentPage]
        val previousTab = tabPositions.getOrNull(currentPage - 1)
        val nextTab = tabPositions.getOrNull(currentPage + 1)
        val fraction = pagerState.currentPageOffset
        val indicatorWidth = if (fraction > 0 && nextTab != null) {
            lerp(currentTab.width, nextTab.width, fraction).roundToPx()
        } else if (fraction < 0 && previousTab != null) {
            lerp(currentTab.width, previousTab.width, -fraction).roundToPx()
        } else {
            currentTab.width.roundToPx()
        }
        val indicatorOffset = if (fraction > 0 && nextTab != null) {
            lerp(currentTab.left, nextTab.left, fraction).roundToPx()
        } else if (fraction < 0 && previousTab != null) {
            lerp(currentTab.left, previousTab.left, -fraction).roundToPx()
        } else {
            currentTab.left.roundToPx()
        }
        val placeable = measurable.measure(
            Constraints(
                minWidth = indicatorWidth,
                maxWidth = indicatorWidth,
                minHeight = 0,
                maxHeight = constraints.maxHeight
            )
        )
        layout(constraints.maxWidth, maxOf(placeable.height, constraints.minHeight)) {
            placeable.placeRelative(
                indicatorOffset,
                maxOf(constraints.minHeight - placeable.height, 0)
            )
        }
    }
}
