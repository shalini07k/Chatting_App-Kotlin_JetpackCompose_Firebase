package com.example.chatting_app.presentation.homescreen

import android.R.attr.phoneNumber
import android.R.attr.text
import android.R.id.home
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
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
import com.example.chatting_app.presentation.viewmodel.BaseViewModel
import com.example.chatting_app.presentation.viewmodel.PhoneAuthViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navHostController: NavHostController, homeBaseViewModel: BaseViewModel) {

    var showPopup by remember { mutableStateOf(false) }
    val chatData by homeBaseViewModel.chatList.collectAsState()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    if (userId != null) {
        LaunchedEffect(Unit) {
            homeBaseViewModel.getChatForUser(userId) { chats ->

            }
        }
    }
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showPopup= true
                },
                containerColor = colorResource(id = R.color.light_green),
                contentColor = Color.White,
                modifier = Modifier.size(65.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_chat_icon),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = Color.White
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
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(color = Color.White)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                var isSearching by remember { mutableStateOf(false) }
                var searchText by remember { mutableStateOf("") }
                var showMenu by remember { mutableStateOf(false) }

                if (isSearching) {
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = {
                            Text("Search", color = Color.DarkGray)
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .align(Alignment.CenterStart)
                            .padding(start = 12.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                } else {
                    Text(
                        "WhatsApp",
                        fontSize = 28.sp,
                        color = colorResource(R.color.light_green),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 12.dp),
                        fontWeight = FontWeight.Bold
                    )

                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {

                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.camera),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        if (isSearching) {
                            IconButton(onClick = {
                                isSearching = false
                                searchText = ""
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.cross),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                        } else {
                            IconButton(onClick = {
                                isSearching = true
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.search),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                        }
                        IconButton(onClick = {
                            showMenu = !showMenu
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.more),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false },
                                modifier = Modifier.background(color = Color.White)
                            ) {
                                DropdownMenuItem(
                                    text = { Text(text = "New Group") },
                                    onClick = { showMenu = false }
                                )
                                DropdownMenuItem(
                                    text = { Text(text = "New Broadcast") },
                                    onClick = { showMenu = false }
                                )
                                DropdownMenuItem(
                                    text = { Text(text = "Linked Device") },
                                    onClick = { showMenu = false }
                                )
                                DropdownMenuItem(
                                    text = { Text(text = "Stared Messages") },
                                    onClick = { showMenu = false }
                                )
                                DropdownMenuItem(
                                    text = { Text(text = "Setting") },
                                    onClick = {
                                        showMenu = false
                                        navHostController.navigate(Routes.SettingScreen)
                                    }
                                )
                            }
                        }


                    }

                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))

            if (showPopup) {
                AddUserPopUp(
                    onDismiss = { showPopup = false }, onUserAdd = { newUser ->
                    homeBaseViewModel.addChat(newUser)
                },
                    baseViewModel = homeBaseViewModel
                )
            }
            LazyColumn {
                items(chatData) { chat ->
                    ChatDesign(chatBox = chat, onClick = {
                        navHostController.navigate(
                            Routes.ChatScreen.createRoute(
                                phoneNumber = chat.phoneNumber ?: "Ok"
                            )
                        )
                    }, baseViewModel = homeBaseViewModel)

                }
            }
        }
    }


//    val chatData= listOf(
//        ChatBox(
//            R.drawable.salman_khan,
//            name = "Salman khan",
//            time = "10:00 AM",
//            message = "hello"
//        ),
//        ChatBox(
//            image = R.drawable.akshay_kumar,
//            name="Akshay Kumar",
//            time = "12:00 PM",
//            message = "hi"
//        ),
//        ChatBox(
//            R.drawable.salman_khan,
//            name = "Salman khan",
//            time = "10:00 AM",
//            message = "hello"
//        ),
//        ChatBox(
//            image = R.drawable.akshay_kumar,
//            name="Akshay Kumar",
//            time = "12:00 PM",
//            message = "hi"
//        ),
//        ChatBox(
//            R.drawable.salman_khan,
//            name = "Salman khan",
//            time = "10:00 AM",
//            message = "hello"
//        ),
//        ChatBox(
//            image = R.drawable.akshay_kumar,
//            name="Akshay Kumar",
//            time = "12:00 PM",
//            message = "hi"
//        ),
//        ChatBox(
//            R.drawable.salman_khan,
//            name = "Salman khan",
//            time = "10:00 AM",
//            message = "hello"
//        ),
//        ChatBox(
//            image = R.drawable.akshay_kumar,
//            name="Akshay Kumar",
//            time = "12:00 PM",
//            message = "hi"
//        ),
//        ChatBox(
//            R.drawable.salman_khan,
//            name = "Salman khan",
//            time = "10:00 AM",
//            message = "hello"
//        ),
//        ChatBox(
//            image = R.drawable.akshay_kumar,
//            name="Akshay Kumar",
//            time = "12:00 PM",
//            message = "hi"
//        ),
//        ChatBox(
//            R.drawable.salman_khan,
//            name = "Salman khan",
//            time = "10:00 AM",
//            message = "hello"
//        ),
//        ChatBox(
//            image = R.drawable.akshay_kumar,
//            name="Akshay Kumar",
//            time = "12:00 PM",
//            message = "hi"
//        ),
//        ChatBox(
//            R.drawable.salman_khan,
//            name = "Salman khan",
//            time = "10:00 AM",
//            message = "hello"
//        ),
//        ChatBox(
//            image = R.drawable.akshay_kumar,
//            name="Akshay Kumar",
//            time = "12:00 PM",
//            message = "hi"
//        ),
//        ChatBox(
//            R.drawable.salman_khan,
//            name = "Salman khan",
//            time = "10:00 AM",
//            message = "hello"
//        ),
//        ChatBox(
//            image = R.drawable.akshay_kumar,
//            name="Akshay Kumar",
//            time = "12:00 PM",
//            message = "hi"
//        ),
//        ChatBox(
//            R.drawable.salman_khan,
//            name = "Salman khan",
//            time = "10:00 AM",
//            message = "hello"
//        ),
//        ChatBox(
//            image = R.drawable.akshay_kumar,
//            name="Akshay Kumar",
//            time = "12:00 PM",
//            message = "hi"
//        )
//
//    )
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = {},
//                containerColor = colorResource(R.color.light_green),
//                modifier = Modifier.size(65.dp),
//                contentColor = Color.White){// here the content is the small message box inside the floating action button
//
//                Icon(painter= painterResource(id=R.drawable.chat_icon),
//                    contentDescription = null,
//                    modifier = Modifier.size(28.dp)
//                )
//            }
//        },
//        bottomBar = {
//            BottomNavigation()
//        }
//    ) {
//        Column (modifier = Modifier.padding(it)){
//            Box(modifier=Modifier.fillMaxWidth()){
//                Text(text="SayHi",
//                    fontSize = 28.sp,
//                    color=colorResource(id=R.color.light_green),
//                    modifier=Modifier.align(Alignment.CenterStart).padding(start = 16.dp),
//                    fontWeight = FontWeight.Bold
//                )
//                Row(modifier= Modifier.align(Alignment.CenterEnd)) {
//                    IconButton(onClick = {}) {
//                        Icon(
//                            painter=painterResource(R.drawable.camera),
//                            contentDescription = null,
//                            modifier=Modifier.size(24.dp)
//                        )
//                    }
//                    IconButton(onClick = {}) {
//                        Icon(
//                            painter=painterResource(R.drawable.search),
//                            contentDescription = null,
//                            modifier=Modifier.size(24.dp)
//                        )
//                    }
//                    IconButton(onClick = {}) {
//                        Icon(
//                            painter=painterResource(R.drawable.more),
//                            contentDescription = null,
//                            modifier=Modifier.size(24.dp)
//                        )
//                    }
//                }
//            }
//            HorizontalDivider()
//            LazyColumn {
//                items(chatData){
//                    ChatDesign(chatBox = it)
//                }
//            }
//        }
//    }
}

@Composable
fun AddUserPopUp(
    onDismiss: () -> Unit,
    onUserAdd: (ChatBox) -> Unit,
    baseViewModel: BaseViewModel
) {
    var phoneNumber by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    var userFound by remember { mutableStateOf<ChatBox?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text(text = " Enter the phone number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
        Row {
            Button(
                onClick = {
                    isSearching = true
                    baseViewModel.SearchUserByPhoneNumber(phoneNumber) { user ->
                        isSearching = false
                        if (user != null) {
                            userFound = user
                        } else {
                            userFound = null
                        }

                    }
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))
            ) {

                Text("Search")
                Spacer(modifier = Modifier.height(8.dp))


            }
            Button(
                onClick = onDismiss,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))
            ) {
                Text("Cancel")
            }
        }
        if (isSearching) {
            Text("Searching...", color = Color.DarkGray)
        }
        userFound?.let {
            Column {
                Text("User Found ${it.name}")
                Button(
                    onClick = {
                        onUserAdd(it)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))
                ) {
                    Text("Add to Chat")
                }
            }
        } ?: run {

            if (isSearching) {
                Text("No user found with this phone number", color = Color.DarkGray)
            }
        }
    }
}