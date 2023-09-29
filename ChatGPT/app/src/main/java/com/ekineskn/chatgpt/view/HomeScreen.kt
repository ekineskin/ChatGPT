package com.ekineskn.chatgpt.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekineskn.chatgpt.ui.theme.White200
import com.ekineskn.chatgpt.viewmodel.AppViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ekineskn.chatgpt.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: AppViewModel = viewModel()
) {
    val messages by viewModel.messages.collectAsState()
    val anythingLoading by viewModel.loading.collectAsState()

    val (input, setInput) = remember {
        mutableStateOf("")
    }

    Scaffold(containerColor = Color.White, topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text("Chat GPT", color = Color.White)
                Text("Online", fontSize = 10.sp, color = White200)
            }
        }
    }) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
            verticalArrangement = Arrangement.Bottom
        ) {
            LazyColumn() {
                items(messages) { message ->
                    Message(message)
                }
                if (anythingLoading) {
                    item {
                        Loading()
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(White200)
                    .padding(end = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(modifier = Modifier.weight(.9f), value = input, onValueChange = {
                    setInput(it)
                },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        textColor = Color.Gray,
                        placeholderColor = Color.Gray

                    ), placeholder = {
                        Text("Question")
                    })
                IconButton(modifier= Modifier.weight(.1f),onClick = {
                    if (input.isNotEmpty()){
                        viewModel.askQuestion(input)
                        setInput("")
                    }
                }) {
                    Icon(Icons.Default.Send, contentDescription = null, tint = Color.Gray)
                }
            }
        }
    }


}