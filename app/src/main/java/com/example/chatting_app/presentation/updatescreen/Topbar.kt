package com.example.chatting_app.presentation.updatescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatting_app.R

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Topbar() {
    var searching by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    var showmenu by remember {mutableStateOf(false)}
    Box(
        modifier = Modifier.fillMaxWidth().padding(top= 25.dp)
    ){
        Column {
            Row {
                if(searching){
                    TextField(value=search,
                        onValueChange = { search = it },
                        placeholder = { Text(text="Search") },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor =Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(start=12.dp), singleLine = true
                    )
                }
                else {
                    Text(text= "Updates", fontSize = 28.sp, color= Color.Black, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start= 12.dp, top=4.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                if(searching){
                    IconButton(onClick = {searching = false}) {
                        Icon(painter = painterResource(R.drawable.cross),
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
                else{
                    IconButton(onClick = {}) {
                        Icon(painter = painterResource(R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = {searching = true}) {
                        Icon(painter = painterResource(R.drawable.search),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = {showmenu = true},
                    ) {
                        Icon(painter = painterResource(R.drawable.more),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        DropdownMenu(expanded = showmenu, onDismissRequest = {showmenu = false}){
                            DropdownMenuItem(text = {Text(text="Status Privacy")}, onClick ={showmenu = false})
                            DropdownMenuItem(text = {Text(text="Create Channel")}, onClick ={showmenu = false})
                            DropdownMenuItem(text = {Text(text="Settings")}, onClick ={showmenu = false})
                        }
                    }
                }

            }
            HorizontalDivider()
        }
    }
}