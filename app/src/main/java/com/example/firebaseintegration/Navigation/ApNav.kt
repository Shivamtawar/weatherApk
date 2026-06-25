package com.example.firebaseintegration.Navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseintegration.screens.HomeScreen
import com.example.firebaseintegration.screens.LoginPage
import com.example.firebaseintegration.screens.SignUp
import com.example.firebaseintegration.screens.TestScreen

@Composable
fun AppNav() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {


        composable("signup") {
            SignUp(navController)
        }
        composable("login") {
            LoginPage(navController)
        }
        composable("home"){
            TestScreen()
        }
    }
    }