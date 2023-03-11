package com.example.coroutinescomposeapp.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coroutinescomposeapp.R
import com.example.coroutinescomposeapp.ui.theme.CoroutinesComposeAppTheme
import com.example.coroutinescomposeapp.ui.theme.DarkGray

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    CoroutinesComposeAppTheme {
        ProfileScreen({}, {})
    }
}

@Composable
fun ProfileScreen(onBackClicked: () -> Unit, onLogOutClicked: () -> Unit) {
    val scaffoldState =
        rememberScaffoldState(drawerState = rememberDrawerState(initialValue = DrawerValue.Closed))
    CoroutinesComposeAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxHeight(),
            topBar = {
                ProfileTopAppBar(onBackClicked = onBackClicked)
            }) { paddingValues ->
            ProfileBody(
                modifier = Modifier.padding(paddingValues),
                onLogOutClicked = onLogOutClicked
            )
        }
    }
}

@Composable
private fun ProfileTopAppBar(onBackClicked: () -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.White
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = onBackClicked, modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIos,
                    contentDescription = stringResource(id = R.string.back),
                    tint = Color.Black
                )
            }
            Text(
                text = stringResource(R.string.profile),
                style = MaterialTheme.typography.h2,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun ProfileAvatar(@DrawableRes avatarRes: Int, modifier: Modifier, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = avatarRes),
            contentDescription = stringResource(id = R.string.profile_avatar),
            modifier = Modifier
                .size(60.dp)
                .clip(MaterialTheme.shapes.large),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.change_photo),
            style = MaterialTheme.typography.h6,
            color = DarkGray
        )
    }
}

@Composable
private fun ProfileBody(modifier: Modifier, onLogOutClicked: () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ProfileAvatar(
            avatarRes = R.drawable.ic_launcher_background,
            modifier = Modifier.weight(0.22f)
        ) {}
        Text(
            text = "SetJy",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.weight(0.08f)
        )
        UploadItemButton(modifier = Modifier.weight(0.1f)) {}
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.weight(0.6f)
        ) {
            ProfileTextButton(text = stringResource(R.string.trade_store)) {}
            ProfileTextButton(text = stringResource(R.string.payment_method)) {}
            ProfileTextButton(text = stringResource(R.string.balance), cashCount = 1538) {}
            ProfileTextButton(
                text = stringResource(R.string.restore_purchase),
                imageVector = Icons.Rounded.Cached,
                isArrowVisible = false
            ) {}
            ProfileTextButton(
                text = stringResource(R.string.help),
                imageVector = Icons.Rounded.HelpOutline,
                isArrowVisible = false
            ) {}
            ProfileTextButton(
                text = stringResource(R.string.log_out),
                imageVector = Icons.Outlined.Logout,
                isArrowVisible = false,
                onClick = onLogOutClicked
            )
        }
    }
}

@Composable
private fun UploadItemButton(modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(bottom = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = Icons.Rounded.Upload,
                contentDescription = stringResource(R.string.upload_item),
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.fillMaxWidth(0.2f),
                alignment = Alignment.CenterStart
            )
            Text(
                text = stringResource(R.string.upload_item),
                modifier = Modifier.fillMaxWidth(0.6f),
                style = MaterialTheme.typography.button
            )
        }
    }
}

@Composable
private fun ProfileTextButton(
    text: String,
    imageVector: ImageVector = Icons.Outlined.CreditCard,
    isArrowVisible: Boolean = true,
    cashCount: Int? = null,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                imageVector = Icons.Filled.Circle,
                contentDescription = null,
                alpha = 0.2f,
                modifier = Modifier.size(40.dp)
            )
            Image(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.2f)
            )
        }
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(0.5f),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Start
        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            when {
                cashCount != null -> {
                    Text(
                        text = "$$cashCount",
                        modifier = Modifier.fillMaxWidth(0.7f),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.body1
                    )
                }
                isArrowVisible -> {
                    Image(
                        imageVector = Icons.Rounded.ArrowForwardIos,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(0.2f),
                        alignment = Alignment.CenterEnd
                    )
                }
            }
        }
    }
}