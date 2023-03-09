package com.example.coroutinescomposeapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coroutinescomposeapp.screen.DetailsScreen
import com.example.coroutinescomposeapp.screen.LoginScreen
import com.example.coroutinescomposeapp.screen.MainScreen
import com.example.coroutinescomposeapp.screen.SignUpScreen

enum class ComposeScreen {
    SignUp,
    Login,
    Main,
    Details
}

@Composable
fun ComposeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPaddings ->

        NavHost(
            navController = navController,
            startDestination = ComposeScreen.SignUp.name,
            modifier = modifier.padding(innerPaddings)
        ) {
            composable(route = ComposeScreen.SignUp.name) {
                SignUpScreen(
                    onSignUpClicked = {
                        navController.backQueue.clear()
                        navController.navigate(ComposeScreen.Main.name)
                    },
                    onLoginClicked = {
                        navController.navigate(ComposeScreen.Login.name)
                    }
                )
            }
            composable(route = ComposeScreen.Login.name) {
                LoginScreen {
                    navController.backQueue.clear()
                    navController.navigate(ComposeScreen.Main.name)
                }
            }
            composable(route = ComposeScreen.Main.name) {
                MainScreen {
                    navController.navigate(ComposeScreen.Details.name)
                    //here should be put id of product but API is being mocked
                }
            }
            composable(route = ComposeScreen.Details.name) {
                DetailsScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}
