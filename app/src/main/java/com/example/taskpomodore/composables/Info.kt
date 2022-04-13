package com.example.taskpomodore

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShowInfo() {
    val context = LocalContext.current
    val intentGithub = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jurgielele")) }
    val intentLinkedIn = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/patryk-jurgielewicz-4681b2222/")) }
    val intentMail = remember {Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:patrykjurgielewicz123@gmail.com"))}
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp)
                .padding(vertical = 4.dp)
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally,

            )
        {
            Image(
                painter = painterResource(R.drawable.photo),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Text(
                "Patryk Jurgielewicz",
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                lineHeight = 90.sp
            )
            Text(
                "I am a 2nd year student of computer science at the Gdańsk School of Banking - extramural studies. I am actively looking for an internship as an android developer.",
                fontSize = 16.sp,
                color = Gray,
                fontWeight = FontWeight.Medium,
                lineHeight = 22.sp,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )
            InfoItemRow(name = "Github", painter = painterResource(id = R.drawable.icon_github) ) {
                context.startActivity(intentGithub)

            }
            InfoItemRow(name = "LinkedIn", painter = painterResource(id = R.drawable.icon_linkedin) ) {
                context.startActivity(intentLinkedIn)

            }
            InfoItemRow(name = "E-mail", painter = painterResource(id = R.drawable.icon_email) ) {
                    context.startActivity(intentMail)
            }
        }
    }
}

@Composable
fun InfoItemRow(name: String,painter: Painter, onClick: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(horizontal = 12.dp)
        .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .size(32.dp)
        )
        Divider(modifier = Modifier.width(10.dp))
        Text(
            name,
            color = Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 48.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowInfoPreview(){
    ShowInfo()
}