package com.ekineskn.chatgpt.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekineskn.chatgpt.models.Message
import com.ekineskn.chatgpt.models.fromUser
import com.ekineskn.chatgpt.ui.theme.White200

@Composable
fun Message(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (message.fromUser) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topEnd = if (message.fromUser) 2.dp else 14.dp,
                        topStart = if (message.fromUser) 14.dp else 2.dp,
                        bottomStart = 14.dp,
                        bottomEnd = 14.dp
                    )
                )
                .background(if (message.fromUser) White200 else MaterialTheme.colorScheme.primary)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {

            Text(
                if (message.fromUser) "You" else "Chat GPT",
                fontSize = 10.sp,
                color = if (message.fromUser) Color(0xff414141) else White200
            )
            Text(
                message.content,
                color = if (message.fromUser) Color(0xff212121) else Color.White
            )
            Spacer(modifier = Modifier.height(5.dp))

        }
    }
}