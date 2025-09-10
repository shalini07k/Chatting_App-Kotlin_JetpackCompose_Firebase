package com.example.chatting_app.presentation.updatescreen

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.chatting_app.R
import com.example.chatting_app.presentation.bottomnavigation.BottomNavigation
import com.example.chatting_app.presentation.navigation.Routes

@Composable


fun UpdateScreen(navHostController: NavHostController){
    val scrollState = rememberScrollState()
    val samplestatus =listOf(
        Statusdata(image = R.drawable.man, name ="Rahul", time = "10 min ago"),
        Statusdata(image = R.drawable.man, name ="Ravi", time = "Just now"),
        Statusdata(image = R.drawable.man, name ="Rama", time = "17 min ago"),
//        Statusdata(image = R.drawable.woman, name ="Shalu", time = "20 min ago"),
//        Statusdata(image = R.drawable.woman, name ="Priya", time = "30 min ago"),
//        Statusdata(image = R.drawable.man, name ="Rajiv", time = "1 hr ago"),
//        Statusdata(image = R.drawable.woman, name ="Shalini", time = "5 hrs ago"),
//        Statusdata(image = R.drawable.woman, name ="Pranshi", time = "15 hrs ago")
    )
    val samplechannel= listOf(
        Channels(image= R.drawable.neat_roots, name= "Neat Roots", description = "Latest news in tech"),
        Channels(image= R.drawable.neat_roots, name= "Neat Roots", description = "Latest news in tech"),
        Channels(image= R.drawable.neat_roots, name= "Neat Roots", description = "Latest news in tech")
    )
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = colorResource(R.color.light_green),
                modifier = Modifier.size(65.dp),
                contentColor = Color.White
            ) {
                Icon(painter = painterResource(id = R.drawable.camera),
                    contentDescription = null, modifier = Modifier.size(30.dp))
            }
        },

        bottomBar = {
            BottomNavigation(navHostController, selectedItem = 0, onClick = {
                    index->
                when(index){
                    0->{navHostController.navigate(Routes.HomeScreen)}
                    1->{navHostController.navigate(Routes.UpdateScreen)}
                    2->{navHostController.navigate(Routes.CommunitiesScreen)}
                    3->{navHostController.navigate(Routes.CallScreen)}
                }
            })
        },
        topBar = {
            Topbar()
        }
    ) {
        Column(modifier = Modifier.padding(it).fillMaxSize().verticalScroll(scrollState)) {
            Text(text = "Status", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp), color = Color.Black)

            MyStatus()

            samplestatus.forEach {
                Statusitem(statusdata= it)
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider( color = Color.Gray)

            Text(text = "Channels",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text( text =" Stay updated on topics that matter to you. Find channels to follow bellow.")

                Spacer(modifier = Modifier.height(32.dp))

                Text(text = "Find channel to follow")
            }

            Spacer(modifier =Modifier.height(16.dp))

            samplechannel.forEach {
                ChannelitemsDesign(channels = it)
            }

        }
    }
}
