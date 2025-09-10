package com.example.chatting_app.presentation.callscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
@Preview(showBackground = true, showSystemUi = true)
fun CallScreen(navHostController: NavHostController) {

    var searching by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    var showmenu by remember { mutableStateOf(false) }
    val callinfo = listOf(
        Callrecords(image= R.drawable.salman_khan, name= "Salman Khan", time= "Today, 8:00 pm", missed=true),
        Callrecords(image= R.drawable.akshay_kumar, name= "Akshay Kumar", time= "Yesterday, 8:00 pm", missed=false),
        Callrecords(image= R.drawable.sharukhkhan, name= "Sharuk khan", time= "Yesterday, 8:00 pm", missed=true),
        Callrecords(image= R.drawable.sharadhakapoor, name= "Sharadha Kapoor", time= "Yesterday, 8:00 pm", missed=true),
        Callrecords(image= R.drawable.ajay_devgn, name= "Ajay Devgan", time= "2 Aug 2025, 8:00 pm", missed=false),
        Callrecords(image= R.drawable.salman_khan, name= "Salman Khan", time= "6 Aug 2025, 8:00 pm", missed=false)
    )

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
            ) {
                Column {
                    Row {
                        if (searching) {
                            TextField(
                                value = search,
                                onValueChange = { search = it },
                                placeholder = { Text(text = "Search") },
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                modifier = Modifier.padding(start = 12.dp), singleLine = true
                            )
                        } else {
                            Text(
                                text = "Calls",
                                fontSize = 28.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        if (searching) {
                            IconButton(onClick = { searching = false }) {
                                Icon(
                                    painter = painterResource(R.drawable.cross),
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        } else {
                            IconButton(onClick = { searching = true }) {
                                Icon(
                                    painter = painterResource(R.drawable.search),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            IconButton(
                                onClick = { showmenu = true },
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.more),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                DropdownMenu(
                                    expanded = showmenu,
                                    onDismissRequest = { showmenu = false }) {
                                    DropdownMenuItem(
                                        text = { Text(text = "Settings") },
                                        onClick = { showmenu = false })
                                }
                            }
                        }

                    }
                    HorizontalDivider()
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {},
                containerColor = colorResource(R.color.light_green),
                modifier = Modifier.size(65.dp),
                contentColor = Color.White){// here the content is the small message box inside the floating action button

                Icon(painter= painterResource(id=R.drawable.add_call),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
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
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

            FavSection()

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.light_green)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Start a new call",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Recent Calls",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyColumn {
                items(callinfo){ data->
                    Calllayout(data)
                }
            }
        }

    }
}