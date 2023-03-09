package com.example.coroutinescomposeapp.screen

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coroutinescomposeapp.R
import com.example.coroutinescomposeapp.model.CategoryUI
import com.example.coroutinescomposeapp.screen.base.CardType
import com.example.coroutinescomposeapp.screen.base.TempData
import com.example.coroutinescomposeapp.ui.theme.*
import kotlinx.coroutines.launch


@Preview
@Composable
fun MainPreview() {
    CoroutinesComposeAppTheme {
        MainScaffold()
    }
}

@Composable
fun MainScaffold() {
    val scaffoldState =
        rememberScaffoldState(drawerState = rememberDrawerState(initialValue = DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()

    var valueSearch by remember { mutableStateOf("") }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
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
        }) { paddingValues ->
        MainBody(
            paddingValues = paddingValues,
            valueSearch = valueSearch,
            onValueChanged = { valueSearch = it })
    }
}

@Composable
fun MainTopAppBar(
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
                    contentDescription = "Menu"
                )
            }
        }
    )
}

@Composable
private fun MainTitle(username: String) {
    Row {
        Text(
            text = "trade by ",
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
            contentDescription = "Profile avatar",
            modifier = Modifier
                .size(30.dp)
                .clip(MaterialTheme.shapes.large),
            contentScale = ContentScale.Crop
        )
        Row {
            Text(
                text = "Location",
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
fun MainDrawer() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
    }
}

@Composable
fun MainBody(paddingValues: PaddingValues, valueSearch: String, onValueChanged: (String) -> Unit) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
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
            placeholder = "What are you looking for?",
        )
        RoundCategories(TempData.listOfIcons)
        CardGroup(cardType = CardType.LATEST) {}
        CardGroup(cardType = CardType.FLASH_SALE) {}
        CardGroup(cardType = CardType.BRANDS) {}
    }
}

@Composable
fun RoundCategories(listOfIcons: List<CategoryUI>) {
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
fun Category(imageVector: ImageVector, description: String) {
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
fun CardGroup(cardType: CardType, onViewAllClick: () -> Unit) {
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
                text = "View all",
                style = MaterialTheme.typography.h6.copy(color = DarkGray),
                modifier = Modifier.clickable(onClick = onViewAllClick)
            )
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(
                items = listOf(cardType, cardType, cardType, cardType, cardType, cardType)
            ) {
                CardItem(cardType = it)
            }
        }
    }
}

@Composable
fun CardItem(cardType: CardType) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "description",
            modifier = Modifier
                .size(width = cardType.width, height = cardType.height)
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop
        )
        CardText(cardType, Modifier.align(Alignment.BottomStart))
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
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
}

@Composable
private fun CardText(cardType: CardType, modifier: Modifier) {
    Column(
        modifier = modifier
            .widthIn(max = cardType.width / 1.5f)
    ) {
        Text(
            text = "Headphones",
            style = MaterialTheme.typography.h6.copy(fontSize = cardType.categoryFontSize.sp),
            modifier = Modifier
                .background(
                    color = SilverSand,
                    shape = MaterialTheme.shapes.large
                )
                .padding(
                    horizontal = cardType.categoryPaddingHorizontal,
                    vertical = cardType.categoryPaddingVertical
                )
        )
        Text(
            text = "Item Name",
            style = MaterialTheme.typography.body2,
            fontSize = cardType.nameFontSize.sp,
            color = Color.White
        )
        Text(
            text = "$ Item Price",
            style = MaterialTheme.typography.body2.copy(fontSize = 10.sp),
            fontSize = cardType.priceFontSize.sp,
            color = Color.White
        )
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
            contentDescription = "Favorite",
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