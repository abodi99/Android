package com.winter.chatsystem.components

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
import com.winter.chatsystem.R
import com.winter.chatsystem.ui.theme.ChatSystemTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneToOne(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
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
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                navController.popBackStack()
                            }
                        )
                )
                Image(painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "ProfileImg",
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .border(
                            width = 4.dp,
                            MaterialTheme.colorScheme.onPrimary,
                            shape = CircleShape
                        )
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
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .fillMaxHeight(0.81f),

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

                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(topEnd = 45.dp, bottomEnd = 25.dp, bottomStart = 50.dp, topStart = 5.dp),
                                shadowElevation = 0.dp
                            ) {
                                Text(text = "Hello, whats up?",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp)
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

                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(topEnd = 45.dp, bottomEnd = 5.dp, bottomStart = 40.dp, topStart = 25.dp),
                                shadowElevation = 0.dp
                            ) {
                                Text(text = "Hi, how you doing?",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp)
                                     //   .height(35.dp)
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
                Column {
                    Row(
                        modifier = Modifier

                            .fillMaxWidth()
                            .height(65.dp)
                            .background(MaterialTheme.colorScheme.background),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            Icons.Default.AccountCircle, contentDescription = "Mic",
                            modifier = Modifier
                                .padding(start = 6.dp, end = 6.dp)
                                .size(30.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        var textFieldValue by remember { mutableStateOf("") }

                        OutlinedTextField(value = textFieldValue,
                            onValueChange = {textFieldValue = it},
                           // label = { Text(text = "Write your message", color = MaterialTheme.colorScheme.onPrimary)},
                            placeholder = { Text(text = "Write your message", color = MaterialTheme.colorScheme.onPrimary)},
                            modifier = Modifier
                                .width(280.dp)
                                .padding(bottom = 4.5.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(20.dp),
                            leadingIcon = { Icon(
                                imageVector = Icons.Default.Face,
                                contentDescription = "Msg",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )},

                            trailingIcon = { Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "Send",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )},

                            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colorScheme.onPrimary,focusedBorderColor = MaterialTheme.colorScheme.primary, unfocusedBorderColor = MaterialTheme.colorScheme.primary, containerColor = MaterialTheme.colorScheme.primary)
                        )
                        Icon(
                            Icons.Default.AddCircle, contentDescription = "Mic",
                            modifier = Modifier
                                .padding(start = 6.dp, end = 6.dp)
                                .size(35.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .fillMaxWidth()
                            .height(62.dp),

                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            navController.navigate("accountSettings")
                        }) {
                            Icon(
                                Icons.Default.Person, contentDescription = "Mic",
                                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(text = "Profile")
                        }

                        Button(onClick = {
                            navController.navigate("settings")
                        }) {
                            Icon(
                                Icons.Default.Settings, contentDescription = "Mic",
                                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(text = "Settings")
                        }
                    }
                }
            }
        }
    }
}