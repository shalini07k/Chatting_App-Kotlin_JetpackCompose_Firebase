package com.example.chatting_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatting_app.presentation.callscreen.CallScreen
import com.example.chatting_app.presentation.communitiesscreen.CommunitiesScreen
import com.example.chatting_app.presentation.homescreen.HomeScreen
import com.example.chatting_app.presentation.profile.UserProfileScreen
import com.example.chatting_app.presentation.splashscreen.SplashScreen
import com.example.chatting_app.presentation.updatescreen.UpdateScreen
import com.example.chatting_app.presentation.userregistrationscreen.UserRegistrationScreen
import com.example.chatting_app.presentation.viewmodel.BaseViewModel
import com.example.chatting_app.presentation.welcomescreen.WelcomeScreen


@Composable
fun WhatsAppNavigation(){
    val navController = rememberNavController()

    NavHost(startDestination = Routes.SplashScreen, navController= navController){
        composable <Routes.SplashScreen>{
            SplashScreen(navController)
        }
        composable <Routes.WelcomeScreen>{
            WelcomeScreen(navController)
        }
        composable <Routes.UserregistrationScreen>{
            UserRegistrationScreen(navController)
        }
        composable <Routes.HomeScreen>{
            val baseViewModel: BaseViewModel = hiltViewModel()
            HomeScreen(navController,baseViewModel)
        }
        composable <Routes.UpdateScreen>{
            UpdateScreen(navController)
        }
        composable <Routes.CommunitiesScreen>{
            CommunitiesScreen(navController)
        }
        composable <Routes.CallScreen>{
            CallScreen(navController)
        }
        composable <Routes.UserProfileScreen>{
           UserProfileScreen(navController)
        }

    }
}