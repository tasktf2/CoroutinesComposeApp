package com.example.coroutinescomposeapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.ChatBubbleOutline
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.PersonOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coroutinescomposeapp.screen.*
import com.example.coroutinescomposeapp.ui.theme.DarkGray
import com.example.coroutinescomposeapp.ui.theme.DarkGrayishBlue
import com.example.coroutinescomposeapp.ui.theme.LightGrayishBlue

enum class ComposeScreen {
    SignUp,
    Login,
    Details
}

enum class BottomMenu(val icon: ImageVector) {
    Main(Icons.Outlined.Home),
    Favorite(Icons.Rounded.FavoriteBorder),
    Cart(Icons.Outlined.ShoppingCart),
    Chat(Icons.Rounded.ChatBubbleOutline),
    Profile(Icons.Rounded.PersonOutline)
}

@Composable
fun ComposeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var bottomAppBarVisibility by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    bottomAppBarVisibility = when (navBackStackEntry?.destination?.route) {
        ComposeScreen.SignUp.name, ComposeScreen.Login.name -> false
        else -> true
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomAppBarVisibility,
                enter = slideInVertically { it },
                exit = ExitTransition.None
            ) {
                AppBottomNavigation(navController = navController)
            }
        }) { innerPaddings ->
        NavHost(
            navController = navController,
            startDestination = ComposeScreen.SignUp.name,
            modifier = modifier.padding(innerPaddings)
        ) {
            composable(route = ComposeScreen.SignUp.name) {
                SignUpScreen(
                    onSignUpClicked = {
                        navController.backQueue.clear()
                        navController.navigate(BottomMenu.Main.name)
                    },
                    onLoginClicked = {
                        navController.navigate(ComposeScreen.Login.name)
                    }
                )
            }
            composable(route = ComposeScreen.Login.name) {
                LoginScreen {
                    navController.backQueue.clear()
                    navController.navigate(BottomMenu.Main.name)
                }
            }
            composable(route = BottomMenu.Main.name) {
                MainScreen {
                    navController.navigate(ComposeScreen.Details.name)
                    //here should be put id of product but API is being mocked
                }
            }
            composable(route = ComposeScreen.Details.name) {
                DetailsScreen { navController.popBackStack() }
            }
            composable(route = BottomMenu.Profile.name) {
                ProfileScreen(onBackClicked = { navController.popBackStack() },
                    onLogOutClicked = {
                        navController.backQueue.clear()
                        navController.navigate(ComposeScreen.SignUp.name)
                    })
            }
            composable(route = BottomMenu.Favorite.name) {
                SimpleScreen(screenName = BottomMenu.Favorite.name)
            }
            composable(route = BottomMenu.Cart.name) {
                SimpleScreen(screenName = BottomMenu.Cart.name)
            }
            composable(route = BottomMenu.Chat.name) {
                SimpleScreen(screenName = BottomMenu.Chat.name)
            }
        }
    }
}

@Composable
fun AppBottomNavigation(navController: NavHostController) {
    val menuItems = listOf(
        BottomMenu.Main,
        BottomMenu.Favorite,
        BottomMenu.Cart,
        BottomMenu.Chat,
        BottomMenu.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: BottomMenu.Main.name
    BottomNavigation(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp
                )
            ), //smh it doesn`t round
        backgroundColor = MaterialTheme.colors.background
    ) {
        menuItems.forEach { menuItem ->
            BottomNavigationItem(
                icon = { Icon(imageVector = menuItem.icon, contentDescription = menuItem.name) },
                selected = currentRoute == menuItem.name,
                onClick = { navController.navigate(menuItem.name) },
                selectedContentColor = DarkGrayishBlue,
                unselectedContentColor = DarkGray,
                modifier = if (currentRoute == menuItem.name) {
                    Modifier.background(
                        color = LightGrayishBlue,
                        shape = MaterialTheme.shapes.large
                    )
                } else {
                    Modifier
                }
            )
        }
    }
}