package com.winter.chatsystem.components

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.winter.chatsystem.R
import com.winter.chatsystem.ui.theme.ChatSystemTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.text.toLowerCase
import java.util.*


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneToOne(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            Row(modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                navController.popBackStack()
                            }
                        )
                )
                Icon(
                    Icons.Rounded.AccountCircle,
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(6.dp)
                        .clip(CircleShape)
                        .border(
                            width = 4.dp,
                            MaterialTheme.colorScheme.onPrimary,
                            shape = CircleShape
                        )
                )
                Text(text = "Talal",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp,
                    modifier = Modifier
                )
            }
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 60.dp, bottom = 180.dp),
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
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 50.dp, bottomStart = 15.dp, topStart = 15.dp),
                                shadowElevation = 0.dp
                            ) {
                                Text(text = "Hello, whats up?",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(7.dp)
                                        //  .height(35.dp)
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
                                shape = RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp, bottomStart = 50.dp, topStart = 10.dp),
                                shadowElevation = 0.dp
                            ) {
                                Text(text = "Hi, how you doing?",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(7.dp)
                                        //   .height(35.dp)
                                        .wrapContentSize(),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
            }
            //Spacer(modifier = Modifier.weight(1f))
        },
        bottomBar = {
            Column(
                modifier = Modifier

            ) {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxWidth()
                        .height(95.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(
                        Icons.Default.AccountCircle, contentDescription = "Mic",
                        modifier = Modifier
                            .padding(start = 6.dp, end = 6.dp)
                            .size(35.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    var textFieldValue by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = textFieldValue,
                        onValueChange = {textFieldValue = it},
                        // label = { Text(text = "Write your message", color = MaterialTheme.colorScheme.onPrimary)},
                        placeholder = { Text(text = "Write your message",
                            //color = MaterialTheme.colorScheme.onTertiary
                        )},
                        modifier = Modifier
                            .width(280.dp)
                            .padding(bottom = 4.5.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(30.dp),
                        leadingIcon = { Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "Msg",
                            //tint = MaterialTheme.colorScheme.onPrimary
                        )},
                        trailingIcon = { Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send",
                            //tint = MaterialTheme.colorScheme.onPrimary,
                        )},
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            placeholderColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                    Icon(
                        Icons.Default.AddCircle, contentDescription = "Mic",
                        modifier = Modifier
                            .padding(start = 6.dp, end = 6.dp)
                            .size(35.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                BottomNavBar(navController)
            }
        }
    )
}