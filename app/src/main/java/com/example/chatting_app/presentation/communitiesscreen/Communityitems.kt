package com.example.chatting_app.presentation.communitiesscreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatting_app.R

@Composable
fun Communityitems(community: Community){
    Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(community.image),
            contentDescription = null,
            modifier = Modifier.size(55.dp)
        )
        Spacer(modifier =Modifier.width(12.dp))

        Column {
            Text(text =community.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text= community.members, color= Color.Gray, fontSize = 14.sp)
        }
    }
}

data class Community(val image: Int, val name: String, val members: String)