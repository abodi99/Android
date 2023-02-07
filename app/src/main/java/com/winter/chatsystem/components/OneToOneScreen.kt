package com.winter.chatsystem.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.winter.chatsystem.R
import com.winter.chatsystem.ui.theme.ChatSystemTheme

@Composable
fun OneToOne() {
    Box(modifier = Modifier
        .fillMaxSize()
        //  .padding(2.dp)
        .background(color = Color.LightGray)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
                .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Image(painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "ProfileImg",
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .border(width = 4.dp, MaterialTheme.colorScheme.onPrimary, shape = CircleShape)
                    //.size(55.dp)

                )
                Text(text = "Talal",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(bottom = 12.dp)


                )
            }


            LazyColumn(modifier = Modifier
                .background(color = Color.LightGray)
                .fillMaxWidth()
                .fillMaxHeight(0.91f),

                ){
                items(20){ item ->
                    if (item % 2 == 0) {
                        Box(modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart) {
                            Surface(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .width(270.dp)
                                    .wrapContentSize(Alignment.CenterStart),

                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(30.dp),
                                shadowElevation = 8.dp
                            ) {
                                Text(text = "Hello",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .height(40.dp)
                                        .wrapContentSize(),
                                    textAlign = TextAlign.Justify
                                )
                            }
                        }
                    } else {
                        Box(modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd) {
                            Surface(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .width(270.dp)
                                    .wrapContentSize(Alignment.CenterEnd),

                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(30.dp),
                                shadowElevation = 8.dp
                            ) {
                                Text(text = "Hi",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .height(40.dp)
                                        .wrapContentSize(),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))


            Box(
                modifier = Modifier
                    .fillMaxSize(),
                // .padding(top = 200.dp),
                contentAlignment = Alignment.BottomCenter
            ){

                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .height(62.dp),

                    verticalAlignment = Alignment.CenterVertically,
                    //    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(
                        Icons.Default.Phone, contentDescription = "Mic",
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )

                    var textFieldValue by remember { mutableStateOf("") }

                    OutlinedTextField(value = textFieldValue,
                        onValueChange = {textFieldValue = it},
                        label = { Text(text = "Write your message", color = MaterialTheme.colorScheme.onPrimary)},
                        modifier = Modifier
                            .width(280.dp)
                            .padding(bottom = 6.5.dp),
                        singleLine = true,
                        leadingIcon = { Icon(imageVector = Icons.Default.Face, contentDescription = "Msg", tint = MaterialTheme.colorScheme.onPrimary)},
                        trailingIcon = { Icon(imageVector = Icons.Default.Send, contentDescription = "Send", tint = MaterialTheme.colorScheme.onPrimary)}
                    )
                    Icon(
                        Icons.Default.Info, contentDescription = "Mic",
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )


                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatSystemTheme {
        OneToOne()
    }
}