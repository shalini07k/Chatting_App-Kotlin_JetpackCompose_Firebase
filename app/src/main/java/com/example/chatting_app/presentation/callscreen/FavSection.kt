package com.example.chatting_app.presentation.callscreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatting_app.R

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FavSection() {

    val samplefav= listOf(
        Favitem(image = R.drawable.akshay_kumar, name= "Aman"),
        Favitem(image = R.drawable.man, name= "Rahul"),
        Favitem(image = R.drawable.salman_khan, name= "Riya"),
        Favitem(image = R.drawable.woman, name= "Rama"),
        Favitem(image = R.drawable.man, name= "Ravi")
    )

    Column(modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)) {
        Text(
            text = "Favorites",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())) {
            samplefav.forEach {
                Favscreen(favitem = it)
            }
        }

    }
}
