package com.example.coroutinescomposeapp.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coroutinescomposeapp.R
import com.example.coroutinescomposeapp.di.TempData
import com.example.coroutinescomposeapp.ui.model.CardType
import com.example.coroutinescomposeapp.ui.model.CardUI
import com.example.coroutinescomposeapp.ui.model.CategoryUI
import com.example.coroutinescomposeapp.ui.theme.*
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch


@Preview
@Composable
private fun MainPreview() {
    CoroutinesComposeAppTheme {
//        MainSuccessScreen({}, listOf(), listOf(), Modifier)
    }
}

@Composable
fun MainScreen(
    uiState: MainState,
    userPassword: String?,
    modifier: Modifier,
    onCardClicked: () -> Unit
) {
    when (uiState) {
        is MainState.Loading -> LoadingScreen()
        is MainState.Success -> MainSuccessScreen(
            onCardClicked = onCardClicked,
            latestList = uiState.latestProducts,
            flashList = uiState.flashSaleProducts,
            userPassword = userPassword,
            modifier = modifier
        )
        is MainState.Error -> ErrorScreen()
    }
}

@Composable
fun MainSuccessScreen(
    onCardClicked: () -> Unit,
    latestList: List<CardUI>,
    flashList: List<CardUI>,
    userPassword: String?,
    modifier: Modifier
) {
    val scaffoldState =
        rememberScaffoldState(drawerState = rememberDrawerState(initialValue = DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    var valueSearch by remember { mutableStateOf("") }
    var isPasswordShown by remember { mutableStateOf(false) }

    if (!isPasswordShown) {
        userPassword?.let {
            LaunchedEffect(Unit) {
                snackBarHostState.showSnackbar("Your password is $userPassword")
                isPasswordShown = true
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        drawerContent = { MainDrawer() },
        topBar = {
            MainTopAppBar(
                onMenuClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                username = "setJy",
                avatarRes = R.drawable.ic_launcher_background,
                onAvatarClicked = {})
        },
        snackbarHost = {
            SnackBarShowPassword(snackBarHostState)
        }) { paddingValues ->
        MainBody(
            modifier = Modifier.padding(paddingValues),
            valueSearch = valueSearch,
            onValueChanged = { valueSearch = it },
            onCardClicked = onCardClicked,
            latest = latestList,
            flash = flashList
        )
    }
}

@Composable
private fun SnackBarShowPassword(snackBarHostState: SnackbarHostState) {
    SnackbarHost(
        hostState = snackBarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    TextButton(onClick = { snackBarHostState.currentSnackbarData?.dismiss() }) {
                        Text(stringResource(R.string.dismiss))
                    }
                }
            ) {
                Text(data.message)
            }
        }
    )
}

@Composable
private fun MainTopAppBar(
    username: String,
    @DrawableRes avatarRes: Int,
    onMenuClicked: () -> Unit,
    onAvatarClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MainTitle(username)
                MainAvatar(onAvatarClicked, avatarRes)
            }
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            IconButton(onClick = onMenuClicked) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        }
    )
}

@Composable
private fun MainTitle(username: String) {
    Row {
        Text(
            text = buildString {
                append(stringResource(R.string.trade_by))
                append(" ")
            },
            style = MaterialTheme.typography.h2,
            fontSize = 20.sp,
        )
        Text(
            text = username,
            style = MaterialTheme.typography.h2,
            fontSize = 20.sp,
            color = ModerateBlue
        )
    }
}

@Composable
private fun MainAvatar(onAvatarClicked: () -> Unit, @DrawableRes avatarRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onAvatarClicked)
    ) {
        Image(
            painter = painterResource(id = avatarRes),
            contentDescription = stringResource(R.string.profile_avatar),
            modifier = Modifier
                .size(30.dp)
                .clip(MaterialTheme.shapes.large),
            contentScale = ContentScale.Crop
        )
        Row {
            Text(
                text = stringResource(R.string.location),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primaryVariant
            )
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .height(height = MaterialTheme.typography.h6.fontSize.value.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
private fun MainDrawer() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
    }
}

@Composable
private fun MainBody(
    modifier: Modifier,
    valueSearch: String,
    onValueChanged: (String) -> Unit,
    onCardClicked: () -> Unit,
    latest: List<CardUI>,
    flash: List<CardUI>
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(state = scrollState)
    ) {
        CustomTextField(
            value = valueSearch,
            onValueChange = onValueChanged,
            modifier = Modifier
                .height(24.dp)
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.large
                ),
            placeholder = stringResource(R.string.search_placeholder),
        )
        RoundCategories(TempData.listOfIcons)
        CardGroup(
            cardType = CardType.LATEST,
            onViewAllClick = {},
            onCardClicked = onCardClicked,
            cardItems = latest
        )
        CardGroup(
            cardType = CardType.FLASH_SALE,
            onViewAllClick = {},
            onCardClicked = onCardClicked,
            cardItems = flash
        )
        CardGroup(
            cardType = CardType.BRANDS,
            onViewAllClick = {},
            onCardClicked = onCardClicked,
            cardItems = latest
        )
    }
}

@Composable
private fun RoundCategories(listOfIcons: List<CategoryUI>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        listOfIcons.forEach { item ->
            Category(imageVector = item.imageVector, description = item.description)
        }
    }
}

@Composable
private fun Category(imageVector: ImageVector, description: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Default.Circle,
                contentDescription = null,
                tint = LightGrayishBlue,
                modifier = Modifier
                    .size(38.dp)
                    .scale(scaleX = 1.1f, scaleY = 1f)
            )
            Icon(imageVector = imageVector, contentDescription = null)
        }
        Text(
            text = description,
            style = MaterialTheme.typography.h6.copy(color = DarkGray, fontSize = 8.sp)
        )
    }
}

@Composable
private fun CardGroup(
    cardType: CardType,
    onViewAllClick: () -> Unit,
    onCardClicked: () -> Unit,
    cardItems: List<CardUI>
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Text(text = cardType.groupName, style = MaterialTheme.typography.h2)
            Text(
                text = stringResource(R.string.view_all),
                style = MaterialTheme.typography.h6.copy(color = DarkGray),
                modifier = Modifier.clickable(onClick = onViewAllClick)
            )
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(
                items = cardItems
            ) { item ->
                CardItem(cardType = cardType, cardItem = item, onCardClicked = onCardClicked)
            }
        }
    }
}

@Composable
private fun CardItem(cardType: CardType, cardItem: CardUI, onCardClicked: () -> Unit) {

    val textModifier = Modifier
        .widthIn(max = cardType.width / 1.5f)
        .padding(start = cardType.startPadding)

    Box(
        modifier = Modifier.clip(MaterialTheme.shapes.small)
    ) {
        GlideImage(
            imageModel = { cardItem.itemImageUrl },
            modifier = Modifier
                .clickable(onClick = onCardClicked)
                .size(width = cardType.width, height = cardType.height)
                .clip(MaterialTheme.shapes.small),
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            failure = {
                Text(text = stringResource(id = R.string.image_request_failure))
            })
        CardShadow(cardType = cardType, modifier = Modifier.align(Alignment.BottomCenter))
        CardCategory(
            modifier = textModifier.align(Alignment.TopStart),
            cardType = cardType,
            cardItem = cardItem
        )
        CardName(
            cardItem = cardItem,
            cardType = cardType,
            modifier = textModifier.align(Alignment.TopStart)
        )
        CardPrice(
            cardItem = cardItem,
            cardType = cardType,
            modifier = textModifier.align(Alignment.BottomStart)
        )
        CardButtons(cardType = cardType, modifier = Modifier.align(Alignment.BottomEnd))
        CardDiscount(
            cardType = cardType,
            cardItem = cardItem,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Composable
private fun CardShadow(cardType: CardType, modifier: Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            colorFilter = ColorFilter.tint(BlackTransparent),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(
                    width = cardType.width,
                    height = cardType.height - cardType.itemNameTopPadding
                )
        )
    }
}

@Composable
private fun CardCategory(
    modifier: Modifier,
    cardType: CardType,
    cardItem: CardUI
) {
    Box(
        modifier = modifier
            .offset(y = cardType.categoryTopPadding)
            .background(
                color = SilverSand,
                shape = MaterialTheme.shapes.large
            )
    ) {
        Text(
            text = cardItem.itemCategory,
            style = MaterialTheme.typography.h6.copy(fontSize = cardType.categoryFontSize.sp),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    horizontal = cardType.categoryPaddingHorizontal,
                    vertical = cardType.categoryPaddingVertical
                )
        )
    }
}

@Composable
private fun CardName(
    cardItem: CardUI,
    cardType: CardType,
    modifier: Modifier
) {
    Text(
        text = cardItem.itemName,
        style = MaterialTheme.typography.body2,
        fontSize = cardType.nameFontSize.sp,
        color = Color.White,
        modifier = modifier.offset(y = cardType.itemNameTopPadding)
    )
}

@Composable
private fun CardPrice(
    cardItem: CardUI,
    cardType: CardType,
    modifier: Modifier
) {
    Text(
        text = buildString {
            append("$ ")
            append(cardItem.itemPrice)
        },
        style = MaterialTheme.typography.body2.copy(fontSize = 10.sp),
        fontSize = cardType.priceFontSize.sp,
        color = Color.White,
        modifier = modifier.offset(y = cardType.priceBottomPadding)
    )
}

@Composable
private fun CardButtons(cardType: CardType, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        when (cardType) {
            CardType.FLASH_SALE -> {
                CardFavoriteButton {}
                CardAddButton(modifier = Modifier) {}
            }
            else -> CardAddButton(modifier = Modifier.scale(0.57f)) {}
        }
    }
}

@Composable
private fun CardDiscount(
    cardType: CardType,
    cardItem: CardUI,
    modifier: Modifier
) {
    if (cardType == CardType.FLASH_SALE) {
        Box(
            modifier = modifier
                .size(width = 56.dp, height = 24.dp)
                .padding(top = 7.dp, end = 8.dp)
                .background(color = BrightRed, shape = MaterialTheme.shapes.large)

        ) {
            Text(
                text = buildString {
                    append(cardItem.itemDiscount)
                    append(stringResource(R.string.discount))
                },
                style = MaterialTheme.typography.h1,
                fontSize = 10.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
            )

        }

    }
}

@Composable
private fun CardFavoriteButton(onClick: () -> Unit) {
    var valueFavorite by remember { mutableStateOf(false) }
    val image = if (valueFavorite) {
        Icons.Outlined.Favorite
    } else {
        Icons.Outlined.FavoriteBorder
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.clickable { valueFavorite = !valueFavorite }) {
        Icon(
            imageVector = Icons.Default.Circle,
            contentDescription = null,
            tint = LightGrayishBlue,
            modifier = Modifier.size(28.dp)
        )
        Icon(
            imageVector = image,
            contentDescription = stringResource(id = R.string.favorite),
            tint = DarkBlue,
            modifier = Modifier.size(12.dp)
        )
    }
}

@Composable
private fun CardAddButton(modifier: Modifier, onAddClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.clickable(onClick = onAddClick)
    ) {
        Icon(
            imageVector = Icons.Default.Circle,
            contentDescription = null,
            tint = LightGrayishBlue, modifier = Modifier.size(35.dp)
        )
        Divider(
            color = DarkBlue,
            thickness = 1.dp,
            modifier = Modifier.width(12.dp)
        )
        Divider(
            color = DarkBlue,
            thickness = 1.dp,
            modifier = Modifier
                .width(12.dp)
                .rotate(90f)
        )
    }
}