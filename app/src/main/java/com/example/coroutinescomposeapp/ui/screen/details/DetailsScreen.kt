@file:OptIn(ExperimentalPagerApi::class)

package com.example.coroutinescomposeapp.ui.screen.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.coroutinescomposeapp.R
import com.example.coroutinescomposeapp.ui.model.DetailsUI
import com.example.coroutinescomposeapp.ui.screen.ErrorScreen
import com.example.coroutinescomposeapp.ui.screen.LoadingScreen
import com.example.coroutinescomposeapp.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage

@Preview(showBackground = true)
@Composable
private fun DetailsPreview() {
    CoroutinesComposeAppTheme {
//        DetailsSuccessScreen {}
    }
}

@Composable
fun DetailsScreen(
    uiState: DetailsState,
    modifier: Modifier,
    onBackClicked: () -> Unit,
    onAddToCartClicked: () -> Unit
) {
    when (uiState) {
        DetailsState.Loading -> LoadingScreen()
        is DetailsState.Success -> DetailsSuccessScreen(
            detailsUI = uiState.details,
            modifier = modifier,
            onBackClicked = onBackClicked,
            onAddToCartClicked = onAddToCartClicked
        )
        DetailsState.Error -> ErrorScreen()
    }
}

@Composable
fun DetailsSuccessScreen(
    detailsUI: DetailsUI,
    modifier: Modifier,
    onBackClicked: () -> Unit,
    onAddToCartClicked: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    var quantity by remember { mutableStateOf(1) }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
        topBar = { DetailsTopAppBar(onBackClicked = onBackClicked) }
    ) { paddingValues ->
        DetailsBody(
            modifier = Modifier.padding(paddingValues),
            detailsUI = detailsUI,
            quantity = quantity,
            onMinusClicked = { if (quantity != 1) quantity -= 1 },
            onPlusClicked = { quantity += 1 },
            onAddToCartClicked = onAddToCartClicked
        )
    }
}

@Composable
private fun DetailsTopAppBar(onBackClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIos,
                    contentDescription = stringResource(R.string.back)
                )
            }
        })
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun DetailsBody(
    modifier: Modifier,
    detailsUI: DetailsUI,
    quantity: Int,
    onMinusClicked: () -> Unit,
    onPlusClicked: () -> Unit,
    onAddToCartClicked: () -> Unit
) {
    val pagerState: PagerState = rememberPagerState()
    val scrollState = rememberScrollState()
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .verticalScroll(state = scrollState)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            DetailsPager(
                state = pagerState,
                images = detailsUI.itemImages
            ) {}
            ItemPreviewRow(
                detailsUI = detailsUI,
                state = pagerState
            )
            ItemDescription(detailsUI = detailsUI)
            Spacer(modifier = Modifier.height(0.dp))
        }
        AddToCart(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxHeight(0.15f),
            price = calculateTotalPrice(price = detailsUI.itemPrice, quantity = quantity),
            onMinusClicked = onMinusClicked,
            onPlusClicked = onPlusClicked,
            onAddToCartClicked = onAddToCartClicked
        )
    }
}

private fun calculateTotalPrice(price: Double, quantity: Int): Double = price * quantity

@Composable
@OptIn(ExperimentalPagerApi::class)
private fun DetailsPager(
    state: PagerState,
    images: List<String>,
    onShareClick: () -> Unit
) {
    var valueFavorite by remember { mutableStateOf(false) }
    HorizontalPager(
        count = images.size,
        state = state,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        itemSpacing = 5.dp
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .offset(x = (-40).dp)
        ) {
            GlideImage(
                imageModel = { images[page] },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(width = 328.dp, height = 279.dp)
                    .clip(MaterialTheme.shapes.small),
                loading = {
                    Box(modifier = Modifier.matchParentSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                failure = {
                    Text(text = "image request failed.")
                }
            )
            Column(
                modifier = Modifier
                    .size(height = 95.dp, width = 42.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 21.dp, y = (-28).dp)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.medium
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val image = if (valueFavorite) {
                    Icons.Outlined.Favorite
                } else {
                    Icons.Outlined.FavoriteBorder
                }
                IconButton(onClick = { valueFavorite = !valueFavorite }) { //stub
                    Icon(
                        imageVector = image,
                        contentDescription = stringResource(R.string.favorite),
                        tint = DarkBlue
                    )
                }
                Divider(color = DarkBlue, thickness = 2.dp, modifier = Modifier.width(14.dp))
                IconButton(onClick = onShareClick) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = stringResource(R.string.share),
                        tint = DarkBlue
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ItemPreviewRow(
    detailsUI: DetailsUI,
    state: PagerState
) {
    val lazyListState = rememberLazyListState()

    LazyRow(
        state = lazyListState,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        items(items = detailsUI.itemImages) { item ->

            val elevation = when (state.currentPage) {
                detailsUI.itemImages.indexOf(item) -> 10.dp
                else -> 0.dp
            }
            val itemModifier =
                when (state.currentPage) {
                    detailsUI.itemImages.indexOf(item) -> Modifier
                        .size(width = 83.dp, height = 45.dp)
                    else -> Modifier
                        .size(width = 66.dp, height = 38.dp)
                        .padding(horizontal = 4.dp)
                }
            Card(elevation = elevation) {
                GlideImage(
                    imageModel = { item },
                    modifier = itemModifier.clip(MaterialTheme.shapes.medium),
                    loading = {
                        Box(modifier = Modifier.matchParentSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                    failure = {
                        Text(text = stringResource(R.string.image_request_failure))
                    }
                )
            }
        }
    }
}

@Composable
private fun ItemDescription(detailsUI: DetailsUI) {

    Column(verticalArrangement = Arrangement.Top, modifier = Modifier.padding(horizontal = 24.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = detailsUI.itemName,
                style = MaterialTheme.typography.h1.copy(fontSize = 17.sp),
                modifier = Modifier.fillMaxWidth(0.6f)
            )
            Text(
                text = "$${detailsUI.itemPrice}",
                style = MaterialTheme.typography.h1.copy(fontSize = 17.sp)
            )
        }
        Text(
            text = detailsUI.itemDescription, style = MaterialTheme.typography.h6,
            color = DarkGray,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = stringResource(R.string.rating),
                tint = BrightOrange
            )
            Text(
                text = detailsUI.itemRating.toString(),
                style = MaterialTheme.typography.h1,
                fontSize = 9.sp
            )
            Text(
                text = buildString {
                    append(" (")
                    append(detailsUI.itemCountOfReviews)
                    append(" ")
                    append(stringResource(R.string.reviews))
                    append(")")
                },
                style = MaterialTheme.typography.h6,
                color = DarkGray
            )
        }
        Text(
            text = stringResource(R.string.color),
            style = MaterialTheme.typography.h1,
            fontSize = 10.sp,
            color = DarkGray
        )
        RowOfColors(detailsUI)
    }
}

@Composable
private fun RowOfColors(detailsUI: DetailsUI) {

    var colorPick by remember { mutableStateOf(detailsUI.colors[0]) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        detailsUI.colors.forEach { itemColor ->
            val borderModifier = when (colorPick) {
                itemColor -> Modifier.border(
                    width = 2.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = MaterialTheme.shapes.medium
                )
                else -> Modifier.border(
                    width = 2.dp,
                    color = MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.medium
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                colorFilter = ColorFilter.tint(Color(itemColor.toColorInt())),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = borderModifier
                    .size(width = 34.dp, height = 26.dp)
                    .clickable { colorPick = itemColor }
                    .clip(MaterialTheme.shapes.medium)
            )
        }
    }
}

@Composable
private fun AddToCart(
    modifier: Modifier,
    price: Double = 0.0,
    onMinusClicked: () -> Unit,
    onPlusClicked: () -> Unit,
    onAddToCartClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = VeryDarkBlue,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.35f),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(R.string.quantity),
                style = MaterialTheme.typography.h6,
                color = DarkGray
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Button(
                    onClick = onMinusClicked,
                    modifier = Modifier
                        .size(width = 38.dp, height = 22.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Divider(color = Color.White, thickness = 2.dp, modifier = Modifier.width(10.dp))
                }
                Button(
                    onClick = onPlusClicked,
                    modifier = Modifier
                        .size(width = 38.dp, height = 22.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Divider(
                            color = Color.White,
                            thickness = 2.dp,
                            modifier = Modifier.width(10.dp)
                        )
                        Divider(
                            color = Color.White,
                            thickness = 2.dp,
                            modifier = Modifier
                                .width(10.dp)
                                .rotate(90f)
                        )
                    }
                }
            }
        }
        Button(
            onClick = onAddToCartClicked,
            modifier = Modifier
                .size(width = 170.dp, height = 44.dp)
                .fillMaxWidth(0.5f),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                modifier = Modifier
                    .width(width = 170.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "#$price",
                    style = MaterialTheme.typography.h6,
                    color = VeryLightBlue,
                    fontSize = 8.sp
                )
                Text(
                    text = stringResource(R.string.add_to_cart),
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    fontSize = 8.sp
                )
            }
        }
    }
}