package com.example.chatting_app.presentation.splashscreen


import android.util.Log.d
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.chatting_app.R
import com.example.chatting_app.presentation.navigation.Routes
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navHostController: NavHostController){

    LaunchedEffect(Unit) {
        delay(6000)
        navHostController.navigate(Routes.WelcomeScreen){
            popUpTo<Routes.SplashScreen>{inclusive = true}
        }

    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id=R.drawable.logo),contentDescription = null,
            modifier = Modifier.size(80.dp).align(Alignment.Center)
        )
        Column(modifier = Modifier.align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text="From", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier){
                Icon(painter = painterResource(id=R.drawable.meta), contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = colorResource(id=R.color.light_green)
                )
                Spacer(modifier = Modifier.width(12.dp))

                Text(text = "Meta", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}