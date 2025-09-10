package com.example.chatting_app.presentation.navigation

import kotlinx.serialization.Serializable


sealed class Routes {
    @Serializable
    object SplashScreen : Routes()

    @Serializable
    object WelcomeScreen : Routes()

    @Serializable
    object UserregistrationScreen : Routes()

    @Serializable
    object HomeScreen : Routes()

    @Serializable
    object UpdateScreen : Routes()

    @Serializable
    object CommunitiesScreen : Routes()

    @Serializable
    object CallScreen : Routes()

    @Serializable
    object UserProfileScreen : Routes()

    @Serializable
    object SettingScreen: Routes()

    @Serializable
    object ChatScreen: Routes(){
        const val route="chat_screen/{phoneNumber}"
        fun createRoute(phoneNumber:String)= "chat_screen/$phoneNumber"
    }
}
//@Serializable
//sealed class Routes {
//    object SplashScreen : Routes()
//    object WelcomeScreen : Routes()
//    object UserregistrationScreen : Routes()
//    object HomeScreen : Routes()
//    object UpdateScreen : Routes()
//    object CommunitiesScreen : Routes()
//    object CallScreen : Routes()
//}
