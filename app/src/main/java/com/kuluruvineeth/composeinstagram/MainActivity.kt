package com.kuluruvineeth.composeinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kuluruvineeth.composeinstagram.presentation.Authentication.AuthenticationViewModel
import com.kuluruvineeth.composeinstagram.presentation.Authentication.LoginScreen
import com.kuluruvineeth.composeinstagram.presentation.Authentication.SignUpScreen
import com.kuluruvineeth.composeinstagram.presentation.Main.FeedScreen
import com.kuluruvineeth.composeinstagram.presentation.Main.ProfileScreen
import com.kuluruvineeth.composeinstagram.presentation.Main.SearchScreen
import com.kuluruvineeth.composeinstagram.presentation.SplashScreen
import com.kuluruvineeth.composeinstagram.ui.theme.ComposeinstagramTheme
import com.kuluruvineeth.composeinstagram.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeinstagramTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel : AuthenticationViewModel = hiltViewModel()
                    ComposeInstagramApp(
                        navController = navController,
                        authenticationViewModel = authViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeInstagramApp(
    navController: NavHostController,
    authenticationViewModel: AuthenticationViewModel
){
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route){
        composable(route=Screens.LoginScreen.route){
            LoginScreen(
                navController = navController,
                viewModel = authenticationViewModel
            )
        }
        composable(route=Screens.SignUpScreen.route){
            SignUpScreen(
                navController = navController,
                viewModel = authenticationViewModel
            )
        }
        composable(route=Screens.SplashScreen.route){
            SplashScreen(
                navController = navController,
                authViewModel = authenticationViewModel
            )
        }
        composable(route=Screens.FeedScreen.route){
            FeedScreen(
                navController = navController
            )
        }
        composable(route=Screens.SearchScreen.route){
            SearchScreen(
                navController = navController
            )
        }
        composable(route=Screens.ProfileScreen.route){
            ProfileScreen(
                navController = navController
            )
        }
    }
}
